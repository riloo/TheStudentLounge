

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import addOns.Book;

/**
 * Servlet implementation class getRoomsServlet
 */
@WebServlet(description = "will the a list of the users rooms", urlPatterns = { "/getRoomsServlet" })
public class getRoomsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getRoomsServlet() {
     
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		   String id = request.getSession().getAttribute("userID").toString();
		   ArrayList <Book> rooms = DBmanage.getChatRoom(id);
		   request.setAttribute("roomList", rooms);
		   request.getRequestDispatcher("/html/chats.jsp").forward(request, response);
	        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
