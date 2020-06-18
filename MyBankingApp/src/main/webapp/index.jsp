<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/master.css"/>
<title>First Eurus National Bank</title>
</head>
<body>
	<h1>First Eurus National Bank</h1>
	<% if (request.getAttribute("logoutMessage") != null) { %>
		<div class="success-message" id="index-success-message">
    		<p>
        	<b><%=request.getAttribute("logoutMessage")%></b>
    		</p>
		</div>
	<% } session.setAttribute("logoutMessage", null); %>
	<a href=login.jsp>Sign In</a>
	<br />
	<a href=signUp.jsp>Sign Up</a>
</body>
</html>
<script>
	window.onload = function() {
    	window.setTimeout(fadeout, 5000); 
	}

	function fadeout() {
		document.getElementById('index-success-message').style.opacity = '0';
	}
</script>