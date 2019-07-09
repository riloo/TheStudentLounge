

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

import addOns.Message;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MessageServlet
 * wil retrieve messages and send them to the jsp
 */
@WebServlet(urlPatterns = { "/MessageServlet" })
public class MessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MessageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("In MessageServlet.doGet");
		//get the session
		HttpSession session = request.getSession();
		String chatId;
		//if they do not have a the session for the chat, then set it
		System.out.println(request.getMethod());
		System.out.println("The session room id is " + session.getAttribute("roomID"));
		System.out.println("The user id is " + session.getAttribute("userID"));
	
		//if they accessed this with a get, meaning they chose it from their chats
		if(request.getMethod() == "GET" && request.getParameter("room") != null ){
			System.out.println("The request parameter for room is " + request.getParameter("room"));
			chatId =  request.getParameter("room");
			session.setAttribute("roomID",chatId);
			System.out.println("the room is " + chatId);
		}
		//otherwise its just a refresh because they posted a message, or its an AJAX refresh
		else{
			//this is set so that if they do a refresh it will know that the post was already executed.
			System.out.println("the room was not null");
			chatId = (String) session.getAttribute("roomID");
			System.out.println("The room id is " + chatId);
		}
		System.out.println("The chat that you are in now is " + chatId);
		ArrayList<Message> msgs = DBmanage.getMessages(chatId);
			
		//set that array list into the request and forward to the JSP
		request.setAttribute("messages", msgs);
		
		request.getRequestDispatcher("/html/Chatroom.jsp").forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	//this will receive messages from the submit message button.
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("In MessageServlet.doPost");
		HttpSession session = request.getSession();
		//doGet(request, response);
		
		
		//trying a redirect instead to change method type to get hopefully -
		//response.sendRedirect("./MessageServlet");
		//that doesn't work, because it resets all the information.
		
		//try a redirect with parameters in the url
		String url = "./MessageServlet?room=" + session.getAttribute("roomID") + "&userID="+ session.getAttribute("userID");
		System.out.println("The url we are sending you to is " + url);
		response.sendRedirect(url);
		//maybe make a new request with only the information that I want it in and then do a request.forward....
		// N0. you cannot make a new HttpServletRequest
		
		// this wont work, because it wont go to the doGet(). request.getRequestDispatcher("/MessageServlet").forward(request,  response);
		
	}

}
