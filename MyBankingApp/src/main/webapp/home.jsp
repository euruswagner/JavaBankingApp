<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/master.css" />
<title>User Home Page</title>
</head>
<body>
	<h1>Welcome ${currentUserName}!</h1>
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
	<% if (request.getAttribute("usersAccounts") != null) { %>
	<h1>Your Accounts</h1>
	<table>
		<tr>
			<th>Account Number:</th>
			<th>Account Nickname:</th>
			<th>Balance:</th>
			<th>Account Status:</th>
			<th>Account Type:</th>
		</tr>
		<c:forEach items="${usersAccounts}" var="account">
			<tr>
				<td>${account.getAccountId()}</td>
				<td>${account.getNickname()}</td>
				<td>${account.getBalance()}</td>
				<td>${account.getAccountStatus()}</td>
				<td>${account.getAccountType()}</td>
			</tr>
		</c:forEach>
	</table>

	<h1>Deposit Money</h1>
	<form method="post" action="accountDepositServlet">
		<table>
			<tr>
				<td>Account Number:</td>
				<td><input type="number" name="accountId" /></td>
			</tr>
			<tr>
				<td>Deposit Amount:</td>
				<td><input type="number" name="depositAmount" /></td>
			</tr>
			<tr>
				<td />
				<td><input type="submit" value="Deposit!" /></td>
		</table>
	</form>
	
	<h1>Withdraw Money</h1>
	<form method="post" action="accountWithdrawServlet">
		<table>
			<tr>
				<td>Account Number:</td>
				<td><input type="number" name="accountId" /></td>
			</tr>
			<tr>
				<td>Withdraw Amount:</td>
				<td><input type="number" name="withdrawAmount" /></td>
			</tr>
			<tr>
				<td />
				<td><input type="submit" value="Withdraw!" /></td>
		</table>
	</form>
	
	<h1>Transfer Money</h1>
	<form method="post" action="accountTransferServlet">
		<table>
			<tr>
				<td>From Account:</td>
				<td><input type="number" name="fromAccountId" /></td>
			</tr>
			<tr>
				<td>To Account:</td>
				<td><input type="number" name="toAccountId" /></td>
			</tr>
			<tr>
				<td>Transfer Amount:</td>
				<td><input type="number" name="transferAmount" /></td>
			</tr>
			<tr>
				<td />
				<td><input type="submit" value="Transfer!" /></td>
		</table>
	</form>
	<% } %>
	<br />
	<a href=createAccount.jsp>Create Account</a>
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