<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>All Users</title>
<link href="css/menu.css" rel="stylesheet" type="text/css" />
</head>
<body background="images/background1.jpg">
	<%@include file="header.html"%>
	<center>
		<div id="hmenu">
			<ul>
				<li><a href="addUser">Add User</a></li>
				<li><a href="addContent.html">Add Content</a></li>
				<li><a href="listContents.html">List of Contents</a></li>
				<li><a href="listUsers">List of Users</a></li>
				<li><a href="login.html">Login</a></li>
			</ul>
		</div>
	</center>
	<div style="text-align: center">
		<br>
		<h1>List Users</h1>

		<h3>
			<a href="addUser.html">Add New User</a>
		</h3>
	</div>
	<c:if test="${!empty users}">
		<table class="tablestyleSmall" border="1" align="center"
			cellpadding="10">
			<tr>
				<th>User Name</th>
				<th>Password</th>
				<th>Name</th>
				<th>Phone</th>
				<th>Email</th>
				<th>Role</th>
				<th>Update/Delete</th>
			</tr>

			<c:forEach items="${users}" var="user">
				<tr>
					<td><c:out value="${user.userName}" /></td>
					<td><c:out value="${user.userPassword}" /></td>
					<td><c:out value="${user.name}" /></td>
					<td><c:out value="${user.phone}" /></td>
					<td><c:out value="${user.email}" /></td>
					<td><c:out value="${user.role}" /></td>
					<td align="center"><a href="editUser.html?id=${user.userId}">Edit</a>
						| <a href="deleteUser.html?id=${user.userId}">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>