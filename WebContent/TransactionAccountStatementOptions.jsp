<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
 <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<title>Account Statement Options</title>

<style>
input[type="number"],input[type="submit"],input[type="text"]{
width:100%;
}

.effect{border: 0; padding: 7px 0; border-bottom: 1px solid #ccc;}
.effect ~ .focus-border{position: absolute; bottom: 0; left: 50%; width: 0; height: 2px; background-color: #3399FF; transition: 0.4s;}
.effect:focus ~ .focus-border{width: 100%; transition: 0.4s; left: 0;}
</style>

<script>$('#date').datepicker({ dateFormat: 'dd/mm/yyyy' }).val();</script>

<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  </head>
  
 
<body>
<!-- <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script> -->
<script type="text/javascript" src="TransactionAccountStatementOptions.js"></script>
<%@include file="HeaderFooter.jsp" %><br>
<%@include file="Session.jsp" %>


	<form action="TransactionController?operation=viewAccountStatement" method="post">

		
		
		<div class="container">
              
  <table class="table">
    <thead>
      <tr>
				<th>Account ID</th>
				<th>${account.ws_acct_id}<input type="hidden" name="accid" value="${account.ws_acct_id }"></th>
			</tr>
    </thead>
    <tbody>
    
			<tr>
			<td><input type="radio" id="ssn_rb" onchange="onRadioChange()" name="acct_statement_options" value="number" checked="checked" required> Last Number of Transactions</td>
			<td>
			<input class="effect" type="number" id="ssn_in" name="numberOfTrxns" placeholder="Last number of transactions" required>
            <span class="focus-border"></span>
			</td>
				 
				
			</tr>
			<tr>
				<td><input type="radio" id="id_rb" onchange="onRadioChange()" name="acct_statement_options" value="date"> Start Date - End Date</td>
				
				<td><input  id="id_in" id="a1"  class="effect" type="text"  placeholder="Enter end date (dd/mm/yyyy)" name="startdate" required disabled>
				<br></br><input id="id_in1" id="a2" class="effect" type="text"  placeholder="Enter end date (dd/mm/yyyy)" name="enddate" required disabled> </td>
				
				
			</tr>
	
			<tr>
				<td colspan="2" align="center"><input type="submit" class="btn btn-dark" id="search_btn" value="Submit"></td>
			</tr>
			
			<tr>
				<td colspan="2" align="center"><h3 style="color: red;">${acctStatementError }</h3></td>
			</tr>
  
    </tbody>
  </table>
</div>
</form>

</body>
