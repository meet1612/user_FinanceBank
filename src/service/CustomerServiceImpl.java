package service;

import java.util.ArrayList;

import bean.Account;
import bean.Customer;
import dao.CustomerDao;

public class CustomerServiceImpl implements CustomerService {
	
	@Override
	public int createCustomer(Customer c) {
		int status = CustomerDao.createCustomer(c);
		return status;
	}

	@Override
	public Customer getCustomerBySSN(long ssn) {
		Customer c = CustomerDao.getCustomerBySSN(ssn);
		return c;
	}

	@Override
	public Customer getCustomerByID(long custid) {
		Customer c = CustomerDao.getCustomerByID(custid);
		return c;
	}

	@Override
	public int deleteCustomer(long custid) {
		AccountServiceImpl accService = new AccountServiceImpl();

		Account a = new Account();
		a.setWs_cust_id(custid);
		ArrayList<Account> listOfAcc = accService.readAllCustomerAccount(a);
		System.out.println("Size of listOfAcc ArrayList : " + listOfAcc.size());
		int status = 0;
		if (listOfAcc.size() != 0) {
			int s = accService.updateAccountStatusUsingCustId(custid);

			if (s > 0) {
				// System.out.println("if s>0");
				status = CustomerDao.deleteCustomer(custid);
				// System.out.println("status : "+status);
				if (status <= 0) {
					// System.out.println("if status <= 0");
					int r = accService.resetAccountStatusUsingCustId(custid);
				}
			}
		}
		else {
			status = CustomerDao.deleteCustomer(custid);
		}
		return status;
	}

	@Override
	public int updateCustomer(Customer c) {
		int status = CustomerDao.updateCustomer(c);
		return status;
	}

	@Override
	public ArrayList<Customer> readAllcustomer() {
		return CustomerDao.readAllcustomer();
	}

	@Override
	public boolean check(Customer c) 
	{
		return CustomerDao.check(c);
	}

	@Override
	public Customer getCustomerByEmail(String email) {
		Customer c = CustomerDao.getCustomerByEmail(email);
		return c;
	}

	@Override
	public int changepass(Customer c) {
		int status = CustomerDao.changepass(c);
		return status;
	}

}
