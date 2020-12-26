package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import bean.Transaction;
import util.DbConnection;

public class TransactionDao {
	
	public static long generateTransactionId() {
		try {
			Connection con = DbConnection.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT MAX(ws_trxn_id) from transactions");

			while (rs.next()) {
				long id = rs.getLong(1);
				return id == 0 ? 100000000 : id + 1;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return -1;
	}

	public static int transactionDeposit(Transaction t) {
		int status = 0;
		try {
			Connection con = DbConnection.getConnection();
			long balance = 0;
			Date date = new Date();
			Timestamp ts = new Timestamp(date.getTime());
			PreparedStatement ps = con.prepareStatement("select * from account  where ws_acct_id = ?");
			ps.setLong(1, t.getWs_src_acct_id());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				balance = rs.getLong(5);
			}
			long new_balance = balance + t.getWs_amt();

			PreparedStatement ps1 = con.prepareStatement(
					"update account set ws_acct_balance = ?  where ws_acct_id = ?");
			ps1.setLong(1, new_balance);
			
			ps1.setLong(2, t.getWs_src_acct_id());
			int x = ps1.executeUpdate();
			if (x > 0) {
				long trans_id = generateTransactionId();
				PreparedStatement ps2 = con.prepareStatement(
						"insert into transactions ( ws_trxn_id,ws_src_acct_id,ws_tgt_acct_id,ws_amt,ws_op) values (?,?,?,?,?)");
				ps2.setLong(1, trans_id);
				ps2.setLong(2, t.getWs_src_acct_id());
				ps2.setLong(3, t.getWs_tgt_acct_id());
				ps2.setLong(4, t.getWs_amt());
				ps2.setString(5, t.getWs_op());
				status = ps2.executeUpdate();
				if (status > 0) {
					System.out.println("Deposite Successfull");
				}

			}
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return status;
	}

	public static int transactionWithdraw(Transaction t) {
		int status = 0;
		try {
			Connection con = DbConnection.getConnection();
			long balance = 0;
			Date date = new Date();
			Timestamp ts = new Timestamp(date.getTime());
			PreparedStatement ps = con.prepareStatement("select * from account  where ws_acct_id = ?");
			ps.setLong(1, t.getWs_src_acct_id());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				balance = rs.getLong(5);
			}
			long new_balance = balance - t.getWs_amt();

			PreparedStatement ps1 = con.prepareStatement(
					"update account set ws_acct_balance = ? where ws_acct_id = ?");
			ps1.setLong(1, new_balance);
		
			ps1.setLong(2, t.getWs_src_acct_id());
			int x = ps1.executeUpdate();
			if (x > 0) {
				long trans_id = generateTransactionId();
				PreparedStatement ps2 = con.prepareStatement(
						"insert into transactions ( ws_trxn_id,ws_src_acct_id,ws_tgt_acct_id,ws_amt,ws_op) values (?,?,?,?,?)");
				ps2.setLong(1, trans_id);
				ps2.setLong(2, t.getWs_src_acct_id());
				ps2.setLong(3, t.getWs_tgt_acct_id());
				ps2.setLong(4, t.getWs_amt());
				ps2.setString(5, t.getWs_op());
				status = ps2.executeUpdate();
				if (status > 0) {
					System.out.println("Withdraw Successfull");
				}

			}
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return status;
	}

	public static int transactionTransfer(Transaction src) {
		int status = 0;
		try {
			Connection con = DbConnection.getConnection();

			// getting balance from source acc id and updating it ---start
			long balance = 0;
			Date date = new Date();
			Timestamp ts = new Timestamp(date.getTime());
			PreparedStatement ps = con.prepareStatement("select * from account  where ws_acct_id = ?");
			ps.setLong(1, src.getWs_src_acct_id());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				balance = rs.getLong(5);
			}
			long new_balance_src = balance - src.getWs_amt();

			PreparedStatement ps1 = con.prepareStatement(
					"update account set ws_acct_balance = ? where ws_acct_id = ?");
			ps1.setLong(1, new_balance_src);
			
			ps1.setLong(2, src.getWs_src_acct_id());
			int xsrc = ps1.executeUpdate();
			// getting balance from source acc id and updating it ---end

			int xtgt = 0;
			// getting balance from target acc id and updating it ---start
			if (xsrc > 0) {
				balance = 0;
				ps.setLong(1, src.getWs_tgt_acct_id());
				rs = null;
				rs = ps.executeQuery();
				while (rs.next()) {
					balance = rs.getLong(5);
				}
				long new_balance_tgt = balance + src.getWs_amt();
				ps1.setLong(1, new_balance_tgt);
			
				ps1.setLong(2, src.getWs_tgt_acct_id());
				xtgt = ps1.executeUpdate();
			}
			// getting balance from target acc id and updating it ---end

			// generating two entries for both source and target account id in transactions
			// table
			if (xsrc > 0 && xtgt > 0) {

				long trans_id = generateTransactionId();
				PreparedStatement ps2 = con.prepareStatement(
						"insert into transactions ( ws_trxn_id,ws_src_acct_id,ws_tgt_acct_id,ws_amt,ws_op) values (?,?,?,?,?)");
				ps2.setLong(1, trans_id);
				ps2.setLong(2, src.getWs_src_acct_id());
				ps2.setLong(3, src.getWs_tgt_acct_id());
				ps2.setLong(4, -src.getWs_amt());
				ps2.setString(5, src.getWs_op());
				status = ps2.executeUpdate();
				if (status > 0) {
					System.out.println("Transfer money Successfull--->1st transaction entry");
				}

				status = 0;
				ps2.setLong(1, trans_id);
				ps2.setLong(2, src.getWs_tgt_acct_id());
				ps2.setLong(3, src.getWs_src_acct_id());
				ps2.setLong(4, src.getWs_amt());
				ps2.setString(5, src.getWs_op());
				status = ps2.executeUpdate();
				if (status > 0) {
					System.out.println("Transfer money Successfull--->2nd transaction entry");
				}
			}
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return status;
	}

	public static ArrayList<Transaction> readTransaction(long acc_id) {
		ArrayList<Transaction> allTransaction = new ArrayList<Transaction>();
		try {
			Connection con = DbConnection.getConnection();

			PreparedStatement ps = con.prepareStatement("select * from transactions  where ws_src_acct_id = ?");
			ps.setLong(1, acc_id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Transaction t = new Transaction();
				t.setWs_trxn_id(rs.getLong(1));
				t.setWs_src_acct_id(rs.getLong(2));
				t.setWs_tgt_acct_id(rs.getLong(3));
				t.setWs_amt(rs.getLong(4));
				t.setWs_op(rs.getString(5));
				t.setWs_trxn_date(rs.getTimestamp(6));
				allTransaction.add(t);
			}
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return allTransaction;
	}

	public static ArrayList<Transaction> readTransactionNumberWise(long acc_id, int n) {
		ArrayList<Transaction> transactions = readTransaction(acc_id);
		Collections.sort(transactions);
		ArrayList<Transaction> numberWiseTransactions = new ArrayList<Transaction>();
		if (transactions.size() > n) {
			for (int i = 0; i < n; i++) {
				System.out.println(transactions.get(i));
				numberWiseTransactions.add(transactions.get(i));
			}
		}
		else {
			numberWiseTransactions = transactions;
		}
		return numberWiseTransactions;
	}

	public static ArrayList<Transaction> readTransactionDateWise(long acc_id, String startDate, String endDate) {
		ArrayList<Transaction> transactions = new ArrayList<Transaction>();
		try {
			Connection con = DbConnection.getConnection();

			// String sDate1="31/12/1998";
			Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(startDate);
			System.out.println(startDate + "\t" + date1);
			Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse(endDate);
			System.out.println(endDate + "\t" + date1);

			Timestamp ts1 = new Timestamp(date1.getTime());
			Timestamp ts2 = new Timestamp(date2.getTime());
//		    ts2.setDate(ts2.getDate()+1);
			ts2.setHours(23);
			ts2.setMinutes(59);
			ts2.setSeconds(59);
			System.out.println("TS1 : " + ts1);
			System.out.println("TS2 : " + ts2);

			PreparedStatement ps = con.prepareStatement(
					"select * from transactions where ws_src_acct_id = ? and (ws_trxn_date >= ? and ws_trxn_date <= ?)");
			ps.setLong(1, acc_id);
			ps.setTimestamp(2, ts1);
			ps.setTimestamp(3, ts2);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Transaction t = new Transaction();
				t.setWs_trxn_id(rs.getLong(1));
				t.setWs_src_acct_id(rs.getLong(2));
				t.setWs_tgt_acct_id(rs.getLong(3));
				t.setWs_amt(rs.getLong(4));
				t.setWs_op(rs.getString(5));
				t.setWs_trxn_date(rs.getTimestamp(6));
				transactions.add(t);
			}
			con.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return transactions;
	}
		public static void main(String[] args) throws ParseException {
//		Transaction t = new Transaction();
//		
//		t.setWs_src_acct_id(123123123);
//		t.setWs_tgt_acct_id(123123456);
//		t.setWs_op("T");
//		t.setWs_amt(10000);

		// transactionTransfer(t);
//		long acc_id = 123123123;
//		readTransactionNumberWise(acc_id, 2);
//		Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse("07/10/1998");  
//	    Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse("17/10/1998"); 
//	    
//	    Timestamp ts1 = new Timestamp(date1.getTime());
//	    Timestamp ts2 = new Timestamp(date2.getTime());
//	    System.out.println("TS1 : "+ts1);
//	    System.out.println("TS2 : "+ts2);

		long acc_id = 142536789;
		ArrayList<Transaction> trs = readTransactionDateWise(acc_id, "16/06/2020","18/06/2020");

		for (Transaction t : trs) {
			System.out.println(t);
		}
//generatePdf(trs);
	}

}
