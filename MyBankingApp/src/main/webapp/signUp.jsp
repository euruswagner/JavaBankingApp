<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/master.css"/>
<title>Registration Page</title>
</head>
<body>
	<h1>Sign Up!</h1>
	<form method="post" action="signUpServlet">
		<% if (request.getAttribute("errorMessage") != null) { %>
			<div class="error-message">
    			<p>
        		<b><%=request.getAttribute("errorMessage")%></b>
    			</p>
			</div>
		<% } session.setAttribute("errorMessage", null); %>
		<table>
			<tr>
				<td>User Name:</td>
				<td><input type="text" name="username"></td>
			</tr>
			<tr>
				<td>First Name:</td>
				<td><input type="text" name="firstName"></td>
			</tr>
			<tr>
				<td>Last Name:</td>
				<td><input type="text" name="lastName"></td>
			</tr>
			<tr>
				<td>Email:</td>
				<td><input type="text" name="email"></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type="password" name="password"></td>
			</tr>
			<tr>
				<td />
				<td><input type="submit" value="Sign Up!"></td>
			</tr>
		</table>
	</form>
</body>
</html>