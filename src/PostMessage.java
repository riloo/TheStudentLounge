

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import addOns.Message;

/**
 * Servlet implementation class PostMessage
 */
@WebServlet("/PostMessage")
public class PostMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostMessage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("\ndoPost message was called via " + request.getMethod());
		
		HttpSession session = request.getSession();

		String chatId = (String) session.getAttribute("roomID");
		String userId = session.getAttribute("userID").toString();
		String post = (String) request.getParameter("userPost");
		DBmanage.postMessage(chatId, userId, post, Timestamp.valueOf(LocalDateTime.now()));

		System.out.println("Posting message to " + chatId + " from the user " + userId + " message is" + post);
		//want to send it to the Message servlet so it can now reload it with the new messages. But thats not working....
		request.getRequestDispatcher("/MessageServlet").forward(request,  response);

	}

}
