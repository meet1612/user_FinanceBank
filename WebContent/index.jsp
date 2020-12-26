<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
<title>Finance Bank</title>
</head>
<body>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>

<%-- <jsp:include page="Session.jsp"/> --%>
<%-- <% 
	if(session.getAttribute("login") == null)
	{
		response.sendRedirect("login.jsp");
	}
%> --%>
<%@include file="Session.jsp" %>
<%@include file="HeaderFooter.jsp" %>
<h1 align="center" style="margin-top: 20px;">Welcome to Finance Banking System</h1>

<!--  Customer related Operation -->
<!-- <a href="CustomerController?operation=create">Registration of Customer</a><br><br>
<a href="CustomerController?operation=update">Update Customer</a><br><br>
<a href="CustomerController?operation=delete">Delete Customer</a><br><br>
<a href="CustomerController?operation=search">Search Customer</a><br><br>
<a href="CustomerController?operation=status">Customer Status</a><br><br>


<!--  Account related Operation  -->
<!-- <a href = "AccountController?operation=create">Account Registration</a><br><br>
<a href = "AccountController?operation=delete">Account Delete</a><br><br>
<a href = "AccountController?operation=status">Account Status</a><br><br>
<a href = "AccountController?operation=search">Account Search</a><br><br>

<!--  Transaction related Operation  -->
<!-- <a href="TransactionController?operation=withdraw">Transaction : Withdraw Money</a><br><br>
<a href="TransactionController?operation=deposit">Transaction : Deposit Money</a><br><br>
<a href="TransactionController?operation=transfer">Transaction : Transfer Money</a><br><br>-->

</body>
</html>