import java.sql.*;

public class DBconnect {
	
	public static Connection connect()
	{
		Connection conn = null;
		String url = "jdbc:sqlserver://localhost:1433;databaseName=HwChatRoom;integratedSecurity=true";
		try {
			DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		   return conn;
	}
	
	
	// A test main method to see if my connection works - and it does!!
	public static void main(String[] args) throws SQLException
	{
		String name;
		Connection conn = DBconnect.connect();

		//check if emails in db.
		PreparedStatement s = conn.prepareStatement("select * from users where email='miriam@gmail.com'");
		ResultSet set = s.executeQuery();
		if(!set.next())
		{
			System.out.println("nothing there you can insert");
			PreparedStatement stmt = conn.prepareStatement("insert into users(firstName, lastName, password, email) values ('Goldie', 'sdf', 'asdfg', 'miriam@gmail.com')");
			int num = stmt.executeUpdate();
			System.out.println(num);
		}
		else {
			System.out.println("its already there");
		}
	
		
	}

}
