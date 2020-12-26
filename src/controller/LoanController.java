package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Account;
import bean.Customer;
import bean.Loan;
import bean.Transaction;
import bean.customer_loan;
import service.AccountServiceImpl;
import service.CustomerServiceImpl;
import service.LoanServiceImpl;
import service.TransactionServiceImpl;
import util.EmailUtility;

public class LoanController extends HttpServlet {
	
	 private String host;
	    private String port;
	    private String user;
	    private String pass;
	 
	    public void init() {
	        // reads SMTP server setting from web.xml file
	        ServletContext context = getServletContext();
	        host = context.getInitParameter("host");
	        port = context.getInitParameter("port");
	        user = context.getInitParameter("user");
	        pass = context.getInitParameter("pass");
	    }
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String operation = request.getParameter("operation");
		System.out.println(operation);
		if(operation.equals("loan"))
		{
			request.setAttribute("operation", operation);
			LoanServiceImpl service = new LoanServiceImpl();
			ArrayList<Loan> l = service.readAllLoan();
			request.setAttribute("loan", l);
			request.getRequestDispatcher("Loan_management.jsp").include(request, response);
		}
		else if(operation.equals("loanStatus"))
		{
			int id=Integer.parseInt(request.getParameter("loan_id"));
			System.out.println(id+"");
			Loan l=new Loan();
			l.setLoan_id(id);
			LoanServiceImpl service = new LoanServiceImpl();
			 l = service.readLoan(l);
			 request.setAttribute("loan", l);
			 request.getRequestDispatcher("LoanStatusAJAX.jsp").include(request, response);
			
		}
		else if(operation.equals("doc_check"))
		{
			int id=Integer.parseInt(request.getParameter("loan_id"));
			System.out.println(id+"next");
			Loan l=new Loan();
			l.setLoan_id(id);
			l.setStatus(1);
			LoanServiceImpl service = new LoanServiceImpl();
			CustomerServiceImpl service1 = new CustomerServiceImpl();
			AccountServiceImpl service2 = new AccountServiceImpl();
			int affectedRows=service.updateLoanStatus(l);
			if(affectedRows>0)
			{
				System.out.println("success");
				l=service.readLoan(l);
				Account a=new Account();
				a.setWs_acct_id(l.getAcc_id());
				a=service2.readAccount(a);
				Customer c = new Customer();
				c = service1.getCustomerByID(a.getWs_cust_id());
				
				 String recipient = c.getWs_email_id();
			        String subject ="Loan Request Progression";
			        String content = "Loan has been accepted and requested for manual document check .";
			 
			        String resultMessage = "";
			 
			        try {
			            EmailUtility.sendEmail(host, port, user, pass, recipient, subject,
			                    content);
			            resultMessage = "The e-mail was sent successfully";
			        } catch (Exception ex) {
			            ex.printStackTrace();
			            resultMessage = "There were an error: " + ex.getMessage();
			        } finally {
			           System.out.println("hello"+resultMessage);
			        }
				
			}
			else
			{
				System.out.println("reject");
			}
			
		}
		
		
		else if(operation.equals("final_check"))
		{
			int id=Integer.parseInt(request.getParameter("loan_id"));
			Loan l=new Loan();
			l.setLoan_id(id);
			l.setStatus(2);
			LoanServiceImpl service=new LoanServiceImpl();
			CustomerServiceImpl service1 = new CustomerServiceImpl();
			AccountServiceImpl service2 = new AccountServiceImpl();
			int affected_rows=service.updateLoanStatus(l);
			if(affected_rows>0)
			{
				
				System.out.println("Success");
				
				l=service.readLoan(l);
				Account a=new Account();
				a.setWs_acct_id(l.getAcc_id());
				a=service2.readAccount(a);
				Customer c = new Customer();
				c = service1.getCustomerByID(a.getWs_cust_id());
				
				 String recipient = c.getWs_email_id();
			        String subject ="Loan Request Progression";
			        String content = "Documents verified successfuly and waiting for final check! .";
			 
			        String resultMessage = "";
			 
			        try {
			            EmailUtility.sendEmail(host, port, user, pass, recipient, subject,
			                    content);
			            resultMessage = "The e-mail was sent successfully";
			        } catch (Exception ex) {
			            ex.printStackTrace();
			            resultMessage = "There were an error: " + ex.getMessage();
			        } finally {
			           System.out.println("hello"+resultMessage);
			        }
				
				
			}
			else
			{
				System.out.println("error");
			}
		}
		
		
		else if(operation.equals("loan_request"))
		{
			HttpSession session = request.getSession();
			String email_id=(String)session.getAttribute("email_id");
			Customer c = new Customer();
			CustomerServiceImpl service = new CustomerServiceImpl();
			c=service.getCustomerByEmail(email_id);
			ArrayList<Account> alist = new ArrayList<Account>();
			Account a = new Account();
			a.setWs_cust_id(c.getWs_cust_id());
			AccountServiceImpl service1= new AccountServiceImpl();
			alist=service1.readAllCustomerAccount(a);
			
			System.out.println(c);
			request.setAttribute("accounts", alist);
			request.setAttribute("Customer", c);
			
			request.getRequestDispatcher("Loan_request.jsp").include(request, response);
		}
		else if(operation.equals("loan_details"))
		{
			HttpSession session = request.getSession();
			String email_id=(String)session.getAttribute("email_id");
			Customer c = new Customer();
			CustomerServiceImpl service = new CustomerServiceImpl();
			LoanServiceImpl service1 = new LoanServiceImpl();
			c=service.getCustomerByEmail(email_id);
			customer_loan cl=new customer_loan();
			cl.setCustomer_id(c.getWs_cust_id());
			cl=service1.readCustomerLoan(cl);
			
			Loan l=new Loan();
			l.setLoan_id(cl.getLoan_id());
			l=service1.readLoan(l);
			
			 request.setAttribute("loan", l);
			 request.getRequestDispatcher("Loan_details.jsp").include(request, response);
			
		}
		else if(operation.equals("approved"))
		{
			int id=Integer.parseInt(request.getParameter("loan_id"));
			Loan l = new Loan();
			l.setLoan_id(id);
			l.setStatus(3);
			LoanServiceImpl service=new LoanServiceImpl();
			AccountServiceImpl service1=new AccountServiceImpl();
			CustomerServiceImpl service2=new CustomerServiceImpl();
			TransactionServiceImpl service3= new TransactionServiceImpl();
			int affected_rows=service.updateLoanStatus(l);
			if(affected_rows>0)
			{
				System.out.println("Success");
				l=service.readLoan(l);
				Account a=new Account();
				a.setWs_acct_id(l.getAcc_id());
				a=service1.readAccount(a);
				Customer c=new Customer();
				c=service2.getCustomerByID(a.getWs_cust_id());
				
				
				
				Transaction t = new Transaction();
				t.setWs_src_acct_id(a.getWs_acct_id());
				t.setWs_tgt_acct_id(a.getWs_acct_id());
				t.setWs_amt(l.getLoan_amt());
				t.setWs_op("D");
				affected_rows = service3.transactionDeposit(t);
				if(affected_rows>0)
				{
				
					
					 String recipient = c.getWs_email_id();
				        String subject ="Loan Approved Successfully";
				        String content = "A/c"+a.getWs_acct_id()+" Credited by"+l.getLoan_amt()+" Total balance :"+(a.getWs_acct_balance()+l.getLoan_amt());
				 
				        String resultMessage = "";
				 
				        try {
				            EmailUtility.sendEmail(host, port, user, pass, recipient, subject,
				                    content);
				            resultMessage = "The e-mail was sent successfully";
				        } catch (Exception ex) {
				            ex.printStackTrace();
				            resultMessage = "There were an error: " + ex.getMessage();
				        } finally {
				           System.out.println("hello"+resultMessage);
				        }
					System.out.println("Your amount is deposited");
					
				}
				
				
			}
			else
			{
				System.out.println("Error");
			}
			
		}
		
		
		
