package com.ats.rusasoft.model;


public class Hod {

	private int hodId;
	private int instituteId;

	private String deptId;
	
 	private int highestQualificationId;
 	
 	private String hodName;
 	private String contactNo;
	
 	private String email;
 	
 	private int delStatus;
	private int isActive;
 	private int isEnrollSystem;
 	
 	private int makerId;
 	 private String makerDate;
 	 
 	private int exInt1;
	private int exInt2;

	private String exVar1;
	private String exVar2;
	
	private int editBy;
	private String updateDatetime;
	
	public int getHodId() {
		return hodId;
	}
	public void setHodId(int hodId) {
		this.hodId = hodId;
	}
	public int getInstituteId() {
		return instituteId;
	}
	public void setInstituteId(int instituteId) {
		this.instituteId = instituteId;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public int getHighestQualificationId() {
		return highestQualificationId;
	}
	public void setHighestQualificationId(int highestQualificationId) {
		this.highestQualificationId = highestQualificationId;
	}
	public String getHodName() {
		return hodName;
	}
	public void setHodName(String hodName) {
		this.hodName = hodName;
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
	public int getIsEnrollSystem() {
		return isEnrollSystem;
	}
	public void setIsEnrollSystem(int isEnrollSystem) {
		this.isEnrollSystem = isEnrollSystem;
	}
	public int getMakerId() {
		return makerId;
	}
	public void setMakerId(int makerId) {
		this.makerId = makerId;
	}
	public String getMakerDate() {
		return makerDate;
	}
	public void setMakerDate(String makerDate) {
		this.makerDate = makerDate;
	}
	public int getExInt1() {
		return exInt1;
	}
	public void setExInt1(int exInt1) {
		this.exInt1 = exInt1;
	}
	public int getExInt2() {
		return exInt2;
	}
	public void setExInt2(int exInt2) {
		this.exInt2 = exInt2;
	}
	public String getExVar1() {
		return exVar1;
	}
	public void setExVar1(String exVar1) {
		this.exVar1 = exVar1;
	}
	public String getExVar2() {
		return exVar2;
	}
	public void setExVar2(String exVar2) {
		this.exVar2 = exVar2;
	}
	public int getEditBy() {
		return editBy;
	}
	public void setEditBy(int editBy) {
		this.editBy = editBy;
	}
	public String getUpdateDatetime() {
		return updateDatetime;
	}
	public void setUpdateDatetime(String updateDatetime) {
		this.updateDatetime = updateDatetime;
	}
	
	@Override
	public String toString() {
		return "Hod [hodId=" + hodId + ", instituteId=" + instituteId + ", deptId=" + deptId
				+ ", highestQualificationId=" + highestQualificationId + ", hodName=" + hodName + ", contactNo="
				+ contactNo + ", email=" + email + ", delStatus=" + delStatus + ", isActive=" + isActive
				+ ", isEnrollSystem=" + isEnrollSystem + ", makerId=" + makerId + ", makerDate=" + makerDate
				+ ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2
				+ ", editBy=" + editBy + ", updateDatetime=" + updateDatetime + "]";
	}
	

}
