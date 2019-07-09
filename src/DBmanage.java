import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;

import addOns.Book;
import addOns.Message;

import com.microsoft.sqlserver.jdbc.*;

public class DBmanage {

	public static boolean validate(String name, String pass) {
		boolean status = false;

		Connection conn = Connect.getConnect().getConnection();

		PreparedStatement ps;
		try {
			ps = conn
					.prepareStatement("select * from users where email=? and password=?");
			ps.setString(1, name);
			ps.setString(2, pass);
			ResultSet rs = ps.executeQuery();
			status = rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return status;

	}

	public static int newUser(String fname, String lname, String email,
			String password) {
		Connection conn = Connect.getConnect().getConnection();
		PreparedStatement ps;
		//check that the user isn't already in the database, if they aren't 
		int num = uniqueEmail(email);
		if (num == 0) {
			//insert their data into the database
			try {

				ps = conn
						.prepareStatement("insert into users(firstName, lastName, password, email) values( ?, ?, ?, ?)");
				ps.setString(1, fname);
				ps.setString(2, lname);
				ps.setString(3, password);
				ps.setString(4, email);
				ps.executeUpdate();
				return 0;
			} catch (SQLException e) {
				e.printStackTrace();
				return -2;
			}
		} 
		return num;
	}

	//method to see if that email is in the db
	public static int uniqueEmail(String email) {

		try {
			PreparedStatement s = Connect.getConnect().getConnection()
					.prepareStatement("select * from users where email=?");
			s.setString(1, email);
			ResultSet set = s.executeQuery();
			//if nothing was returned, then its unique
			if (!set.next()) {
				return 0;
			} else {
				return -1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}

	}

	public static String getFirstName(String userid) {
		//gets a connection to database
		//System.out.println("The user id we are getting is " + userid);
		Connection conn = Connect.getConnect().getConnection();
		String name = null;
		try {
			//make a sql statement
			PreparedStatement s = conn.prepareStatement("select * from users where userId= ?");
			s.setString(1, userid);
			ResultSet set = s.executeQuery();
			
			//something must be returned because we only call this after logging them in
			if(!set.next()){
				//System.out.println("It was null");
			
			}
			else{
				name =  set.getString("firstName");
				//System.out.println(name);
			}
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return name;
	}
	
	
	public static ArrayList<Book> getChatRoom(String userID){
		Connection conn = Connect.getConnect().getConnection();
		ArrayList <Book> bookISBN = new ArrayList<Book> ();
		Book book;
		try {
			PreparedStatement s = conn.prepareStatement("select chatRoomId,bookTitle from chatRoom inner join usersChatLog on chatRoomId = chatRoomISBN where userID= ?");
			s.setString(1, userID);
			ResultSet set = s.executeQuery();
			//something must be returned because we only call this after logging them in
			while(set.next()){
				book = new Book(set.getString("chatRoomId"),set.getString("bookTitle"));
				bookISBN.add(book);
			}
			 			
				
		}
		
		catch (SQLException e){
			e.printStackTrace();
		}
		
		return bookISBN;
}
	public static ArrayList<Book> getAllRooms(){
		Connection conn = Connect.getConnect().getConnection();
		ArrayList <Book> allChats = new ArrayList<Book> ();
		Book book;
		try {
			PreparedStatement s = conn.prepareStatement("select chatRoomISBN, bookTitle from chatRoom");
			ResultSet set = s.executeQuery();
			//something must be returned because we only call this after logging them in
			while(set.next()){
				 book =new Book(set.getString("chatRoomISBN"), set.getString("bookTitle"));
				 allChats.add(book);
			}
		}
		
		catch (SQLException e){
			e.printStackTrace();
		}
		
		return allChats;
}
	
	public static int getUserID(String uname)
	{
		Connection conn = Connect.getConnect().getConnection();
		int id = -1;
		try {
			//make a sql statement
			PreparedStatement s = conn.prepareStatement("select userId from users where email= ?");
			s.setString(1, uname);
			ResultSet set = s.executeQuery();
			//something must be returned because we only call this after logging them in
			if(set.next())
			{
				id =  set.getInt("userId");
			}
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return id;
	}
	
	 public static boolean checkUserInChat(String isbn, String userid){
		 Connection conn = Connect.getConnect().getConnection();
		 try{
		 PreparedStatement s = conn.prepareStatement("select * from UsersChatLog where userId = ? and chatRoomId = ?");
		 s.setString(1,userid);
		 s.setString(2,isbn);
		ResultSet set = s.executeQuery();
		   if(set.next())
		   {
			   return true;
		   }
		  
		 }
		 catch(SQLException e){
				e.printStackTrace();
			}
		return false;
	 }
		 
	public static boolean addUserToChat(String isbn, String userid) {
		Connection conn = Connect.getConnect().getConnection();
		try {
			PreparedStatement s = conn.prepareStatement("insert into usersChatLog(userId, chatRoomId) values(?, ?)");
			s.setString(1, userid);
			s.setString(2, isbn);
			
			s.executeUpdate();
			return true;
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return false;
	}
	
	public static ArrayList<Message> getMessages(String chatID){
		Message message;
		String userID;
		ArrayList<Message> msgs = new ArrayList<Message>();
		try {
			Connection conn = Connect.getConnect().getConnection();
			PreparedStatement stmt = conn.prepareStatement("select * from message where chatRoomId = ?");
			stmt.setString(1, chatID);
			ResultSet set = stmt.executeQuery();
			while(set.next())
			{
				//To do - change it that it really gets their first name. instead its hardcoded now.
				message = new Message();
				message.setMessage(set.getString("message"));
				message.setTime(set.getTimestamp("time"));
				userID = set.getString("userId");
				message.setUserId(userID);
				message.setFname(getFirstName(userID));
				msgs.add(message);
			}
			//set that array list into the request and forward to the JSP
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return msgs;
	}
	public static void postMessage(String chatId, String userId, String post, Timestamp time){
		try{
			Connection conn = Connect.getConnect().getConnection();
			PreparedStatement stmt = conn.prepareStatement("insert into message(userId, chatRoomId, message, time) "
					+ "values(?, ?, ? ,?)");
			stmt.setString(1,  userId);
			stmt.setString(2, chatId);
			stmt.setString(3, post);
			stmt.setTimestamp(4,  time);
			
			stmt.executeUpdate();
		}
		
		catch(SQLException e){
			e.printStackTrace();
		}
		
	}
}
