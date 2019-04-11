package com.ats.rusasoft.model;

public class NewDeanList {

	private int facultyId;
	private String deptId;
	private String facultyFirstName;
	private int highestQualification;
	private int teachingTo;
	private int currentDesignationId;
	private String joiningDate;
	private String realivingDate;
	private String contactNo;
	private String email;
	private int makerUserId;
	private String deptName;
	private String qualificationName;
	private String designationName;

	public int getFacultyId() {
		return facultyId;
	}

	public void setFacultyId(int facultyId) {
		this.facultyId = facultyId;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getFacultyFirstName() {
		return facultyFirstName;
	}

	public void setFacultyFirstName(String facultyFirstName) {
		this.facultyFirstName = facultyFirstName;
	}

	public int getHighestQualification() {
		return highestQualification;
	}

	public void setHighestQualification(int highestQualification) {
		this.highestQualification = highestQualification;
	}

	public int getTeachingTo() {
		return teachingTo;
	}

	public void setTeachingTo(int teachingTo) {
		this.teachingTo = teachingTo;
	}

	public int getCurrentDesignationId() {
		return currentDesignationId;
	}

	public void setCurrentDesignationId(int currentDesignationId) {
		this.currentDesignationId = currentDesignationId;
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

	public int getMakerUserId() {
		return makerUserId;
	}

	public void setMakerUserId(int makerUserId) {
		this.makerUserId = makerUserId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getQualificationName() {
		return qualificationName;
	}

	public void setQualificationName(String qualificationName) {
		this.qualificationName = qualificationName;
	}

	public String getDesignationName() {
		return designationName;
	}

	public void setDesignationName(String designationName) {
		this.designationName = designationName;
	}

	@Override
	public String toString() {
		return "NewDeanList [facultyId=" + facultyId + ", deptId=" + deptId + ", facultyFirstName=" + facultyFirstName
				+ ", highestQualification=" + highestQualification + ", teachingTo=" + teachingTo
				+ ", currentDesignationId=" + currentDesignationId + ", joiningDate=" + joiningDate + ", realivingDate="
				+ realivingDate + ", contactNo=" + contactNo + ", email=" + email + ", makerUserId=" + makerUserId
				+ ", deptName=" + deptName + ", qualificationName=" + qualificationName + ", designationName="
				+ designationName + "]";
	}

}
