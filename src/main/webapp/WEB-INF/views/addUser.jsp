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
	function add() {
		  if(validate())
		        {
			    document.userForm.method = "POST";
				document.userForm.action = "addUser.do";
				document.userForm.submit();
		        }
		      else
		        {
		        return false;
		        }		
	}
	function validate(){
		var phonePattern = /^\d{10}$/;
		var phone = document.getElementById("phoneNo").value;
		var email = document.getElementById("emailVal").value;
		var name = document.getElementById("name").value;
		var passw = document.getElementById("passw").value;
		var uname = document.getElementById("uname").value;

		if(uname==""){
			  alert("Please enter User Name");
			  return false;
		  }else if(passw==""){
			  alert("Please enter Password");
			  return false;
		  }else if(name==""){
			  alert("Please enter Name");
			  return false;
		  }else if(!phone.match(phonePattern)){
			  alert("Please enter proper phone number");
			  return false;
		  }else if(!(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email))){
			  alert("Please enter proper email");
			  return false;
		  }
		  return true;
	}
	function update() {
		 if(validate())
	        {
		    document.userForm.method = "POST";
			document.userForm.action = "editUser.do";
			document.userForm.submit();
	        }
	      else
	        {
	        return false;
	        }		
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
				<c:if test="${!empty username}">
				<li><a href="myAcct.html?uname=${username}">My Account</a></li>
				</c:if>
				<c:if test="${role=='admin'}">
					<li><a href="adminPage">Admin Page</a></li>
				</c:if>
				<li><a href="login.html">Login</a></li>
			</ul>
		</div>
	</center>
	<div style="text-align: center">
		<c:if test="${!empty status}">
			<h3>"${status}" !!!</h3>
		</c:if>

		<br>
		<c:if test="${message=='update'}">
			<h3>Update your details</h3>
		</c:if>
		<c:if test="${message!='update'}">
			<h3>Please fill in your details below</h3>
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
				<td><form:input path="userName" id="uname" value="${user.userName}"/></td>
			</tr>
			<tr>
				<td><form:label path="userPassword">Password</form:label></td>
				<td><form:input type="password" path="userPassword" id="passw"
						value="${user.userPassword}"/></td>
			</tr>
			<tr>
				<td><form:label path="name">Name</form:label></td>
				<td><form:input path="name" value="${user.name}" id="name"/></td>
			</tr>
			<tr>
				<td><form:label path="phone">Phone</form:label></td>
				<td><form:input path="phone" id="phoneNo" value="${user.phone}"/></td>
			</tr>

			<tr>
				<td><form:label path="email">Email</form:label></td>
				<td><form:input path="email" id="emailVal" value="${user.email}"/></td>
			</tr>
			<tr>
				<c:if test="${role=='admin'}">
					<td><form:label path="role">Role</form:label></td>
					<td><form:select path="role">
							<form:option value="NONE" label="--- Select ---" />
							<form:option value="User" />
							<form:option value="Admin" />
							<!--   items="${user.role}" />-->
						</form:select></td>
				</c:if>
			</tr>
			<tr>
				<c:if test="${message=='update'}">
					<td align="center" colspan="2"><input type="submit"
						value="Update" onclick="editUser();" /></td>
				</c:if>
				<c:if test="${message!='update'}">
					<td align="center" colspan="2"><input type="submit"
						value="Submit" onclick="add(); return false;" /></td>
				</c:if>
			</tr>
		</table>

	</form:form>

</body>
</html>