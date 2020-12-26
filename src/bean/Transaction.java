package bean;

import java.sql.Timestamp;

public class Transaction implements Comparable<Transaction> {
	
	
	@Override
	public int compareTo(Transaction o) {
		return o.ws_trxn_date.compareTo(this.ws_trxn_date); 
	}
	
	@Override
	public String toString() {
		return "Transaction [ws_trxn_id=" + ws_trxn_id + ", ws_src_acct_id=" + ws_src_acct_id + ", ws_tgt_acct_id="
				+ ws_tgt_acct_id + ", ws_amt=" + ws_amt + ", ws_op=" + ws_op + ", ws_trxn_date=" + ws_trxn_date + "]";
	}
	public long getWs_trxn_id() {
		return ws_trxn_id;
	}
	public void setWs_trxn_id(long ws_trxn_id) {
		this.ws_trxn_id = ws_trxn_id;
	}
	public long getWs_src_acct_id() {
		return ws_src_acct_id;
	}
	public void setWs_src_acct_id(long ws_src_acct_id) {
		this.ws_src_acct_id = ws_src_acct_id;
	}
	public long getWs_tgt_acct_id() {
		return ws_tgt_acct_id;
	}
	public void setWs_tgt_acct_id(long ws_tgt_acct_id) {
		this.ws_tgt_acct_id = ws_tgt_acct_id;
	}
	public long getWs_amt() {
		return ws_amt;
	}
	public void setWs_amt(long ws_amt) {
		this.ws_amt = ws_amt;
	}
	public String getWs_op() {
		return ws_op;
	}
	public void setWs_op(String ws_op) {
		this.ws_op = ws_op;
	}
	public Timestamp getWs_trxn_date() {
		return ws_trxn_date;
	}
	public void setWs_trxn_date(Timestamp ws_trxn_date) {
		this.ws_trxn_date = ws_trxn_date;
	}
	
	long ws_trxn_id;
	long ws_src_acct_id;
	long ws_tgt_acct_id;
	long ws_amt;
	String ws_op;
	Timestamp ws_trxn_date;

}
