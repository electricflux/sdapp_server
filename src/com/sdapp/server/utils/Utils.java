package com.sdapp.server.utils;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.sdapp.logger.SdLogger;
import com.sdapp.server.ServletUtilities;

public class Utils {
	
	public static void printPageWithTitle(
			String title, HttpServletResponse response)
	{
		try{
		/** Create the response */
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println(ServletUtilities.headWithTitle(title) +
				"<BODY BGCOLOR=\"#FDF5E6\">\n" +
				"<H1 ALIGN=CENTER>" + title + "</H1>\n" +
				"<TABLE BORDER=1 ALIGN=CENTER>\n");
		/** End table */
		out.println("</TABLE>\n</BODY></HTML>");
		} catch (Exception e) {
			SdLogger.getInstance().getLogger().info(e.getMessage());
		}
	}
	
	public static void printParamRow(PrintWriter out, String name, String value)
	{
		out.println("<TR><TD>"+name+"\n<TD>");
		if (value.length() == 0)
			out.print("<I>No Value</I>");
		else
			out.print(value);
	}

}
