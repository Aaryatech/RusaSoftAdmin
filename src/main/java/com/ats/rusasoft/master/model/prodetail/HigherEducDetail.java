package com.ats.rusasoft.master.model.prodetail;

public class HigherEducDetail {

	private int educationDetailId;

	private int instituteId;

	private int programType;
	private int yearId;
	private int proceedingTo;

	private int noStudent;

	private int delStatus;
	private int isActive;
	private int makerUserId;
	private String makerDatetime;
	private int exInt1;
	private int exInt2;
	private String exVar1;
	private String exVar2;
	
	public int getEducationDetailId() {
		return educationDetailId;
	}
	public void setEducationDetailId(int educationDetailId) {
		this.educationDetailId = educationDetailId;
	}
	public int getInstituteId() {
		return instituteId;
	}
	public void setInstituteId(int instituteId) {
		this.instituteId = instituteId;
	}
	public int getProgramType() {
		return programType;
	}
	public void setProgramType(int programType) {
		this.programType = programType;
	}
	public int getYearId() {
		return yearId;
	}
	public void setYearId(int yearId) {
		this.yearId = yearId;
	}
	public int getProceedingTo() {
		return proceedingTo;
	}
	public void setProceedingTo(int proceedingTo) {
		this.proceedingTo = proceedingTo;
	}
	public int getNoStudent() {
		return noStudent;
	}
	public void setNoStudent(int noStudent) {
		this.noStudent = noStudent;
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
	public int getMakerUserId() {
		return makerUserId;
	}
	public void setMakerUserId(int makerUserId) {
		this.makerUserId = makerUserId;
	}
	public String getMakerDatetime() {
		return makerDatetime;
	}
	public void setMakerDatetime(String makerDatetime) {
		this.makerDatetime = makerDatetime;
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
	
	@Override
	public String toString() {
		return "HigherEducDetail [educationDetailId=" + educationDetailId + ", instituteId=" + instituteId
				+ ", programType=" + programType + ", yearId=" + yearId + ", proceedingTo=" + proceedingTo
				+ ", noStudent=" + noStudent + ", delStatus=" + delStatus + ", isActive=" + isActive + ", makerUserId="
				+ makerUserId + ", makerDatetime=" + makerDatetime + ", exInt1=" + exInt1 + ", exInt2=" + exInt2
				+ ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + "]";
	}
	

}
