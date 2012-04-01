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
public class RegisterServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SdLogger.getInstance().getLogger().info("Get on registerServlet");
		getServletContext().getRequestDispatcher("/WEB-INF/jsp/registerServlet.jsp").forward(req, resp);
	}
}

