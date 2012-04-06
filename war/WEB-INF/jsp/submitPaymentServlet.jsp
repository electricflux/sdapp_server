<%@ page contentType="text/html; charset=ISO-8859-1"%>
<html>
<head>
<title>Payment Submission Servlet</title>
</head>
<body>
	<h3>Submit payment</h3>
	<form action="submitPaymentServlet" method="post">
		<table>
			<tr>
				<td>License plate number</td>
				<td><input type="text" name="licensePlateNumber"></td>
			</tr>
			<tr>
				<td>Parking spot id</td>
				<td><input type="text" name="parkingSpotId"></td>
			</tr>
			<tr>
				<td>Start timestamp</td>
				<td><input type="text" name="startTimestamp"></td>
			</tr>
			<tr>
				<td>End timestamp</td>
				<td><input type="text" name="endTimestamp"></td>
			</tr>
			<tr>
				<td>Payment amount</td>
				<td><input type="text" name="amountPaid"></td>
			</tr>
			<tr>
				<td><input type="submit" value="Submit">&nbsp; <input
					type="reset"></td>
				<td></td>
		</table>
	</form>
</body>
</html>