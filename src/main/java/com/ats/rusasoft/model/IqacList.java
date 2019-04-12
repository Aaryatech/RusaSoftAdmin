package com.ats.rusasoft.model;

public class IqacList {

	private int facultyId;
	private String facultyFirstName;
	private String joiningDate;
	private String contactNo;
	private String email;
	private String designationName;
	public int getFacultyId() {
		return facultyId;
	}
	public void setFacultyId(int facultyId) {
		this.facultyId = facultyId;
	}
	public String getFacultyFirstName() {
		return facultyFirstName;
	}
	public void setFacultyFirstName(String facultyFirstName) {
		this.facultyFirstName = facultyFirstName;
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
		return "IqacList [facultyId=" + facultyId + ", facultyFirstName=" + facultyFirstName + ", joiningDate="
				+ joiningDate + ", contactNo=" + contactNo + ", email=" + email + ", designationName=" + designationName
				+ "]";
	}
	
	
}
