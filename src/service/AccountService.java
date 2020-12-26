package service;
import java.util.ArrayList;

import bean.Account;

public interface AccountService {
	
	
	public long generateAccountId();
	public int createAccount(Account a);
	public ArrayList<Account> readAllAccount();
	public Account readAccount(Account a);
	public ArrayList<Account> readAllCustomerAccount(Account a);
	public int updateAccountStatus(Account a);
	public int resetAccountStatus(Account a);
	public int updateAccountStatusUsingCustId(long cust_id);
	public int resetAccountStatusUsingCustId(long cust_id);

}
