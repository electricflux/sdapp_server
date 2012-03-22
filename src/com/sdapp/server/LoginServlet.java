package com.sdapp.server;

import java.security.MessageDigest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sdapp.PersistenceManager.DAO;
import com.sdapp.domain.UserMsg;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, java.io.IOException {

		try
		{	    
			/**
			 * Get the parameters
			 */
			String username = request.getParameter("un");
			String password = request.getParameter("pw");
			
			/**
			 * Sanity check
			 */
			if ((username == null || password == null) &&
					(username.length()<0 || password.length()<0) &&
					(false == username.contains("@")))
				response.sendRedirect("invalidLogin.jsp"); //error page 
			
			/**
			 * Create the user object
			 */
			UserMsg user = new UserMsg();
			user.setUserName(username);

			MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
			digest.update(password.getBytes());
			byte[] hash = digest.digest();
			user.setMd5Password(new String(hash));
			
			/**
			 * See if the user object exists
			 */
			user  = DAO.getUser(user);

			if (user != null)
			{
				HttpSession session = request.getSession(true);	    
				session.setAttribute("currentSessionUser",user); 
				response.sendRedirect("userLogged.jsp"); //logged-in page      		
			}
			else 
				response.sendRedirect("invalidLogin.jsp"); //error page 
		} 
		catch (Throwable theException) 	    
		{
			System.out.println(theException); 
		}
	}
}

