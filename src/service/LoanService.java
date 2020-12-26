package service;

import java.util.ArrayList;


import bean.*;

public interface LoanService {

	public ArrayList<Loan> readAllLoan();
	public Loan readLoan(Loan l);
	public int updateLoanStatus(Loan l);
	public int insertLoan(Loan l);
	public int insertcustomer_loan(customer_loan cl);
	public customer_loan readCustomerLoan(customer_loan cl);
}
