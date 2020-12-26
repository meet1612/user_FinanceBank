var request;
var id;
function sendInfo(loan_id) 
{
	console.log(loan_id);
	id = loan_id;
	var url = "LoanController?operation=loanStatus?value="+id;

	  var xhttp = new XMLHttpRequest();
	  xhttp.open("GET", url, true);
	  xhttp.send();
}
