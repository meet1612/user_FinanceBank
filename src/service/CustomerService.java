package service;

import java.util.ArrayList;

import bean.Customer;

public interface CustomerService {
	
	public boolean check(Customer c);
	public int createCustomer(Customer c);
	public Customer getCustomerBySSN(long ssn);
	public Customer getCustomerByID(long custid);
	public Customer getCustomerByEmail(String email);
	public int deleteCustomer(long custid);
	public int updateCustomer(Customer c);
	public int changepass(Customer c);
	public ArrayList<Customer> readAllcustomer();

}
