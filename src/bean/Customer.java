package bean;

import java.sql.Timestamp;

public class Customer {
	
	
	long ws_ssn;
	long ws_cust_id;
	String ws_name;
	String ws_adrs;
	String ws_city;
	String ws_state;
	int ws_age;
	char ws_status;
	String ws_email_id;
	String ws_password;
	
	public String getWs_city() {
		return ws_city;
	}
	public void setWs_city(String ws_city) {
		this.ws_city = ws_city;
	}
	public String getWs_state() {
		return ws_state;
	}
	public void setWs_state(String ws_state) {
		this.ws_state = ws_state;
	}
	public char getWs_status() {
		return ws_status;
	}
	public void setWs_status(char ws_status) {
		this.ws_status = ws_status;
	}
	
	public String getWs_email_id() {
		return ws_email_id;
	}
	public void setWs_email_id(String ws_email_id) {
		this.ws_email_id = ws_email_id;
	}
	public String getWs_password() {
		return ws_password;
	}
	public void setWs_password(String ws_password) {
		this.ws_password = ws_password;
	}
	public long getWs_ssn() 
	{
		return ws_ssn;
	}
	public void setWs_ssn(long ws_ssn)
	{
		this.ws_ssn = ws_ssn;
	}
	public long getWs_cust_id() 
	{
		return ws_cust_id;
	}
	public void setWs_cust_id(long ws_cust_id) 
	{
		this.ws_cust_id = ws_cust_id;
	}
	public String getWs_name() 
	{
		return ws_name;
	}
	public void setWs_name(String ws_name) 
	{
		this.ws_name = ws_name;
	}
	public String getWs_adrs() 
	{
		return ws_adrs;
	}
	public void setWs_adrs(String ws_adrs) 
	{
		this.ws_adrs = ws_adrs;
	}
	public int getWs_age() 
	{
		return ws_age;
	}
	public void setWs_age(int ws_age) 
	{
		this.ws_age = ws_age;
	}

}
