package com.sdapp.server;

import java.io.IOException;

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
public class RegisterServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SdLogger.getInstance().getLogger().info("Get on registerServlet");
		getServletContext().getRequestDispatcher("/WEB-INF/jsp/registerServlet.jsp").forward(req, resp);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{	    
			/**
			 * Get the parameters
			 */
			String username = request.getParameter("username");
			String deviceId = request.getParameter("deviceId");
			String authToken = request.getParameter("authToken");
			String license = request.getParameter("license");

			/**
			 * Sanity check
			 */
			if ((username != null) && (deviceId != null) &&
					(license != null) &&
					(authToken != null) &&
					(username.length() > 0) &&
					(true == username.contains("@")))
			{	 

				/**
				 * Create the user object
				 */
				UserMsg user = new UserMsg();
				user.setUserName(username);
				user.setAuthToken(authToken);
				user.setDeviceIdentifier(deviceId);

				/**
				 * See if the user object exists
				 */
				user  = DAO.getUser(user);

				if (user != null)
					DAO.updateUser(user,authToken);
				else 
					DAO.saveUser(user);
				
				request.setAttribute("user",user);			
				SdLogger.getInstance().getLogger().info("Username: "+user.getUserName());
			}
		} 
		catch (Throwable theException) 	    
		{
			SdLogger.getInstance().getLogger().info(theException.getMessage()); 
		}
		
	    getServletConfig().getServletContext().
	    	getRequestDispatcher("/WEB-INF/jsp/registrationSuccessfulServlet.jsp?user=" + request.getParameter("user")).forward(request, response);
	}
}

