

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class addToChat
 */
@WebServlet("/addToChat")
public class addToChat extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addToChat() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String isbn = request.getParameter("isbn");
		String userID = request.getSession().getAttribute("userID").toString();
		System.out.println("Your isbn is " + isbn);
		//check if they are already in it
		if(DBmanage.checkUserInChat(isbn,userID)){
			//send them back
			System.out.println("Can't add you.");
			request.setAttribute("cantJoin", "true");
			request.getRequestDispatcher("./getAllRooms").forward(request, response);
		}
		else{
			boolean success = DBmanage.addUserToChat(isbn,userID);
			if(success)
			{
				System.out.println("added you to the database");
			}
			else {
				System.out.println("could not add you to the database");
			}
			
			response.sendRedirect("./getRoomsServlet");
			System.out.println("Got to this line - uh oh.");
		}
	}

}
