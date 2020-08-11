<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Shopping</title>

<script type="text/javascript">
	function validate() {
		if (document.getElementById("username").value.trim() == ""
				|| document.getElementById("password").value.trim() == "") {
			alert("Please enter the username/password");
			document.getElementById("username").focus();
			return false;
		}
		document.loginForm.method = "POST";
		document.loginForm.action = "login.do";
		document.loginForm.submit();
	}
	function newUser() {
		document.loginForm.method = "GET";
		document.loginForm.action = "addUser.do";
		document.loginForm.submit();
	}
</script>
<style type="text/css">
.box {
	width: 300px;
	height: 350px;
	background-color: #d9d9d9;
	position: fixed;
	margin-left: -150px; /* half of width */
	margin-top: -50px; /* half of height */
	top: 50%;
	left: 50%;
}
</style>
<link href="css/menu.css" rel="stylesheet" type="text/css" />
</head>
<body background="images/background1.jpg">
	<%@include file="header.html"%>
	<center>
		<div id="hmenu">
			<ul>
				<li><a href="viewContent?value=technical">Technical Content</a></li>
				<li><a href="viewContent?value=general">General Content</a></li>
				<li><a href="viewContent?value=protected">Protected Content</a></li>
				<li><a href="addUser">Sign Up</a></li>
			</ul>
		</div>
	</center>
	<div>
		<center>
			<c:if test="${!empty status}">
				<h3>
					<br>"${status}" !!!<br>
				</h3>
			</c:if>
			<c:if test="${protected=='login'}">
				<center style="color: red">
					<br>Please login to view the protected content..!<br>
				</center>
			</c:if>
			<c:if test="${!empty message}">
				<br>
				<span style="color: red">You are successfully logged out..!<br></span>
			</c:if>
		</center>
		<div style="text-align: center">
			<br>
			<img src="images/user.jpg" height=150 width=150 />
		</div>
		<form:form action="login.do" name="loginForm" method="POST">
			<center>
				<br>
				<table style="text-align: center; clear: both">
					<tr>
						<td><label style="font-size: 70; padding: 60">User
								Name</label></td>
						<td><input type="text" id="username" name="username"></input></td>
					</tr>
					<tr>
						<td><label>Password</label></td>
						<td><input type="password" id="password" name="password"></input></td>
					</tr>
					<tr>
						<td></td>
					</tr>
					<tr>
						<td colspan=2><input type="button" value="Login"
							onclick="validate();" class="buttonStyle"></input></td>

					</tr>
				</table>
			</center>
		</form:form>
	</div>
	<!--  </div> -->
</body>
</html>