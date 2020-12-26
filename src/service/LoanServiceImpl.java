package service;

import java.util.ArrayList;

import bean.Loan;
import bean.customer_loan;
import dao.LoanDao;

public class LoanServiceImpl implements LoanService {

	@Override
	public ArrayList<Loan> readAllLoan() {
		return LoanDao.readAllLoan();
		
	}

	@Override
	public Loan readLoan(Loan l) {
		return LoanDao.readLoan(l);
	}

	@Override
	public int updateLoanStatus(Loan l) {
		return LoanDao.updateLoanStatus(l);
	}
	@Override
	public int insertLoan(Loan l) {
		return LoanDao.insertLoan(l);
	}

	@Override
	public int insertcustomer_loan(bean.customer_loan cl) {
		// TODO Auto-generated method stub
		return LoanDao.insertcustomer_loan(cl);
	}

	@Override
	public customer_loan readCustomerLoan(customer_loan cl) {
		return LoanDao.readCustomerLoan(cl);
	}

}
