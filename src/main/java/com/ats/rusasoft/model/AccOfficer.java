package com.ats.rusasoft.model;

public class AccOfficer {

	private int officerId;

	private int instituteId;

	private String accOfficerName;

	private String contactNo;
	private String email;
	
	private int qualificationId;
	private String joiningDate; 
	private String realivingDate; 
	private int makerUserId; 
	private int makerEnterDatetime ;
 	
 	private int delStatus;
	private int isActive;
 	
 	private int exInt1;
	private String exVar1;
	
	public int getOfficerId() {
		return officerId;
	}
	public void setOfficerId(int officerId) {
		this.officerId = officerId;
	}
	public int getInstituteId() {
		return instituteId;
	}
	public void setInstituteId(int instituteId) {
		this.instituteId = instituteId;
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
	public int getQualificationId() {
		return qualificationId;
	}
	public void setQualificationId(int qualificationId) {
		this.qualificationId = qualificationId;
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
	public int getMakerUserId() {
		return makerUserId;
	}
	public void setMakerUserId(int makerUserId) {
		this.makerUserId = makerUserId;
	}
	public int getMakerEnterDatetime() {
		return makerEnterDatetime;
	}
	public void setMakerEnterDatetime(int makerEnterDatetime) {
		this.makerEnterDatetime = makerEnterDatetime;
	}
	public int getDelStatus() {
		return delStatus;
	}
	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}
	public int getIsActive() {
		return isActive;
	}
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	public int getExInt1() {
		return exInt1;
	}
	public void setExInt1(int exInt1) {
		this.exInt1 = exInt1;
	}
	public String getExVar1() {
		return exVar1;
	}
	public void setExVar1(String exVar1) {
		this.exVar1 = exVar1;
	}
	
	@Override
	public String toString() {
		return "AccOfficer [officerId=" + officerId + ", instituteId=" + instituteId + ", accOfficerName="
				+ accOfficerName + ", contactNo=" + contactNo + ", email=" + email + ", qualificationId="
				+ qualificationId + ", joiningDate=" + joiningDate + ", realivingDate=" + realivingDate
				+ ", makerUserId=" + makerUserId + ", makerEnterDatetime=" + makerEnterDatetime + ", delStatus="
				+ delStatus + ", isActive=" + isActive + ", exInt1=" + exInt1 + ", exVar1=" + exVar1 + "]";
	}
	
}
