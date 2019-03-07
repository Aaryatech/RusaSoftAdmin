package com.ats.rusasoft.model;

public class IqacList {

	private int iqacId;
	private String iqacName;
	private String joiningDate;
	private String contactNo;
	private String email;
	private String designationName;
	public int getIqacId() {
		return iqacId;
	}
	public void setIqacId(int iqacId) {
		this.iqacId = iqacId;
	}
	public String getIqacName() {
		return iqacName;
	}
	public void setIqacName(String iqacName) {
		this.iqacName = iqacName;
	}
	public String getJoiningDate() {
		return joiningDate;
	}
	public void setJoiningDate(String joiningDate) {
		this.joiningDate = joiningDate;
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
	public String getDesignationName() {
		return designationName;
	}
	public void setDesignationName(String designationName) {
		this.designationName = designationName;
	}
	@Override
	public String toString() {
		return "IqacList [iqacId=" + iqacId + ", iqacName=" + iqacName + ", joiningDate=" + joiningDate + ", contactNo="
				+ contactNo + ", email=" + email + ", designationName=" + designationName + "]";
	}
	
	
	

}
