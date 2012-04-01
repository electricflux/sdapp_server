package com.sdapp.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdapp.logger.SdLogger;

/**
 * Servlet implementation class LoginServlet
 */
public class RegistrationSuccessfulServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SdLogger.getInstance().getLogger().info("Get on RegistrationSuccessfulServlet");
		System.out.println("Get on RegistrationSuccessfulServlet");
		getServletContext().getRequestDispatcher("/WEB-INF/jsp/registrationSuccessfulServlet.jsp").forward(req, resp);
	}	
}
