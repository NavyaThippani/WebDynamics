<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Registration</title>
<script type="text/javascript">
	function editUser() {
		document.userForm.method = "POST";
		document.userForm.action = "editUser?value=myacct";
		document.userForm.submit();
	}
</script>
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
				<li><a href="addContent">Write Content</a></li>
				<c:if test="${role=='admin'}">
					<li><a href="adminPage">Admin Page</a></li>
				</c:if>
				<li><a href="logout.html">Logout</a></li>

			</ul>
		</div>
	</center>
	<div style="text-align: center">
		<br>
		<h3>Your User account details:</h3>

		<c:if test="${!empty status}">
			<h3>"${status}" !!!</h3>
		</c:if>
	</div>
	<form:form name="userForm">

		<table class="tablestyleSmall" border="1" align="center"
			cellpadding="10">
			<tr>
				<c:if test="${message=='update'}">
					<form:input path="userId" value="${user.userId}" type="hidden"
						readonly="true" style="height:40% padding:20px 10px" />
				</c:if>
			</tr>
			<tr>
				<td><form:label path="userName">User Name</form:label></td>
				<td><form:input path="userName" value="${user.userName}"
						readonly="true" /></td>
			</tr>
			<tr>
				<td><form:label path="userPassword">Password</form:label></td>
				<td><form:input type="password" path="userPassword"
						value="${user.userPassword}" /></td>
			</tr>
			<tr>
				<td><form:label path="name">Name</form:label></td>
				<td><form:input path="name" value="${user.name}" /></td>
			</tr>
			<tr>
				<td><form:label path="phone">Phone</form:label></td>
				<td><form:input path="phone" value="${user.phone}" /></td>
			</tr>

			<tr>
				<td><form:label path="email">Email</form:label></td>
				<td><form:input path="email" value="${user.email}" /></td>
			</tr>
			<tr>
				<td align="center" colspan="2"><input type="submit"
					value="Update" onclick="editUser();" /></td>
			</tr>
		</table>
	</form:form>
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
					<th class="thstyle">Update / Delete</th>
					<td class="trstyle"><a
						href="editContent.html?id=${content.contentId}">Edit</a> | <a
						href="deleteContent.html?id=${content.contentId}">Delete</a></td>
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