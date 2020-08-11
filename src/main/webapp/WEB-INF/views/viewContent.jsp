<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>All Contents</title>
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
				<c:if test="${empty username}">
					<li><a href="login.html">Login</a></li>
					<li><a href="addUser.html">Sign Up</a></li>
				</c:if>
				<c:if test="${!empty username}">
					<li><a href="myAcct.html?uname=${username}">My Account</a></li>
					<li><a href="addContent.html">Write Content</a></li>
					<li><a href="logout.html">Logout</a></li>
				</c:if>

			</ul>
		</div>
	</center>
	<div style="text-align: center">
		<br>
		<h2>Content</h2>

	</div>
	<c:if test="${!empty contents}">
		<c:forEach items="${contents}" var="content">
			<table class="tablestyle" border="1" align="center" cellpadding="10">

				<tr>
					<th class="thstyle">Title</th>
					<td class="trstyle"><c:out value="${content.contentTitle}" /></td>
				</tr>
				<tr>
					<th class="thstyle">Body</th>
					<td class="trstyle"><textarea rows="1" cols="76" readonly
							class="textareastyle">${content.contentBody}</textarea></td>
				</tr>
				<tr>
					<th class="thstyle">Author</th>
					<td class="trstyle"><c:out value="${content.author}" /></td>
				</tr>
				<tr>
					<c:if test="${role=='admin'}">
						<th class="thstyle">Edit/Delete</th>
						<td class="trstyle"><a
							href="editContent.html?id=${content.contentId}">Edit</a> | <a
							href="deleteContent.html?id=${content.contentId}">Delete</a></td>
					</c:if>
				</tr>

			</table>
			<br>
			<div style="text-align: center">
				<img src="images/ending.jpg" height=40 width=550 />
			</div>
			<br>
		</c:forEach>

	</c:if>
	<c:if test="${empty contents}">
		<div style="text-align: center">
			<br>
			<h3>There is no Content present in this section to read...!</h3>

		</div>
	</c:if>
</body>
</html>