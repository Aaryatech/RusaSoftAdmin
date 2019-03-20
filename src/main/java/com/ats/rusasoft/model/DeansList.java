package com.ats.rusasoft.model;


public class DeansList {

	private int deanId;
	private String deanName;
	private String contactNo;
	private String email;
	private String joiningDate;
	private String realivingDate;
	private String qualificationName;

	public int getDeanId() {
		return deanId;
	}
	public void setDeanId(int deanId) {
		this.deanId = deanId;
	}
	public String getQualificationName() {
		return qualificationName;
	}
	public void setQualificationName(String qualificationName) {
		this.qualificationName = qualificationName;
	}
	public String getDeanName() {
		return deanName;
	}
	public void setDeanName(String deanName) {
		this.deanName = deanName;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getJoiningDate() {
		return joiningDate;
	}
	public void setJoiningDate(String joiningDate) {
		this.joiningDate = joiningDate;
	}
	public String getRealivingDate() {
		return realivingDate;
	}
	public void setRealivingDate(String realivingDate) {
		this.realivingDate = realivingDate;
	}
	@Override
	public String toString() {
		return "DeansList [deanId=" + deanId + ", deanName=" + deanName + ", contactNo=" + contactNo + ", email="
				+ email + ", joiningDate=" + joiningDate + ", realivingDate=" + realivingDate + ", qualificationName="
				+ qualificationName + "]";
	}
		
}
