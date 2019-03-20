package com.ats.rusasoft.model;

public class StaffList {
	private int facultyId;
	private String facultyName;
	private String joiningDate;
	private String realivingDate;
	private String contactNo;
	private String email;
	private int currentDesignationId ;
	private String qualificationName;
	private String designationName;
	private String deptName;
	
	
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDesignationName() {
		return designationName;
	}
	public void setDesignationName(String designationName) {
		this.designationName = designationName;
	}
	public String getQualificationName() {
		return qualificationName;
	}
	public void setQualificationName(String qualificationName) {
		this.qualificationName = qualificationName;
	}
	public int getFacultyId() {
		return facultyId;
	}
	public void setFacultyId(int facultyId) {
		this.facultyId = facultyId;
	}
	public String getFacultyName() {
		return facultyName;
	}
	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
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
	public int getCurrentDesignationId() {
		return currentDesignationId;
	}
	public void setCurrentDesignationId(int currentDesignationId) {
		this.currentDesignationId = currentDesignationId;
	}
	@Override
	public String toString() {
		return "StaffList [facultyId=" + facultyId + ", facultyName=" + facultyName + ", joiningDate=" + joiningDate
				+ ", realivingDate=" + realivingDate + ", contactNo=" + contactNo + ", email=" + email
				+ ", currentDesignationId=" + currentDesignationId + ", qualificationName=" + qualificationName
				+ ", designationName=" + designationName + ", deptName=" + deptName + "]";
	}
		
}
