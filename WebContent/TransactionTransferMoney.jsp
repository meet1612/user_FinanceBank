<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
<title>Transfer Money</title>

<style>
table{
border:1px solid black;
border-collapse: collapse;
width:60%;


}
td{
text-align:center;
padding:10px;
}
tr{
border:1px solid black;
}
tr:nth-child(odd) {
  background: #eee;
}
td input[type="submit"],input[type="text"],input[type="number"]{
width:100%;
height:100%;
}
</style>

</head>
<body>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
<%@include file="HeaderFooter.jsp" %><br>
<%@include file="Session.jsp" %>
<h1 align="center"> Transfer Money</h1>
<br>
	<form action="TransactionController?operation=transferMoney" method="post">

		<table align="center" style="font-size:20px;font-style:bold" class="rounded">
			<tr>
				<td >Customer ID</td>
				<td>${account.ws_cust_id}<input type="hidden" name="custid" value="${account.ws_cust_id }"></td>
			</tr>
			<tr>
				<td>Account ID</td>
				<td>${account.ws_acct_id}<input type="hidden" name="accid" value="${account.ws_acct_id }"></td>
			</tr>
			<tr>
				<td>Account Type</td>
				<td role='cell'>
				<c:set var = "status" scope = "session" value = "${account.ws_acct_type } "/>
				<c:set var = "statusA" scope = "session" value = "S"/>
				<c:set var = "statusI" scope = "session" value = "C"/>
				
					<c:if test="${fn:substring(statusA,0,1) == fn:substring(status, 0,1)}">
					 <c:out value="Saving"></c:out>
					</c:if>
				
					<c:if test="${fn:substring(statusI,0,1) == fn:substring(status, 0,1)}">
					 <c:out value="Current"></c:out>
					</c:if>
				
				</td>

			</tr>
			<tr>
				<td>Balance</td>
				<td>${account.ws_acct_balance}<input type="hidden" name="accBalance" value="${account.ws_acct_balance }"></td>
			</tr>
			<tr>
				<td>Target Account ID</td>
				<td><input type="number" name="tgtAccountId" oninput="this.value = Math.abs(this.value)" required></td>
			</tr>
			<tr>
				<td>Transfer Amount</td>
				<td><input type="number" name="transferAmt" oninput="this.value = Math.abs(this.value)" required></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" class="btn btn-dark" value="Submit" style="width:100%"></td>
			</tr>
			
			<tr>
			<td colspan="2" align="center"><h3 style="color: red;">${transferError }</h3></td>
			</tr>
		</table>
				
	</form>
<br><br>
</body>
</html>