		else if(operation.equals("reject"))
		{
			int id=Integer.parseInt(request.getParameter("loan_id"));
			System.out.println(id+"next");
			
			LoanServiceImpl service = new LoanServiceImpl();
			CustomerServiceImpl service1 = new CustomerServiceImpl();
			AccountServiceImpl service2 = new AccountServiceImpl();
			
			Loan l1=new Loan();
			l1.setLoan_id(id);
			l1=service.readLoan(l1);
			int old_status=l1.getStatus();
			
			Loan l=new Loan();
			l.setLoan_id(id);
			l.setStatus(4);
			
			int affectedRows=service.updateLoanStatus(l);
			if(affectedRows>0)
			{
				System.out.println("success");
				PrintWriter out=response.getWriter();
				out.write("Success");
				
				l=service.readLoan(l);
				Account a=new Account();
				a.setWs_acct_id(l.getAcc_id());
				a=service2.readAccount(a);
				Customer c = new Customer();
				c = service1.getCustomerByID(a.getWs_cust_id());
				
				 String recipient = c.getWs_email_id();
			        String subject ="Loan Request Rejected";
			        String content = "";
			        if(old_status==1)
			        {
			        	content="Loan Rejected due to Document verification Failure";
			        }
			        else if(old_status==0)
			        {
			        	content="Loan should not be granted because of high amount";
			        }
			        else if(old_status==2)
			        {
			        	content="Documents are verified successfully but some issues are from bank side so please contact nearest bank..!";
			        }
			        else
			        {
			        	content="Loan rejected successfully";
			        }
			        String resultMessage = "";
			 
			        try {
			            EmailUtility.sendEmail(host, port, user, pass, recipient, subject,
			                    content);
			            resultMessage = "The e-mail was sent successfully";
			            
			            
			        
			            
			            
			        } catch (Exception ex) {
			            ex.printStackTrace();
			            resultMessage = "There were an error: " + ex.getMessage();
			        } finally {
			           System.out.println("hello"+resultMessage);
			        }
			        
			        
					
			        
				
			}
			else
			{
				System.out.println("reject");
			}
			
		}
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String operation = request.getParameter("operation");
		 if(operation.equals("loanStatus"))
		{
			int id=Integer.parseInt(request.getParameter("loan_id"));
			System.out.println(id+"");
			Loan l=new Loan();
			l.setLoan_id(id);
			LoanServiceImpl service = new LoanServiceImpl();
			 l = service.readLoan(l);
			 request.setAttribute("loan", l);
			 request.getRequestDispatcher("LoanStatusAJAX.jsp").include(request, response);
			
		}
		 else if(operation.equals("loan_request"))
		 {
			 int acc_id=Integer.parseInt(request.getParameter("accounts"));
			 long loan_amt=Long.parseLong(request.getParameter("loan_amt"));
			 int status=0;
			 float emi=Float.parseFloat(request.getParameter("emi"));
			 int months=Integer.parseInt(request.getParameter("months"));
			 
			 Loan l = new Loan();
			 
			 l.setAcc_id(acc_id);
			 l.setEmi(emi);
			 l.setStatus(status);
			 l.setLoan_amt(loan_amt);
			 l.setMonths(months);
			 
			 LoanServiceImpl service = new LoanServiceImpl();
			 CustomerServiceImpl service1 = new CustomerServiceImpl();
			 int loan_id=service.insertLoan(l);
			 
			 if(loan_id>0)
			 {
				 System.out.println("success");
				 l.setLoan_id(loan_id);
				 
				 HttpSession session = request.getSession();
				 Customer c=new Customer();
				 c=service1.getCustomerByEmail((String)session.getAttribute("email_id"));
				 
				 customer_loan cl=new customer_loan();
				 cl.setCustomer_id(c.getWs_cust_id());
				 cl.setLoan_id(loan_id);
				 
				 int affected_rows=service.insertcustomer_loan(cl);
				 if(affected_rows>0) {
					 System.out.println("success1");
				 request.setAttribute("loan", l);
				 request.getRequestDispatcher("Loan_details.jsp").include(request, response);
				 }
			 }
			 else
			 {
				 System.out.println("error");
			 }
			 
			 
			 
		 }
	}
}
