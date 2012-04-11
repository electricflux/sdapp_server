package com.sdapp.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdapp.logger.SdLogger;

public class GetApkServlet extends HttpServlet {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			SdLogger.getInstance().getLogger().info("Get on getAPKServlet");
			resp.sendRedirect("/WEB-INF/apk/quickpark.apk");
		}
}
