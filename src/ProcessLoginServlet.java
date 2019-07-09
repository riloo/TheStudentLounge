

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ProcessLoginServlet
 */
@WebServlet("/ProcessLoginServlet")
public class ProcessLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProcessLoginServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String uname = request.getParameter("uname");
		String password = request.getParameter("psw");
		/**PrintWriter out = response.getWriter();
		//Check that I'am actually in the server with the correct parameters of name and password
		out.write("In servlet now");
		out.write(name + " ");
		out.write(password);
		*/
		//if its a valid name and password
		if(DBmanage.validate(uname, password))
		{
			//set a session with their first name
			HttpSession session = request.getSession();
			//- put this back in eventually!! session.setAttribute("name", DBmanage.getFirstName(uname));
			//add the userid in a session variable.
			session.setAttribute("userID", DBmanage.getUserID(uname));
			//TO DO add last name to session
			request.getRequestDispatcher("/html/home.jsp").forward(request, response);
		}
		
		
		else
		{
			request.getRequestDispatcher("/html/Login.jsp").forward(request, response);
			
		}
	}

}
