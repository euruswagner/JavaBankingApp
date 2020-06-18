<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/master.css"/>
<title>Create Account Page</title>
</head>
<body>
	<h1>Create an Account!</h1>
	<form method="post" action="createAccountServlet">
		<% if (request.getAttribute("errorMessage") != null) { %>
			<div class="error-message">
    			<p>
        		<b><%=request.getAttribute("errorMessage")%></b>
    			</p>
			</div>
		<% } session.setAttribute("errorMessage", null); %>
		<table>
			<tr>
				<td>Nickname:</td>
				<td><input type="text" name="nickname"></td>
			</tr>
			<tr>
				<td>Account Type:</td>
				<td>
					<select name="accountType">
    					<option value="Checking">Checking</option>
    					<option value="Savings">Savings</option>
  					</select>
  				</td>
			</tr>
			<tr>
				<td />
				<td><input type="submit" value="Create!"></td>
			</tr>
		</table>
	</form>
</body>
</html>