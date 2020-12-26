package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import bean.Account;
import util.DbConnection;

public class AccountDao {
	
	
	public static long generateAccountId() {
		try {
			Connection con = DbConnection.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT MAX(ws_acct_id) from account");

			while (rs.next()) {
				long id = rs.getLong(1);
				return id == 0 ? 100000000 : id + 1;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return -1;
	}

	public static int createAccount(Account a) {
		int status = 0;
		try {
			Connection con = DbConnection.getConnection();
			PreparedStatement ps = con.prepareStatement("insert into account (ws_cust_id,ws_acct_id,ws_acct_type,ws_acct_status,ws_acct_balance,ws_acct_crdate) values(?, ?, ?, ?, ?, ?)");

			a.setWs_acct_id(AccountDao.generateAccountId());

			System.out.println(a.getWs_cust_id());
			System.out.println(a.getWs_acct_id());
			System.out.println(a.getWs_acct_type());
			System.out.println(a.getWs_acct_status());
			System.out.println(a.getWs_acct_balance());
			System.out.println(a.getWs_acct_crdate());
			
			

			ps.setLong(1, a.getWs_cust_id());
			ps.setLong(2, a.getWs_acct_id());
			ps.setString(3, a.getWs_acct_type());
			ps.setString(4, a.getWs_acct_status());
			ps.setLong(5, a.getWs_acct_balance());
			ps.setTimestamp(6, a.getWs_acct_crdate());
			status = ps.executeUpdate();
			System.out.println(status);
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return status;
	}

	public static ArrayList<Account> readAllAccount() {
		ArrayList<Account> account = new ArrayList<>();
		try {
			Connection con = DbConnection.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from account");
			while (rs.next()) {
				Account a = new Account();
				a.setWs_cust_id(rs.getLong(1));
				a.setWs_acct_id(rs.getLong(2));
				a.setWs_acct_type(rs.getString(3));
				a.setWs_acct_status(rs.getString(4));
				a.setWs_acct_balance(rs.getLong(5));
				a.setWs_acct_crdate(rs.getTimestamp(6));
				account.add(a);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return account;
	}

	// fetch account details based on account ID
	public static Account readAccount(Account a) {
		try {
			Connection con = DbConnection.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from account where ws_acct_id = ?");
			ps.setLong(1, a.getWs_acct_id());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				a.setWs_cust_id(rs.getLong(1));
//				a.setWs_acct_id(rs.getLong(2));
				a.setWs_acct_type(rs.getString(3));
				a.setWs_acct_status(rs.getString(4));
				a.setWs_acct_balance(rs.getLong(5));
				a.setWs_acct_crdate(rs.getTimestamp(6));
				
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return a;
	}

	// fetch account details based on customer ID bcz one customer id may have more than 1 account associated with it.
	public static ArrayList<Account> readAllCustomerAccount(Account a) {
		ArrayList<Account> account = new ArrayList<>();
		try {
			Connection con = DbConnection.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from account where ws_cust_id = ?");
			ps.setLong(1, a.getWs_cust_id());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Account acc = new Account();
				
				acc.setWs_cust_id(rs.getLong(1));
				acc.setWs_acct_id(rs.getLong(2));
				acc.setWs_acct_type(rs.getString(3));
				acc.setWs_acct_status(rs.getString(4));
				acc.setWs_acct_balance(rs.getLong(5));
				acc.setWs_acct_crdate(rs.getTimestamp(6));
				

				account.add(acc);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return account;
	}

	// update account status using accountID
	public static int updateAccountStatus(Account a) {
		int status = 0;
		try {
			Connection con = DbConnection.getConnection();
			PreparedStatement ps = con.prepareStatement("update account set ws_acct_status = ? where ws_acct_id = ?");
			ps.setString(1, "I");
			ps.setLong(2, a.getWs_acct_id());
			status = ps.executeUpdate();
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return status;
	}

	// reset account status using accountID
	public static int resetAccountStatus(Account a) {
		int status = 0;
		try {
			Connection con = DbConnection.getConnection();
			PreparedStatement ps = con.prepareStatement("update account set ws_acct_status = ? where ws_acct_id = ?");
			ps.setString(1, "A");
			ps.setLong(2, a.getWs_acct_id());
			status = ps.executeUpdate();
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return status;
	}

	// update account status using customerID
	public static int updateAccountStatusUsingCustId(long cust_id) {
		int status = 0;
		try {
			Connection con = DbConnection.getConnection();
			PreparedStatement ps = con.prepareStatement("update account set ws_acct_status = ? where ws_cust_id = ?");
			ps.setString(1, "I");

			ps.setLong(2, cust_id);
			status = ps.executeUpdate();
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return status;
	}

	// reset account status using customerID
	public static int resetAccountStatusUsingCustId(long cust_id) {
		int status = 0;
		try {
			Connection con = DbConnection.getConnection();
			PreparedStatement ps = con.prepareStatement("update account set ws_acct_status = ? where ws_cust_id = ?");
			ps.setString(1, "I");
		
			ps.setLong(2, cust_id);
			status = ps.executeUpdate();
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return status;
	}

//	public static int deleteAccount(Account a)
//	{
//		int status = 0;
//		try
//		{
//			Connection con = DbConnection.getConnection();
//			PreparedStatement ps = con.prepareStatement("delete from account where ws_acct_id = ?");
//			ps.setLong(1, a.getWs_acct_id());
//			status = ps.executeUpdate();
//			con.close();
//		}
//		catch (Exception ex) 
//		{
//			ex.printStackTrace();
//		}
//		return status;
//	}

	public static void main(String args[]) {
//		Account a = new Account();
//		a.setWs_cust_id(123456789);
//		a.setWs_acct_id(100000000);
//		a.setWs_acct_id(AccountDao.generateCustomerId());
//		a.setWs_acct_type("S");
//		a.setWs_acct_balance(1000000);
//		a.setWs_acct_crdate(Timestamp.valueOf("2020-06-16 10:10:10.0"));
//		a.setWs_acct_duration(10);
//		a.setWs_msg("hello");
//		a.setWs_acct_crdate(Timestamp.valueOf("2020-06-17 10:10:10.0"));
//		System.out.println(createAccount(a));
	}


}
