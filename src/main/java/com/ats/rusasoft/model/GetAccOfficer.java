package com.ats.rusasoft.model;

public class GetAccOfficer {
	private int officerId;

	private String accOfficerName;

	private String contactNo;
	private String email;
	
	private String joiningDate; 
	
	private String  qualificationName ;

	public int getOfficerId() {
		return officerId;
	}

	public void setOfficerId(int officerId) {
		this.officerId = officerId;
	}

	public String getAccOfficerName() {
		return accOfficerName;
	}

	public void setAccOfficerName(String accOfficerName) {
		this.accOfficerName = accOfficerName;
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

	public String getQualificationName() {
		return qualificationName;
	}

	public void setQualificationName(String qualificationName) {
		this.qualificationName = qualificationName;
	}

	@Override
	public String toString() {
		return "GetAccOfficer [officerId=" + officerId + ", accOfficerName=" + accOfficerName + ", contactNo="
				+ contactNo + ", email=" + email + ", joiningDate=" + joiningDate + ", qualificationName="
				+ qualificationName + "]";
	}
	
	
	

}
