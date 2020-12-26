package service;

import java.util.ArrayList;

import bean.Account;
import dao.AccountDao;

public class AccountServiceImpl implements  AccountService {
	
	@Override
	public long generateAccountId() 
	{
		return AccountDao.generateAccountId();
	}

	@Override
	public int createAccount(Account a) 
	{
		return AccountDao.createAccount(a);
	}

	@Override
	public ArrayList<Account> readAllAccount() 
	{
		return AccountDao.readAllAccount();
	}

	@Override
	public Account readAccount(Account a) 
	{
		return AccountDao.readAccount(a);
	}

	@Override
	public ArrayList<Account> readAllCustomerAccount(Account a) 
	{
		return AccountDao.readAllCustomerAccount(a);
	}

	@Override
	public int updateAccountStatus(Account a) 
	{
		return AccountDao.updateAccountStatus(a);
	}

	@Override
	public int resetAccountStatus(Account a) {
		return AccountDao.resetAccountStatus(a);
	}

	@Override
	public int updateAccountStatusUsingCustId(long cust_id) {
		return AccountDao.updateAccountStatusUsingCustId(cust_id);
	}

	@Override
	public int resetAccountStatusUsingCustId(long cust_id) {
		return AccountDao.resetAccountStatusUsingCustId(cust_id);
	}

}
