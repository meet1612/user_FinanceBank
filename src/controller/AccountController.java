package controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Account;
import bean.Customer;
import service.AccountServiceImpl;
import service.CustomerServiceImpl;

public class AccountController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String operation = request.getParameter("operation");
		if(operation.equals("create"))
		{
			request.setAttribute("operation", operation);
			request.getRequestDispatcher("AccountRegistration.jsp").include(request, response);
		}
		else if(operation.equals("delete"))
		{
			request.setAttribute("operation", operation);
			request.getRequestDispatcher("AccountDelete.jsp").include(request, response);
		}
		else if(operation.equals("accounts"))
		{
			HttpSession session = request.getSession();
			Customer c= new Customer();
			String email=(String)session.getAttribute("email_id");
			CustomerServiceImpl service = new CustomerServiceImpl() ;
			AccountServiceImpl service1 = new AccountServiceImpl();
			c=service.getCustomerByEmail(email);
			Account a = new Account();
			a.setWs_cust_id(c.getWs_cust_id());
			ArrayList<Account> list = new ArrayList<Account>();
			list=service1.readAllCustomerAccount(a);
			request.setAttribute("list", list);
			request.setAttribute("operation", operation);
			request.getRequestDispatcher("myaccount.jsp").include(request, response);
		}
		else if(operation.equals("status"))
		{
			request.setAttribute("operation", operation);
			AccountServiceImpl service = new AccountServiceImpl();
			ArrayList<Account> a = service.readAllAccount();
			request.setAttribute("account", a);
			request.getRequestDispatcher("AccountStatus.jsp").include(request, response);
		}
		else if(operation.equals("search"))
		{
			request.setAttribute("operation", operation);
			request.getRequestDispatcher("AccountSearch.jsp").include(request, response);
		}
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String operation = request.getParameter("operation");
		if(operation.equals("create"))
		{
			Date date = new Date();
			Timestamp ts = new Timestamp(date.getTime());
			
			long ws_cust_id = Long.parseLong(request.getParameter("customerid"));
			String ws_acct_type = request.getParameter("accountType");
			String ws_acct_status = "A";
			long ws_acct_balance = Long.parseLong(request.getParameter("amount"));
			Timestamp ws_acct_crdate = ts;
			
			
			Account a = new Account();
			a.setWs_cust_id(ws_cust_id);
			a.setWs_acct_type(ws_acct_type);
			a.setWs_acct_status(ws_acct_status);
			a.setWs_acct_balance(ws_acct_balance);
			a.setWs_acct_crdate(ws_acct_crdate);
			
			
			AccountServiceImpl service = new AccountServiceImpl();
			int status = service.createAccount(a);
			System.out.println("hi"+status);
			if (status > 0) 
			{
				System.out.println("Insert successfull");
				request.setAttribute("insertmsg", "Inserted Successfully");
				request.setAttribute("operation", "create");
				request.getRequestDispatcher("AccountRegistration.jsp").include(request, response);
			} 
			else 
			{
				System.out.println("Error occured while inserting data");
				request.setAttribute("insertError", "Invalid Fields !!");
				request.setAttribute("operation", "create");
				request.getRequestDispatcher("AccountRegistration.jsp").include(request, response);
			}
		}
		else if(operation.equals("delete"))
		{
			long ws_acct_id = Long.parseLong(request.getParameter("accountid")); 
			Account a = new Account();
			a.setWs_acct_id(ws_acct_id);
			
			AccountServiceImpl service = new AccountServiceImpl();
			int status = service.updateAccountStatus(a);
			if (status > 0) 
			{
				System.out.println("Delete successfull");
				response.sendRedirect("index.jsp");
			} 
			else 
			{
				System.out.println("Error occured while delete data");
				request.setAttribute("insertError", "Invalid Fields !!");
				request.setAttribute("operation", "delete");
				request.getRequestDispatcher("AccountDelete.jsp").include(request, response);
			}
		}
		else if(operation.equals("search"))
		{
			AccountServiceImpl service = new AccountServiceImpl();
			String searchBy = request.getParameter("search");
			ArrayList<Account> list = new ArrayList<Account>();
			if(searchBy.equals("cust_id"))
			{
				long custid = Long.parseLong(request.getParameter("custid"));
				Account a = new Account();
				a.setWs_cust_id(custid);
				list = service.readAllCustomerAccount(a);
				for(Account acc : list)
				{
					System.out.println(acc);
				}	
			}
			else if(searchBy.equals("acc_id"))
			{
				long accid = Long.parseLong(request.getParameter("accid"));
				Account a = new Account();
				a.setWs_acct_id(accid);
				Account acc = service.readAccount(a);
				System.out.println(acc);
				list.add(acc);
			}
			request.setAttribute("list", list);
			request.getRequestDispatcher("AccountDisplay.jsp").include(request, response);
		}
	}

}
