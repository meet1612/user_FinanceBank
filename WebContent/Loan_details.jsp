<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.ArrayList,bean.Loan"%>

<!DOCTYPE html>
<html>
<head>
<meta charset='utf-8'>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Display Loan</title>
<meta charset="ISO-8859-1">

<link href='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css' rel='stylesheet'>
<link href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.0.3/css/font-awesome.css' rel='stylesheet'>
<link rel="stylesheet" href="LoanStatusAJAX.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">


<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />
   
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.js" type="text/javascript"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js" type="text/javascript"></script>
	



<script>

function onclickStatus1(){
	//console.log("onclick status1");
	var id = $("#loan_id").val();
	console.log("id : "+id);
	$.ajax({
        type: "GET",
        url: "LoanController?operation=doc_check&loan_id="+id,
        data: id,
        //dataType: "json",
        success: function( data, textStatus, jqXHR){ 
        	 //$("#ajaxResponse").html(jqXHR.responseText);
        	 //alert(data);
        	 // data = success / exists
        	if(data=="Success"){
        		console.log("Success");
        	}
        	else{
        		console.log("Reject");
        	}
        }
});
}



function onclickStatus2(){
	//console.log("onclick status1");
	var id = $("#loan_id").val();
	console.log("id : "+id);
	$.ajax({
        type: "GET",
        url: "LoanController?operation=final_check&loan_id="+id,
        data: id,
        //dataType: "json",
        success: function( data, textStatus, jqXHR){ 
        	 //$("#ajaxResponse").html(jqXHR.responseText);
        	 //alert(data);
        	 // data = success / exists
        	if(data=="Success"){
        		console.log("Success");
        	}
        	else{
        		console.log("Reject");
        	}
        }
});
}



function onclickStatus3(){
	//console.log("onclick status1");
	var id = $("#loan_id").val();
	console.log("id : "+id);
	$.ajax({
        type: "GET",
        url: "LoanController?operation=approved&loan_id="+id,
        data: id,
        //dataType: "json",
        success: function( data, textStatus, jqXHR){ 
        	 //$("#ajaxResponse").html(jqXHR.responseText);
        	 //alert(data);
        	 // data = success / exists
        	if(data=="Success"){
        		console.log("Success");
        	}
        	else{
        		console.log("Reject");
        	}
        }
});
}






function onclickReject(){
	var id = $("#loan_id").val();
	console.log("id : "+id);
	$.ajax({
        type: "GET",
        url: "LoanController?operation=reject&loan_id="+id,
        data: id,
        //dataType: "json",
        success: function( data, textStatus, jqXHR){ 
        	 //$("#ajaxResponse").html(jqXHR.responseText);
        	 //alert(data);
        	 // data = success / exists
        	if(data=="Success"){
        		console.log("Success");
        		document.getElementById("reject1").hidden=true;
        		document.getElementById("status1").hidden=true;
        		document.getElementById("rejectError").innerHTML="Rejected Successfully";
        		
        		
        	}
        	else{
        		console.log("Reject");
        	}
        }
});
}

function func1(status)
{
	console.log(status);
	let l1=document.getElementsByName("l1")[0];
	let l2=document.getElementsByName("l2")[0];
	let l3=document.getElementsByName("l3")[0];
	let l4=document.getElementsByName("l4")[0];
	
	let fs1=document.getElementsByName("fs1")[0];
	let fs2=document.getElementsByName("fs2")[0];
	let fs3=document.getElementsByName("fs3")[0];
	let fs4=document.getElementsByName("fs4")[0];
	
	let pb1=document.getElementsByName("pb1")[0];
	
	if(status==0)
	{
		 l1.classList.add("active");
		 
		 fs1.style.display = "block";
		 fs1.style.opacity = 1;
		 

		 pb1.style.width="25%";
		 

	}
	else if(status==1)
	{
		l1.classList.add("active");
		l2.classList.add("active");
		
		 fs1.style.display = "none";
		 fs1.style.position = "relative";
		 fs1.style.opacity = 0;
		 
		 fs2.style.display = "block";
		 fs2.style.opacity = 1;
		 
		 pb1.style.width="50%";
		 
	}
	else if(status==2)
	{
		l1.classList.add("active");
		l2.classList.add("active");
		l3.classList.add("active");
		
		 fs1.style.display = "none";
		 fs1.style.position = "relative";
		 fs1.style.opacity = 0;
		 
		 fs2.style.display = "none";
		 fs2.style.position = "relative";
		fs2.style.opacity = 0;
		 
		 fs3.style.display = "block";
		 fs3.style.opacity = 1;
		 
		 pb1.style.width="75%";
		 
	}
	else if(status==3)
	{
		l1.classList.add("active");
		l2.classList.add("active");
		l3.classList.add("active");
		l4.classList.add("active");
		
		 fs1.style.display = "none";
		 fs1.style.position = "relative";
		 fs1.style.opacity = 0;
		 
		fs2.style.display = "none";
		fs2.style.position = "relative";
		fs2.style.opacity = 0;
		 
		 fs3.style.display = "none";
		 fs3.style.position = "relative";
		 fs3.style.opacity = 0;
		 
		 fs4.style.display = "block";
		 fs4.style.opacity = 1;
		 
		 pb1.style.width="100%";
		 
	}
	
		
	}
	
	


</script>
<script type='text/javascript' src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>
<script type='text/javascript' src='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js'></script>

<script type="text/javascript" src="LoanStatusAJAX.js"></script>

</head>

<%

Loan l=(Loan)request.getAttribute("loan");

%>


