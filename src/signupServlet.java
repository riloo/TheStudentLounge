
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class signupServlet
 */
@WebServlet("/signupServlet")
public class signupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public signupServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String email = request.getParameter("email");
		String password = request.getParameter("psw");

		int executed = DBmanage.newUser(fname, lname, email, password);
		//if they are registered as a new user
		if(executed == 0){
			HttpSession session = request.getSession();
			session.setAttribute("name", fname);
			//request.getRequestDispatcher("./LoginServlet").forward(request,response); this only works for pages, not servlets
			response.sendRedirect("./LoginServlet");
			
		}
		//if it cannot make a them a new user, either database connection issues, are they are already registered
		else {
			//send them back to the sign up page
			//TO DO - display an alert or something that they should change their info
			request.getRequestDispatcher("/html/signupForm.html").forward(request, response);
		}

	}

}
