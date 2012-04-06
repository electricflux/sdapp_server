package com.sdapp.server;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdapp.domain.LicensePlateMsg;
import com.sdapp.domain.PaymentMsg;
import com.sdapp.domain.UserMsg;
import com.sdapp.logger.SdLogger;
import com.sdapp.persistencemanager.DAO;
import com.sdapp.server.utils.Utils;

/**
 * Servlet implementation class LoginServlet
 */
public class SubmitPaymentServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SdLogger.getInstance().getLogger().info("Get on registerServlet");
		getServletContext().getRequestDispatcher("/WEB-INF/jsp/submitPaymentServlet.jsp").forward(req, resp);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		SdLogger.getInstance().getLogger().info("Post on SubmitPaymentServlet");

		/**
		 * Extract and parse the parameters
		 */
		Float amountPaid;
		Long startTimestamp, endTimestamp, parkingSpotId;
		boolean isDevice = false;
		try {
			amountPaid = Float.parseFloat(
					request.getParameter("amountPaid"));
			startTimestamp = Long.parseLong(
					request.getParameter("startTimestamp"));
			endTimestamp = Long.parseLong(
					request.getParameter("endTimestamp"));
			parkingSpotId= Long.parseLong(
					request.getParameter("parkingSpotId"));
			/** parse isDevice field */
			String isDeviceString = 
					request.getParameter("isDevice");
			if (isDeviceString != null && isDeviceString.length() > 0)
				isDevice = Boolean.parseBoolean(isDeviceString);
		} catch (Exception e) {
			SdLogger.getInstance().getLogger().info(e.getMessage());
			handleError(response,isDevice);
			return;
		}
		String license = request.getParameter("licensePlateNumber");

		/** Clean up white-space from license plate string */
		license= license.replaceAll("\\s+", "");
		license = license.toLowerCase();

		/** Check if license object exists */
		LicensePlateMsg licensePlateMsg = DAO.getLicensePlateMsg(license, false);

		/** If parameters are invalid, fail the request */
		if ((licensePlateMsg == null) || 
				(amountPaid == null) || 
				(endTimestamp == null) ||
				(startTimestamp == null) ||
				(parkingSpotId == null))
		{
			handleError(response,isDevice);
			return;
		}

		/**
		 * Populate the payment message
		 */
		PaymentMsg payment  = new PaymentMsg();
		payment.setParkingSpotId(parkingSpotId);
		payment.setAmountPaid(amountPaid);
		payment.setStartTimestamp(startTimestamp);
		payment.setEndTimeStamp(endTimestamp);
		payment.setLicensePlateString(
				licensePlateMsg.getLicensePlateNumber());
		boolean result = DAO.savePaymentMsg(payment);

		/** 
		 * If its device and persistence of the payment failed, 
		 * return a 402 
		 */
		if ((false == result) && 
				isDevice)
		{
			handleError(response,isDevice);
			return;
		}

		/** Create the response */
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String title = "Payment posted";
		out.println(ServletUtilities.headWithTitle(title) +
				"<BODY BGCOLOR=\"#FDF5E6\">\n" +
				"<H1 ALIGN=CENTER>" + title + "</H1>\n" +
				"<TABLE BORDER=1 ALIGN=CENTER>\n" +
				"<TR BGCOLOR=\"#FFAD00\">\n" +
				"<TH>Parameter Name<TH>Parameter Value(s)");
		/** Print parameters as rows */
		Utils.printParamRow(out,"parkingSpotId", 
				""+payment.getParkingSpotId());
		Utils.printParamRow(out,"startTimestamp", 
				""+payment.getStartTimestamp());
		Utils.printParamRow(out,"endTimestamp", 
				""+payment.getEndTimeStamp());
		Utils.printParamRow(out,"amountPaid", 
				""+payment.getAmountPaid());
		Utils.printParamRow(out,"license", 
				""+payment.getLicensePlateString());
		/** End table */
		out.println("</TABLE>\n</BODY></HTML>");
	}
	
	private void handleError(
			HttpServletResponse response, boolean isDevice)
	{
		if (!isDevice)
			Utils.printPageWithTitle(
					"Payment submission failure", 
					response);
		else
			/** Invalid parameters */
			response.setStatus(400);
	}
}

