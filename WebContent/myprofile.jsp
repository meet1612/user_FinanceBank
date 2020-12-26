<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
 <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <!--  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>-->
	<title>Display Customer</title>
	<style>
	
	table{
		border:1px solid grey;
		box-shadow: 5px 5px 5px 5px #888888;
		height:auto;
		width:40%;
	}
	
	td{
		cell-spacing:0;
	}
	
	</style>


	  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	  <link rel="stylesheet" href="/resources/demos/style.css">
	  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>

<body>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>

	<%-- SSN : ${customerBean.ws_ssn}
	<br> ID : ${customerBean.ws_cust_id}
	<br> Name : ${customerBean.ws_name}
	<br> Address : ${customerBean.ws_adrs}
	<br> Age : ${customerBean.ws_age}
	<br> --%>

	<%@include file="HeaderFooter.jsp" %><br>
	<%@include file="Session.jsp" %>
	
	
		<div class="container">
              
  <table class="table" style="width:40%" align="center">
    <thead>
     <tr>
     		<th></th>
			<th>Profile</th>	
				
	</tr>
    </thead>
    <tr>
			<th>SSN</th>
			<td>${customerBean.ws_ssn}</td>
		</tr>
		<tr>
			<th>Customer Id</th>
			<td>${customerBean.ws_cust_id}</td>
		</tr>
		<tr>
			<th>Email Id</th>
			<td>${customerBean.ws_email_id}</td>
		</tr>
		<tr>
			<th>Name</th>
			<td>${customerBean.ws_name}</td>
			
		</tr>
		<tr>
			<th>Address</th>
			<td>${customerBean.ws_adrs}</td>
		</tr>
		<tr>	
			<th>City</th>
			<td>${customerBean.ws_city}</td>
		</tr>
		<tr>	
			<th>State</th>
			<td>${customerBean.ws_state}</td>
		</tr>
		<tr>
			<th>Age</th>
			<td>${customerBean.ws_age}</td>
				
		</tr>
		<tr>	
			<th>Status</th>
			<td role='cell'>
				<c:set var = "status" scope = "session" value = "${customerBean.ws_status} "/>
				<c:set var = "statusA" scope = "session" value = "A"/>
				<c:set var = "statusI" scope = "session" value = "I"/>
				
					<c:if test="${fn:substring(statusA,0,1) == fn:substring(status, 0,1)}">
					 <c:out value="Active"></c:out>
					</c:if>
				
					<c:if test="${fn:substring(statusI,0,1) == fn:substring(status, 0,1)}">
					 <c:out value="Inactive"></c:out>
					</c:if>
				
				</td>
		</tr>
    <tbody>
    
			
  </table>
</div>

		
		
</body>
</html>