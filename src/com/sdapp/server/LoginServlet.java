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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{	    
			/**
			 * Get the parameters
			 */
			String username = request.getParameter("username");
			String deviceId = request.getParameter("deviceId");

			/**
			 * Sanity check
			 */
			if ((username != null) && (deviceId != null) &&
					(username.length() > 0) &&
					(true == username.contains("@")))
			{	 

				/**
				 * Create the user object
				 */
				UserMsg user = new UserMsg();
				user.setUserName(username);
				user.setDeviceIdentifier(deviceId);

				/**
				 * See if the user object exists
				 */
				user  = DAO.getUser(user);

				if (user != null)
				{
					request.setAttribute("user",user);
					getServletConfig().getServletContext().
					getRequestDispatcher("/WEB-INF/jsp/loginSuccessfulServlet.jsp?user=" + request.getParameter("user")).forward(request, response);
				}
			}
			getServletConfig().getServletContext().
			getRequestDispatcher("/WEB-INF/jsp/loginServlet.jsp").forward(request, response);
		} 
		catch (Throwable theException) 	    
		{
			SdLogger.getInstance().getLogger().info(theException.getMessage()); 
		}


	}
}

