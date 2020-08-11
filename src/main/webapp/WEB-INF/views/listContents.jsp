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
				<li><a href="addContent.html">Write Content</a></li>
				<li><a href="logout.html">Logout</a></li>
			</ul>
		</div>
	</center>
	<div style="text-align: center">
		<br>
		<h1>All Contents</h1>
	</div>
	<c:if test="${!empty contents}">
		<table align="center" border="1" style="clear: both">
			<tr>
				<th>Title</th>
				<th>Body</th>
				<th>Author</th>
				<th>Category</th>
				<th>Approved By</th>
				<th>Update/Delete</th>
			</tr>

			<c:forEach items="${contents}" var="content">
				<tr>
					<td><c:out value="${content.contentTitle}" /></td>
					<td><c:out value="${content.contentBody}" /></td>
					<td><c:out value="${content.author}" /></td>
					<td><c:out value="${content.category}" /></td>
					<td><c:out value="${content.approvedBy}" /></td>
					<td align="center"><a
						href="editContent.html?id=${content.contentId}">Edit</a> | <a
						href="deleteContent.html?id=${content.contentId}">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>