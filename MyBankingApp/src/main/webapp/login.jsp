<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/master.css"/>
<title>Login Page</title>
</head>
<body>
	<h1>Welcome! Please login:</h1>
	<% if (request.getAttribute("errorMessage") != null) { %>
		<div class="error-message" id="login-error-message">
    		<p>
        	<b><%=request.getAttribute("errorMessage")%></b>
    		</p>
		</div>
	<% } session.setAttribute("errorMessage", null); %>
	<form method="post" action="loginServlet">
		<table>
			<tr>
				<td>User Name:</td>
				<td><input name="username" /></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input name="password" type="password" /></td>
			</tr>
			<tr>
				<td />
				<td><input type="submit" value="Login" /></td>
		</table>
	</form>
</body>
</html>
<script>
	window.onload = function() {
    	window.setTimeout(fadeout, 5000); 
	}

	function fadeout() {
		document.getElementById('login-error-message').style.opacity = '0';
	}
</script>