package com.CMPDI.CPRMSE.NE.poratl.models;




import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="cprmsecmaster")
public class AppUser {


	@Id
	@Column(unique=true, nullable=false)
	private String employeecode;
    private String dob; // Use Date type if necessary
    private String name;
    private String aadhar;
    private String location;
    private String medical_card_no;
    private String role;
    private String password;
    private String claiment_name;
    private String claiment_aadhar;
    private String modified_by;
    private String modified_onn;
	
    
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmployeecode() {
		return employeecode;
	}
	public void setEmployeecode(String employeecode) {
		this.employeecode = employeecode;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAadhar() {
		return aadhar;
	}
	public void setAadhar(String aadhar) {
		this.aadhar = aadhar;
	}
	
	
	public String getMedical_card_no() {
		return medical_card_no;
	}
	public void setMedical_card_no(String medical_card_no) {
		this.medical_card_no = medical_card_no;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getClaiment_name() {
		return claiment_name;
	}
	public void setClaiment_name(String claiment_name) {
		this.claiment_name = claiment_name;
	}
	public String getClaiment_aadhar() {
		return claiment_aadhar;
	}
	public void setClaiment_aadhar(String claiment_aadhar) {
		this.claiment_aadhar = claiment_aadhar;
	}
	public String getModified_by() {
		return modified_by;
	}
	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}
	public String getModified_onn() {
		return modified_onn;
	}
	public void setModified_onn(String modified_onn) {
		this.modified_onn = modified_onn;
	}
	
	
	
    
}
