package com.socketmachines.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DecimalFormat;

/**
 * Servlet implementation class SocketQuoteServlet
 */
public class SocketQuoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SocketQuoteServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String socketType = request.getParameter("socketType");
		String quantityParam = request.getParameter("quantity");
		long requiredQuantity = 0;
		boolean isValidDataEntered = true;
		try {
			requiredQuantity = Integer.parseInt(quantityParam);
		} catch (NumberFormatException e) {
			isValidDataEntered = false;
		}
		if (requiredQuantity < 0) {
			isValidDataEntered = false;
		}
		response.setContentType("text/html");
		response.getWriter().println("<html><body>");
		if (isValidDataEntered) {
			double totalQuote = calculateQuote(socketType, requiredQuantity);
			String formattedTotalQuote = new DecimalFormat("#,###.00").format(totalQuote);
			response.getWriter().println("<h1>You have selected Socket Type: " + socketType + "</h1>");
			response.getWriter().println(
					"<h1>The total cost for " + requiredQuantity + " unit(s) is: $" + formattedTotalQuote + "</h1>");

		} else {
			response.getWriter()
					.println("<h1 style='color:red;'>Invalid Quantity Entered!!  Please enter Valid value. </h1>");
		}
		response.getWriter().println("</body></html>");
	}

	private double calculateQuote(String socketType, long requiredQuantity) {
		int socketPrice = 0;
		if ("TypeA".equals(socketType)) {
			socketPrice = 25;
		} else if ("TypeB".equals(socketType)) {
			socketPrice = 50;
		} else if ("TypeC".equals(socketType)) {
			socketPrice = 100;
		}
		return socketPrice * requiredQuantity;

	}

}
