package service;

import java.util.ArrayList;

import bean.Account;
import bean.Transaction;
import dao.TransactionDao;

public class TransactionServiceImpl implements TransactionService {

	@Override
	public Account readAccount(Account a) {
		AccountServiceImpl service = new AccountServiceImpl();
		Account acc = service.readAccount(a);
		return acc;
	}

	@Override
	public ArrayList<Account> readAllCustomerAccount(Account a) {
		AccountServiceImpl service = new AccountServiceImpl();
		ArrayList<Account> list = service.readAllCustomerAccount(a);
		return list;
	}

	@Override
	public int transactionWithdraw(Transaction t) {
		return TransactionDao.transactionWithdraw(t);
	}

	@Override
	public int transactionDeposit(Transaction t) {
		return TransactionDao.transactionDeposit(t);
	}

	@Override
	public int transactionTransfer(Transaction t) {
		return TransactionDao.transactionTransfer(t);
	}

	@Override
	public ArrayList<Transaction> readTransactionNumberWise(long acc_id, int n) {
		ArrayList<Transaction> list = TransactionDao.readTransactionNumberWise(acc_id, n);
		return list;
	}

	@Override
	public ArrayList<Transaction> readTransactionDateWise(long acc_id, String startDate, String endDate) {
		ArrayList<Transaction> list = TransactionDao.readTransactionDateWise(acc_id, startDate, endDate);
		return list;
	}

}
