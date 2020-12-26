<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList,bean.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<title>Finance Bank</title>

<style>
input[type="number"],input[type="submit"],input[type="text"]{
width:100%;
}

.info{
 width:40%;
}

.effect{border: 0; padding: 7px 0; border-bottom: 1px solid #ccc;}
.effect ~ .focus-border{position: absolute; bottom: 0; left: 50%; width: 0; height: 2px; background-color: #3399FF; transition: 0.4s;}
.effect:focus ~ .focus-border{width: 100%; transition: 0.4s; left: 0;}
</style>

<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

</head>
<body>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
<script>
function checkEMI(){
	console.log("Inside EMI");
	let P=document.getElementById("loan_amt").value;
	let N=document.getElementById("months").value;
	let R=10/12;
	R=R/100;
	console.log(R);
	let x=Math.pow((1+R), N);
	console.log(x);
	
	let EMI =0;
	EMI= [P * R * x];
	EMI=EMI/[x-1];
	EMI=Math.round(EMI);
	
	document.getElementById("emi").value=EMI;
	document.getElementById("p").innerHTML="Loan Amount : "+P;
	document.getElementById("r").innerHTML="Intrest : 10%";
	document.getElementById("n").innerHTML="Duaration (in Months) : "+N;
	document.getElementById("emi1").innerHTML="EMI : "+EMI;
	document.getElementById("formula").innerHTML=" [P x R x (1+R)^N]/[(1+R)^ (N-1)]";
	
	document.getElementById("submit_btn").hidden=false;
	document.getElementById("check_btn").hidden=true;
}
function onFocus(){
	document.getElementById("check_btn").hidden=false;
}

</script>
    
	<%@include file="HeaderFooter.jsp" %>
	<%@include file="Session.jsp" %>
	
	<%
		Customer c = (Customer) request.getAttribute("Customer");
		ArrayList<Account> alist = (ArrayList<Account>) request.getAttribute("accounts");
		System.out.println(c);
	%>
	
	<form action="LoanController?operation=loan_request" method="post">
	
	<div class="container">
	
	<table class="table">
    <thead>
      <tr>
      		<th>Name</th>
      		<th><input type="text" name="custname" value=<%=c.getWs_name() %> disabled></th>
      </tr>
    </thead>
    <tbody>
      
      	<tr>
      		<td>Email Id</td>
      		<td><input type="text" name="email_id" value=<%= c.getWs_email_id() %> disabled></td>
      	</tr>
      	
      	<tr>
      		<td>Account</td>
      		<td><select name="accounts" id="accounts" style="width:100%">
			<%
			for(Account a:alist)
			{
				out.print("<option value="+a.getWs_acct_id()+">"+a.getWs_acct_id()+"</option>");
			}
			%>
		</select></td>
		</tr>
		
		<tr>
			<td>Address</td>
			<td><textarea name="address" disabled><%= c.getWs_adrs() %></textarea></td>
		</tr>
		
		<tr>
			<td>State</td>
			<td><input type="text" name="state" value=<%= c.getWs_state() %> disabled></td>
		</tr>
	
		<tr>
			<td>City</td>
			<td><input type="text" name="city" value=<%= c.getWs_city() %> disabled></td>
		</tr>
		
		<tr>
			<td>Age</td>
			<td><input type="number" name="age" value=<%=c.getWs_age() %> disabled></td>
		</tr>
		
		<tr>
			<td>Loan Amount</td>
			<td><input id="loan_amt" type="number"  onfocus="onFocus()" name="loan_amt" ></td>
		</tr>
		
		<tr>
			<td>Duration (in Months)</td>
			<td><input id="months" type="number" onfocus="onFocus()" name="months" ></td>
			<td><input type="hidden" name="emi" id="emi"></td>
		</tr>
		
		</tbody>
		</table>
		<div class="infoloan" align="center">
		<input id="submit_btn" hidden type="submit" value="Apply For Loan" style="width:40%" class="btn btn-dark">
		</div>
		</div>
		</form>
		
		<div class="infoloan" align="center">
		
		<button id="check_btn" onclick="checkEMI()" class="btn btn-dark" style="width:40%;">check</button><br>
		<div class="info">
		<span id="p"></span><br>
		<span id="r"></span><br>
		<span id="n"></span><br>
		<span id="emi1"></span><br>
		<span id="formula"></span><br>
		</div>
		</div>
		<br>
		<br>
		
</body>
</html>