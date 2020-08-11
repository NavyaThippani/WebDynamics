<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Admin's Page</title>
<script type="text/javascript">
	function approveOrReject() {
		document.loginForm.method = "POST";
		document.loginForm.action = "approveOrReject.do";
		document.loginForm.submit();
	}
</script>
<link href="css/menu.css" rel="stylesheet" type="text/css" />
</head>
<body background="images/background1.jpg">
	<%@include file="header.html"%>
	<center>
		<div id="hmenu">
			<ul>
				<li><a href="addUser">Add User</a></li>
				<li><a href="addContent.html">Write Content</a></li>
				<li><a href="statusContent.html?status=pending">Pending</a></li>
				<li><a href="statusContent.html?status=reject">Rejected</a></li>
				<li><a href="statusContent.html?status=approve">Approved</a></li>
				<li><a href="listUsers">List of Users</a></li>
				<li><a href="logout.html">Logout</a></li>

			</ul>
		</div>
	</center>
	<div style="text-align: center">
		<br>
		<h2>Welcome Admin..!!!</h2>
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
					<th class="thstyle">Category</th>
					<td class="trstyle"><c:out value="${content.category}" /></td>
				</tr>
				<tr>
					<c:if test="${!empty content.approvedBy}">
						<th class="thstyle">Approved By</th>
						<td class="trstyle"><c:out value="${content.approvedBy}" /></td>
					</c:if>
				</tr>
				<tr>
					<th class="thstyle">Status</th>
					<td class="trstyle"><c:out value="${content.status}" /></td>
				</tr>
				<tr>
					<th class="thstyle">Approve/ Reject</th>
					<td class="trstyle"><a
						href="approveOrReject?value=approve&id=${content.contentId}">Approve</a>
						| <a href="approveOrReject?id=${content.contentId}&value=reject">Reject</a></td>
				</tr>
				<tr>
					<th class="thstyle">Edit/Delete</th>
					<td class="trstyle"><a
						href="editContent.html?id=${content.contentId}">Edit</a> | <a
						href="deleteContent.html?id=${content.contentId}">Delete</a></td>
				</tr>
			</table>
			<br>
			<div style="text-align: center">
				<img src="images/ending.jpg" height=40 width=690 />
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