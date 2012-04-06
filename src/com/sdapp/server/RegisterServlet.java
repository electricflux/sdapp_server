package com.sdapp.server;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdapp.domain.LicensePlateMsg;
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		SdLogger.getInstance().getLogger().info("Post on RegisterServlet");

		/**
		 * Get the parameters
		 */
		String username = request.getParameter("username");
		String deviceId = request.getParameter("deviceId");
		String authToken = request.getParameter("authToken");
		String license = request.getParameter("license");
		String isDeviceString = request.getParameter("isDevice");
		boolean isDevice = false;

		if (isDeviceString != null && isDeviceString.length() > 0)
			isDevice = Boolean.parseBoolean(isDeviceString);

		/** Clean up white-space from license plate string */
		license= license.replaceAll("\\s+", "");
		license = license.toLowerCase();

		boolean result = registerUser(username, deviceId, authToken, license);

		/** If its device and registration failed, return a 403 */
		if ((false == result) && 
				isDevice)
		{
			response.setStatus(403);
			return;
		}

		/** Create the response */
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String title = "Reading All Request Parameters - Registration status: " +
				String.valueOf(result);
		out.println(ServletUtilities.headWithTitle(title) +
				"<BODY BGCOLOR=\"#FDF5E6\">\n" +
				"<H1 ALIGN=CENTER>" + title + "</H1>\n" +
				"<TABLE BORDER=1 ALIGN=CENTER>\n" +
				"<TR BGCOLOR=\"#FFAD00\">\n" +
				"<TH>Parameter Name<TH>Parameter Value(s)");
		/** Print parameters as rows */
		printParamRow(out,"username", username);
		printParamRow(out,"deviceId", deviceId);
		printParamRow(out,"authToken", authToken);
		printParamRow(out,"license", license);
		/** End table */
		out.println("</TABLE>\n</BODY></HTML>");
	}

	private boolean registerUser(String username, String deviceId, String authToken, String license)
	{
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
			 * See if the user object already exists
			 */
			UserMsg retrievedUser  = DAO.getUser(username,false);
			if (retrievedUser != null)
				return false;

			/**
			 * Create the user object
			 */
			UserMsg user = new UserMsg();
			user.setUserName(username);
			user.setAuthToken(authToken);
			user.setDeviceIdentifier(deviceId);


			String licenses[] = license.split(LicensePlateMsg.getSEPARATOR());
			for (String lic : licenses)
			{
				if (lic.length() > 0)
				{
					LicensePlateMsg msg = new LicensePlateMsg();
					msg.setLicensePlateNumber(lic);
					user.getLicensePlateList().add(msg);
				}
			}

			/** Save user */
			DAO.saveUser(user);

			SdLogger.getInstance().getLogger().info("Username: "+user.getUserName());
			return true;
		}

		return false;
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

