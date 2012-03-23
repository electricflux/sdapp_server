<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type"
			content="text/html; charset=windows-1256">
		<title>Login Page</title>
	</head>

<body>
	<form name=f1 action="LoginServlet">
	    Please enter your username/email-id <input type="text" name="un" /><br>
	    Please enter your password <input type="text" name="pw" /> 
	<input type=button value="Login" 
	       onclick="if (f1.un.value.length==0 || f1.pw.value.length==0) alert('Please fill valid values in fields'); else f1.submit();">
	</form>
</body>
</html>
