package service;

import java.util.ArrayList;

import bean.Account;
import bean.Transaction;

public interface TransactionService {
	public Account readAccount(Account a);
	public ArrayList<Account> readAllCustomerAccount(Account a);
	public int transactionWithdraw(Transaction t);
	public int transactionDeposit(Transaction t);
	public int transactionTransfer(Transaction t);
	public ArrayList<Transaction> readTransactionNumberWise(long acc_id, int n);
	public ArrayList<Transaction> readTransactionDateWise(long acc_id, String startDate, String endDate);
}
