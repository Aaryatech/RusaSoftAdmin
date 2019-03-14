package com.ats.rusasoft.master.model.prodetail;

public class StudAdmLocwise {

	private int studentLocId;

	private int instituteId;

	private int programId;
	private int yearId;
	private int locationId;
	private int maleStudent;
	private int femaleStudent;
	private int transStudent;
	private int locTotStudent;

	private int delStatus;
	private int isActive;
	private int makerUserId;
	private String makerDatetime;
	private int exInt1;
	private int exInt2;
	private String exVar1;
	private String exVar2;
	
	
	public int getStudentLocId() {
		return studentLocId;
	}
	public void setStudentLocId(int studentLocId) {
		this.studentLocId = studentLocId;
	}
	public int getInstituteId() {
		return instituteId;
	}
	public void setInstituteId(int instituteId) {
		this.instituteId = instituteId;
	}
	public int getProgramId() {
		return programId;
	}
	public void setProgramId(int programId) {
		this.programId = programId;
	}
	public int getYearId() {
		return yearId;
	}
	public void setYearId(int yearId) {
		this.yearId = yearId;
	}
	public int getMaleStudent() {
		return maleStudent;
	}
	public void setMaleStudent(int maleStudent) {
		this.maleStudent = maleStudent;
	}
	public int getFemaleStudent() {
		return femaleStudent;
	}
	public void setFemaleStudent(int femaleStudent) {
		this.femaleStudent = femaleStudent;
	}
	public int getTransStudent() {
		return transStudent;
	}
	public void setTransStudent(int transStudent) {
		this.transStudent = transStudent;
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
	public int getLocationId() {
		return locationId;
	}
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	
	
	
	public int getLocTotStudent() {
		return locTotStudent;
	}
	public void setLocTotStudent(int locTotStudent) {
		this.locTotStudent = locTotStudent;
	}
	
	
	@Override
	public String toString() {
		return "StudAdmLocwise [studentLocId=" + studentLocId + ", instituteId=" + instituteId + ", programId="
				+ programId + ", yearId=" + yearId + ", locationId=" + locationId + ", maleStudent=" + maleStudent
				+ ", femaleStudent=" + femaleStudent + ", transStudent=" + transStudent + ", locTotStudent="
				+ locTotStudent + ", delStatus=" + delStatus + ", isActive=" + isActive + ", makerUserId=" + makerUserId
				+ ", makerDatetime=" + makerDatetime + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exVar1="
				+ exVar1 + ", exVar2=" + exVar2 + "]";
	}


}
