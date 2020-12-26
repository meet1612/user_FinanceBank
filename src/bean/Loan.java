package bean;

import java.sql.Timestamp;

public class Loan {

	int loan_id;
	int acc_id;
	long loan_amt;
	
	int status;
	int months;
	float emi;
	public int getLoan_id() {
		return loan_id;
	}
	public void setLoan_id(int loan_id) {
		this.loan_id = loan_id;
	}
	public int getAcc_id() {
		return acc_id;
	}
	public void setAcc_id(int acc_id) {
		this.acc_id = acc_id;
	}
	
	public long getLoan_amt() {
		return loan_amt;
	}
	public void setLoan_amt(long loan_amt) {
		this.loan_amt = loan_amt;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getMonths() {
		return months;
	}
	public void setMonths(int months) {
		this.months = months;
	}
	
	public float getEmi() {
		return emi;
	}
	public void setEmi(float emi) {
		this.emi = emi;
	}
	@Override
	public String toString() {
		return "Loan [loan_id=" + loan_id + ", acc_id=" + acc_id + ", loan_amt=" + loan_amt + ", status=" + status
				+ ", months=" + months + ", emi=" + emi + "]";
	}
	
	
}
