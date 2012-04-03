<%@ page contentType="text/html; charset=ISO-8859-1"%>
<html>
<head>
<title>Registration Servlet</title>
</head>
<body>
	<h3>User registration page</h3>
	<form action="registerServlet" method="post">
		<table>
			<tr>
				<td>User Name</td>
				<td><input type="text" name="username"></td>
			</tr>
			<tr>
				<td>Device Identifier</td>
				<td><input type="text" name="deviceId"></td>
			</tr>
			<tr>
				<td>Authorization token</td>
				<td><input type="text" name="authToken"></td>
			</tr>
			<tr>
				<td>License Plates (separated by ;)</td>
				<td><textarea rows="5" cols="50" name="license"></textarea></td>
			</tr>
			<tr>
				<td><input type="submit" value="Submit">&nbsp; <input
					type="reset"></td>
				<td></td>
		</table>
	</form>
</body>
</html>