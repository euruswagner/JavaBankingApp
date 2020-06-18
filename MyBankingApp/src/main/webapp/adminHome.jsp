<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/master.css" />
<title>Admin Home Page</title>
</head>
<body>
	<h1>Welcome ${currentUserName}! You have the full powers of an
		Admin. Remember with great power comes great responsibility.</h1>
	<% if (request.getAttribute("successMessage") != null) { %>
	<div class="success-message" id="home-success-message">
		<p>
			<b><%=request.getAttribute("successMessage")%></b>
		</p>
	</div>
	<% } session.setAttribute("sucessMessage", null); %>

	<% if (request.getAttribute("errorMessage") != null) { %>
	<div class="error-message" id="home-error-message">
		<p>
			<b><%=request.getAttribute("errorMessage")%></b>
		</p>
	</div>
	<% } session.setAttribute("errorMessage", null); %>
	<br />

	<% if (request.getAttribute("nonAdminUsers") != null) { %>
	<c:forEach items="${nonAdminUsers}" var="user">
		<h1>${user.fullName()}- ${user.getUserId()}</h1>
		<table>
			<tr>
				<th>Account Number:</th>
				<th>Account Nickname:</th>
				<th>Balance:</th>
				<th>Account Status:</th>
				<th>Account Type:</th>
				<th>Activate:</th>
				<th>Add User:</th>
			</tr>
			<c:forEach items="${accountsById.get(user.getUserId())}"
				var="account">
				<tr>
					<td>${account.getAccountId()}</td>
					<td>${account.getNickname()}</td>
					<td>${account.getBalance()}</td>
					<td>${account.getAccountStatus()}</td>
					<td>${account.getAccountType()}</td>
					<td>
						<form method="post" action="activateAccountServlet">
							<input type="hidden" name="accountId" value="${account.getAccountId()}">         
							<input type="submit" value="Activate!">
						</form>
					</td>
					<td>
						<form method="post" action="addUserToAccountServlet">
							<table>
								<tr>
									<td>User Account ID:</td>
									<td><input type="number" name="userId"></td>
									<td>
										<input type="hidden" name="accountId" value="${account.getAccountId()}">         
										<input type="submit" value="Add User">
									</td>
								</tr>
							</table>
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:forEach>
	<% } %>
	<br />
	<form method="get" action="logoutServlet">
		<input type="submit" value="Log Out!">
	</form>
</body>
</html>
<script>
	window.onload = function() {
    	window.setTimeout(fadeout, 5000); 
	}

	function fadeout() {
		document.getElementById('home-success-message').style.opacity = '0';
		document.getElementById('home-error-message').style.opacity = '0';
	}
</script>