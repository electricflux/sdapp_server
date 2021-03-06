package com.sdapp.server;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sdapp.domain.LicensePlateMsg;
import com.sdapp.domain.PaymentMsg;
import com.sdapp.domain.UserMsg;
import com.sdapp.domain.json.LicensePlateJsonObject;
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
	private static boolean createJSONRespone = true;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SdLogger.getInstance().getLogger().info("Get on loginServlet");
		getServletContext().getRequestDispatcher("/WEB-INF/jsp/loginServlet.jsp").forward(req, resp);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		SdLogger.getInstance().getLogger().info("Post on LoginServlet");

		/**
		 * Get the parameters
		 */
		String username = request.getParameter("username");
		String isDeviceString = request.getParameter("isDevice");
		boolean isDevice = false;

		if (isDeviceString != null && isDeviceString.length() > 0)
			isDevice = Boolean.parseBoolean(isDeviceString);

		UserMsg user  = null;
		/**
		 * Sanity check
		 */
		if ((username != null) &&
				(username.length() > 0) &&
				(true == username.contains("@")))
		{	 
			/**
			 * See if the user object exists
			 */
			user  = DAO.getUser(username, true);
		}


		/** Login failed */
		if (null == user)
		{
			/** If login failed on device, return a 403 */
			if (isDevice)
			{
				response.setStatus(HttpServletResponse.SC_FORBIDDEN);
				return;
			}
			else
			{
				/** Create the response */
				response.setStatus(HttpServletResponse.SC_OK);
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				String title = "Login Failure";
				out.println(ServletUtilities.headWithTitle(title) +
						"<BODY BGCOLOR=\"#FDF5E6\">\n" +
						"<H1 ALIGN=CENTER>" + title + "</H1>\n" +
						"<TABLE BORDER=1 ALIGN=CENTER>\n");
				/** End table */
				out.println("</TABLE>\n</BODY></HTML>");
			}
		}
		/** Login successful */
		else
		{
			/** Login successful */
			response.setStatus(HttpServletResponse.SC_OK);
			
			/** JSON response or html*/
			if (createJSONRespone)
			{
				/**
				 * TODO: Sid fix this.
				 * Cant figure out how to deserialize JSON objects on client.
				 * It keeps crashing. So extracting String and passing String object.
				 */
				String licensePlateString  = "";
				for (LicensePlateMsg msg : user.getLicensePlateList())
				{
					licensePlateString += ";" + msg.getLicensePlateNumber();
				}
				LicensePlateJsonObject msg = new LicensePlateJsonObject();
				msg.setLicensePlateList(licensePlateString);
				/** JSONify the object and bundle it with the response*/
				ObjectMapper objectMapper = new ObjectMapper();
				String json = objectMapper.writeValueAsString(msg);
				response.setContentType("application/json");
				PrintWriter out = response.getWriter();
				out.write(json);
				out.close();
			}
			else
			{
				createHTMLSuccessResponse(response, user);
			}
		}
	}

	private void createHTMLSuccessResponse(
			HttpServletResponse response, UserMsg user)
	{
		try {
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
			/** License payment tables */
			for (LicensePlateMsg msg : user.getLicensePlateList())
			{
				printParamRow(out,"licensePlate", msg.getLicensePlateNumber());
			}
			/** End table */
			out.println("</TABLE>");

			/** License payment tables */
			for (LicensePlateMsg msg : user.getLicensePlateList())
			{
				printLicensePlateInfo(out, msg);
			}

			/** End HTML page */
			out.println("\n</BODY></HTML>");
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void printLicensePlateInfo(
			PrintWriter out, LicensePlateMsg msg) {

		for (PaymentMsg payment: msg.getPaymentList())
		{
			/** Print parameters as rows */
			out.println("<H1 ALIGN=CENTER>" + msg.getLicensePlateNumber() + "</H1>\n" +
					"<TABLE BORDER=1 ALIGN=CENTER>\n" +
					"<TR BGCOLOR=\"#FFAD00\">\n" +
					"<TH>Parameter Name<TH>Parameter Value(s)");
			/** Print parameters as rows */
			printParamRow(out,"amountPaid", ""+payment.getAmountPaid());
			printParamRow(out,"startTimestamp", ""+payment.getStartTimestamp());
			printParamRow(out,"endTimeStamp", ""+payment.getEndTimeStamp());
			/** End table */
			out.println("</TABLE>");
		}
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

