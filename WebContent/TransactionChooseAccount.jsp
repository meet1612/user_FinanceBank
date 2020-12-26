<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

<title>Choose Account for Transaction</title>
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">

<style>

.div1{
width:50%;
border:1px solid black;
margin-left:350px;
margin-top:100px;

box-shadow: 5px 5px 5px 5px #888888;
}
td{
text-align:center;
}

input[type="submit"]{
	width:100%;
}
</style>
</head>
<body>
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
	<%
		String operation = (String) request.getAttribute("operation");
	%>
	<%@include file="HeaderFooter.jsp" %><br>
	<%@include file="Session.jsp" %>
	
	<form action="TransactionController?operation=<%=operation%>" method="post">
	<div class="div1">
	
<div class="container" >
              
  <table class="table">
    <thead>
      <tr>
				<th>Select Your Account</th>
				<th></th>
			</tr>
    </thead>

	<tbody>
	<tr>
	
	<td colspan="2">
		<select class="custom-select" style="width:500px;" name="accounts" id="accounts">
			<c:forEach var="account" items="${list }">
				<option value="${account.ws_acct_id }">${account.ws_acct_id }</option>
			</c:forEach>
		</select>
	</td> 
	</tr>
	<tr>
		<td colspan="2">
		<input type="submit" class="btn btn-dark" value="Choose">
		</td>
	</tr>
	
	</tbody>
  </table>
</div>
</div>
</form>
	
</body>
</html>