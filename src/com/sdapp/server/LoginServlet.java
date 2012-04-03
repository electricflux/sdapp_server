package com.sdapp.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdapp.domain.UserMsg;
import com.sdapp.logger.SdLogger;
import com.sdapp.persistencemanager.DAO;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SdLogger.getInstance().getLogger().info("Get on loginServlet");
		getServletContext().getRequestDispatcher("/WEB-INF/jsp/loginServlet.jsp").forward(req, resp);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		SdLogger.getInstance().getLogger().info("Get on LoginSuccessfulServlet");

		/**
		 * Get the parameters
		 */
		String username = request.getParameter("username");
		
		UserMsg user = getUser(username);

		/** If login failed, return a 403 */
		if (null == user)
		{
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			return;
		}

		/** Create the response */
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String title = "Login Successful";
		out.println(ServletUtilities.headWithTitle(title) +
				"<BODY BGCOLOR=\"#FDF5E6\">\n" +
				"<H1 ALIGN=CENTER>" + title + "</H1>\n" +
				"<TABLE BORDER=1 ALIGN=CENTER>\n" +
				"<TR BGCOLOR=\"#FFAD00\">\n" +
				"<TH>Parameter Name<TH>Parameter Value(s)");
		/** Print parameters as rows */
		printParamRow(out,"username", user.getUserName());
		printParamRow(out,"deviceId", user.getDeviceIdentifier());
		printParamRow(out,"authToken", user.getAuthToken());
		/** End table */
		out.println("</TABLE>\n</BODY></HTML>");
	}

	private UserMsg getUser(String username)
	{
		UserMsg verifiedUser = null;
		/**
		 * Sanity check
		 */
		if ((username != null) &&
				(username.length() > 0) &&
				(true == username.contains("@")))
		{	 

			/**
			 * Create the user object
			 */
			UserMsg user = new UserMsg();
			user.setUserName(username);

			/**
			 * See if the user object exists
			 */
			verifiedUser  = DAO.getUser(user);
		}
		return verifiedUser;

	}

	private void printParamRow(PrintWriter out, String name, String value)
	{
		out.println("<TR><TD>"+name+"\n<TD>");
		if (value.length() == 0)
			out.print("<I>No Value</I>");
		else
			out.print(value);
	}
}

