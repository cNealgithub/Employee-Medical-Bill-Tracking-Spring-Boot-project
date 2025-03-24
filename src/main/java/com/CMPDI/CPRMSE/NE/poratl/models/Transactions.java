package com.CMPDI.CPRMSE.NE.poratl.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class Transactions {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	private String employeecode;
    private String year;
    private String month;
    private String claimed;
    private String passed;
    private String remarks;
    private String transaction_id;
    
    @ManyToOne
    @JoinColumn(name = "employeecode", referencedColumnName = "employeecode", insertable = false, updatable = false)
    private AppUser appUser;
    
   public Transactions(){
	   
   };
    
    
    
	public Transactions(String employeecode, String year, String month, String claimed, String passed,
			String remarks) {
		super();
		this.employeecode = employeecode;
		this.year = year;
		this.month = month;
		this.claimed = claimed;
		this.passed = passed;
		this.remarks = remarks;
		
	}


	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEmployeecode() {
		return employeecode;
	}
	public void setEmployeecode(String employeecode) {
		this.employeecode = employeecode;
	}
	
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getClaimed() {
		return claimed;
	}
	public void setClaimed(String claimed) {
		this.claimed = claimed;
	}
	public String getPassed() {
		return passed;
	}
	public void setPassed(String passed) {
		this.passed = passed;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}
    
}
