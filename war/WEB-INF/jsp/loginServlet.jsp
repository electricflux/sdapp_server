<%@ page contentType="text/html; charset=ISO-8859-1"%>
<html>
<head>
<title>Login Servlet</title>
</head>
<body>
	<h3>User login page</h3>
	<form action="loginSuccessfulServlet" method="post">
		<table>
			<tr>
				<td>User Name</td>
				<td><input type="text" name="username"></td>
			</tr>
			<tr>
				<td><input type="submit" value="Login">&nbsp; <input
					type="reset"></td>
				<td></td>
		</table>
	</form>
</body>
</html>