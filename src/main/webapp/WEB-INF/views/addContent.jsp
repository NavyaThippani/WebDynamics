<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Content Writing</title>
<script type="text/javascript">
	function addContent() {
		document.contentForm.method = "POST";
		document.contentForm.action = "addContent.do";
		document.contentForm.submit();
	}
	function editContent() {
		document.contentForm.method = "POST";
		document.contentForm.action = "editContent.do";
		document.contentForm.submit();
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
				<li><a href="myAcct.html?uname=${username}">My Account</a></li>
				<c:if test="${role=='admin'}">
					<li><a href="adminPage">Admin Page</a></li>
				</c:if>
				<li><a href="logout.html">Logout</a></li>
			</ul>
		</div>
	</center>
	<div style="text-align: center">
		<br>
		<h2>Write Content</h2>

		<c:if test="${!empty status}">
			<h3>"${status}" !!!</h3>
		</c:if>
	</div>
	<form:form name="contentForm"  modelAttribute="content">
		<table class="tablestyle" border="1" align="center" cellpadding="10">
			<tr>
				<c:if test="${message=='update'}">
					<form:input path="contentId" value="${content.contentId}"
						type="hidden" readonly="true" />
				</c:if>
			</tr>
			<tr>
				<td><form:label path="contentTitle">Title</form:label></td>
				<td><form:input path="contentTitle" value="${content.contentTitle}"/></td>
			</tr>
			<tr>
				<td><form:label path="contentBody">Body</form:label></td>	
				<td><form:textarea rows="1" cols="76" class="textareastyle" path="contentBody"/></td>
				</tr>
			<c:if test="${message=='update'}">
				<tr>
					<td><form:label path="author">Author</form:label></td>
					<td><form:input path="author" value="${content.author}"
							readonly="true" /></td>
				</tr>
			</c:if>
			<tr>
				<td><form:label path="category">Category</form:label></td>
				<td><c:if test="${message!='update'}">
						<form:select path="category">
							<form:option value="NONE" label="--- Select ---" />
							<form:option value="Technical" />
							<form:option value="Protected" />
							<form:option value="General" />
						</form:select>
					</c:if> <c:if test="${message=='update'}">
						<form:input path="category" value="${content.category}"
							readonly="true" />
					</c:if></td>
			</tr>
			<c:if test="${message=='update'}">
				<tr>
					<td><form:label path="createdOn">Created On</form:label></td>
					<td><form:input path="createdOn" value="${content.createdOn}"
							readonly="true" /></td>
				</tr>

				<tr>
					<c:if test="${!empty content.modifiedOn}">
						<td><form:label path="modifiedOn">Last Modified On</form:label></td>
						<td><form:input path="modifiedOn"
								value="${content.modifiedOn}" readonly="true" /></td>
					</c:if>
				</tr>
				<tr>
					<c:if test="${!empty content.approvedBy}">
						<td><form:label path="approvedBy">Approved By</form:label></td>
						<td><form:input path="approvedBy"
								value="${content.approvedBy}" readonly="true" /></td>
					</c:if>
				</tr>
				<tr>
					<td><form:label path="status">Status</form:label></td>
					<td><form:input path="status" value="${content.status}"
							readonly="true" /></td>
				</tr>
			</c:if>
			<tr>
				<c:if test="${message=='update'}">
					<td align="center" colspan="2"><input type="submit"
						value="Update" onclick="editContent();" /></td>
				</c:if>
				<c:if test="${message!='update'}">
					<td align="center" colspan="2"><input type="submit"
						value="Submit" onclick="addContent();" /></td>
				</c:if>
			</tr>
		</table>

	</form:form>



</body>
</html>