<body onload="func1(<%= l.getStatus() %>)">
<!-- <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script> -->

	<%@include file="HeaderFooter.jsp" %>
	<%@include file="Session.jsp" %>
	


<div class="container-fluid">
    <div class="row justify-content-center">
        <div class="col-11 col-sm-9 col-md-7 col-lg-6 col-xl-5 text-center p-0 mt-3 mb-2">
            <div class="card px-0 pt-4 pb-0 mt-3 mb-3">
                <h2 id="heading">Loan Process</h2>
                <p>Loan Process Steps, wait and check your status!</p>
                <form id="msform">
                    <!-- progressbar -->
                    <ul id="progressbar">
                        <li class="active" name="l1" id="account"><strong>Loan Details</strong></li>
                        <li id="personal" name="l2"><strong>Document Check</strong></li>
                        <li id="payment" name="l3"><strong>Final Check</strong></li>
                        <li id="confirm" name="l4"><strong>Finish</strong></li>
                    </ul>
                    <div class="progress">
                        <div class="progress-bar progress-bar-striped progress-bar-animated" name="pb1" role="progressbar" aria-valuemin="0" aria-valuemax="100"></div>
                    </div> <br> <!-- fieldsets -->
                    <fieldset name="fs1">
                        <div class="form-card">
                            <div class="row">
                                <div class="col-7">
                                    <h2 class="fs-title">Loan Information:</h2>
                                </div>
                                <div class="col-5">
                                    <h2 class="steps">Step 1 - 4</h2>
                                </div>
                            </div> 
                            <div>
                            
                            <%
                            
                            
                            
                            
                            
                            out.print("<table>");
                            
                            out.print("<tr>");
                            out.print("<td> Account Id:- </td>");
                            out.print("<td>"+l.getAcc_id()+" </td>");
                            out.print("</tr>");
                            
                            out.print("<tr>");
                            out.print("<td> Loan Amount:-</td>");
                            out.print("<td>"+l.getLoan_amt()+" </td>");
                            out.print("</tr>");
                            
                            
                            out.print("<tr>");
                            out.print("<td> EMI:- </td>");
                            out.print("<td>"+l.getEmi()+" </td>");
                            out.print("</tr>");
                            
                            
                            out.print("<tr>");
                            out.print("<td> Duartion(in months):- </td>");
                            out.print("<td>"+l.getMonths()+" </td>");
                            out.print("</tr>");
                            
                            out.print("</table>");
                            
                            %>
                            
                            </div>
                        </div> 
                        <input type="hidden" name="loan_id" id="loan_id" value=<%= l.getLoan_id() %>>
                        
              
                       <span id="rejectError" style="color:green;"></span>
                       
                    </fieldset>
                    <fieldset name="fs2">
                        <div class="form-card">
                            <div class="row">
                                <div class="col-7">
                                    <h2 class="fs-title">Documentation Checking:</h2>
                                </div>
                                <div class="col-5">
                                    <h2 class="steps">Step 2 - 4</h2>
                                </div>
                            </div> 
                            
                            
                            <h4>Waiting for employee's acknowledgement about the documents verification.</h4>
                            
                        </div>
                        
                    </fieldset>
                    <fieldset name="fs3">
                        <div class="form-card">
                            <div class="row">
                                <div class="col-7">
                                    <h2 class="fs-title">Final Check:</h2>
                                    <p>Verify your Details</p>
                                </div>
                                <div class="col-5">
                                    <h2 class="steps">Step 3 - 4</h2>
                                </div>
                            </div> 
                            
                             <%
                            
                            l=(Loan)request.getAttribute("loan");
                            
                            
                            
                            out.print("<table>");
                            
                            out.print("<tr>");
                            out.print("<td> Account Id:- </td>");
                            out.print("<td>"+l.getAcc_id()+" </td>");
                            out.print("</tr>");
                            
                            out.print("<tr>");
                            out.print("<td> Loan Amount:- </td>");
                            out.print("<td>"+l.getLoan_amt()+" </td>");
                            out.print("</tr>");
                            
                            
                            out.print("<tr>");
                            out.print("<td> EMI:- </td>");
                            out.print("<td>"+l.getEmi()+" </td>");
                            out.print("</tr>");
                            
                            
                            out.print("<tr>");
                            out.print("<td> Duartion(in months):- </td>");
                            out.print("<td>"+l.getMonths()+" </td>");
                            out.print("</tr>");
                            
                            out.print("</table>");
                            
                            %>
                            
                            
                            <h5>Checking Your personal and bank details.</h5>
                            
                            
                        </div> 
                  
                    </fieldset>
                    <fieldset name="fs4">
                        <div class="form-card">
                            <div class="row">
                                <div class="col-7">
                                    <h2 class="fs-title">Finish:</h2>
                                </div>
                                <div class="col-5">
                                    <h2 class="steps">Step 4 - 4</h2>
                                </div>
                            </div>
                            <h2 class="purple-text text-center"><strong>SUCCESS !</strong></h2>
                            <div class="row justify-content-center">
                                <div class="col-3"> <img src="https://thumbs.dreamstime.com/b/purple-correct-mark-symbol-background-purple-correct-mark-symbol-isolated-background-159208188.jpg" class="fit-image" > </div>
                            </div>
                            <div class="row justify-content-center">
                                <div class="col-7 text-center">
                                    <h5 class="purple-text text-center">Your Loan is approved successfully.!</h5>
                                </div>
                            </div>
                        </div>
                    </fieldset>
                </form>
            </div>
        </div>
    </div>
</div>
<br>
<br>
<br>


</body>
</html>