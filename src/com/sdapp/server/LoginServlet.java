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

	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, java.io.IOException {

		try
		{	    
			UserMsg user = new UserMsg();
			user.setUserName(request.getParameter("un"));

			MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
			digest.update(request.getParameter("pw").getBytes());
			byte[] hash = digest.digest();
			user.setMd5Password(new String(hash));

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

