<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
<title>Account Statement</title>
<style>
.des_table{
	display:block;
	width:100%;
}
.mob_table{
	display:none;
}

.button {
 
 float:right;
 margin-right:10px;
 margin-bottom:10px;
  display: inline-block;
  padding: 12px 12px 10px;
  border: 0;
  border-radius: 3px;
  background: #bf4d28;
  text-decoration: none;
  transition: all 1.2s ease-in-out;
  
  i::after {
    content: "\f019";
    font-size: 40px;
    color: #fff;
  }
  
  &:hover {
    animation: pulse 0.2s 2 both;
  }
  
  &.loading {
    i {
      animation: loading 2s infinite linear;
      
      &::after {
        content: "\f1ce";
      }        
    }
  }
  
  &.success {
    border-radius: 50%;
    background: #80bca3;
    
    i::after {
      content: "\f00c";
      color: transparent;
      animation: change-icon 1s 0.6s linear both;
    }
  }
}
@media screen and (max-width: 600px) {
 .des_table{
    display:none;
  }
 
  
  .mob_table{
  	display:block;
  }
}

</style>
</head>
<body>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
	<%@include file="HeaderFooter.jsp" %><br>
	<%@include file="Session.jsp" %>
	
	<div class="des_table">

		<button class="button" ><a href="TransactionController?operation=downloadPDF&accid=${accid}" style="color:white;text-decoration:none" >Download PDF</a></button>
		
	<table class="table">
		<thead>
			<th>Transaction ID</th>
			<th>Description</th>
			<th>Date (YYYY-MM-DD)</th>
			<th>Amount</th>
		</thead>

		<c:forEach var="transaction" items="${statementList }">
			<tr>
				<td>${transaction.ws_trxn_id }</td>
				
				
				
				<td role='cell'>
				<c:set var = "status" scope = "session" value = "${transaction.ws_op } "/>
				<c:set var = "statusD" scope = "session" value = "D"/>
				<c:set var = "statusW" scope = "session" value = "W"/>
				<c:set var = "statusT" scope = "session" value = "T"/>
				
					<c:if test="${fn:substring(statusD,0,1) == fn:substring(status, 0,1)}">
					 <c:out value="Deposit"></c:out>
					</c:if>
				
					<c:if test="${fn:substring(statusW,0,1) == fn:substring(status, 0,1)}">
					 <c:out value="Withdraw"></c:out>
					</c:if>
					
					<c:if test="${fn:substring(statusT,0,1) == fn:substring(status, 0,1)}">
					 <c:out value="Transfer"></c:out>
					</c:if>
				
				</td>
				
				
				
				<td>${transaction.ws_trxn_date }</td>
				<td>${transaction.ws_amt}</td>
			</tr>
		</c:forEach>
	</table>
	</div>
	
	
	<div class="mob_table">

		<button class="button" ><a href="TransactionController?operation=downloadPDF&accid=${accid}" style="color:white;text-decoration:none" >Download PDF</a></button>
		
	<table class="table">
		<thead>
			<th>Transaction ID</th>
			<th>Description</th>
			<th>Date (YYYY-MM-DD)</th>
			<th>Amount</th>
		</thead>

		<c:forEach var="transaction" items="${statementList }">
			<tr>
				<td>${transaction.ws_trxn_id }</td>
				<td>${transaction.ws_op }</td>
				<td>${transaction.ws_trxn_date }</td>
				<td>${transaction.ws_amt}</td>
			</tr>
		</c:forEach>
	</table>
	</div>
	
</body>
</html>