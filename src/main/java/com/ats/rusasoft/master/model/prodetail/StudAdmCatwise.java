package com.ats.rusasoft.master.model.prodetail;

public class StudAdmCatwise {
	

	private int studentCatId;
	
	private int instituteId;
	
	private int programId;
	private int yearId;
	private int castId;
	private int maleStudent;
	private int femaleStudent;
	private int transStudent;
	private int catTotStudent;
	
	
	private int delStatus;
	private int isActive;
	private int makerUserId;
	private String makerDatetime;
	private int exInt1;
	private int exInt2;
	private String exVar1;
	private String exVar2;
	public int getStudentCatId() {
		return studentCatId;
	}
	public void setStudentCatId(int studentCatId) {
		this.studentCatId = studentCatId;
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
	public int getCastId() {
		return castId;
	}
	public void setCastId(int castId) {
		this.castId = castId;
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
	
	
	
	public int getCatTotStudent() {
		return catTotStudent;
	}
	public void setCatTotStudent(int catTotStudent) {
		this.catTotStudent = catTotStudent;
	}
	@Override
	public String toString() {
		return "StudAdmCatwise [studentCatId=" + studentCatId + ", instituteId=" + instituteId + ", programId="
				+ programId + ", yearId=" + yearId + ", castId=" + castId + ", maleStudent=" + maleStudent
				+ ", femaleStudent=" + femaleStudent + ", transStudent=" + transStudent + ", catTotStudent="
				+ catTotStudent + ", delStatus=" + delStatus + ", isActive=" + isActive + ", makerUserId=" + makerUserId
				+ ", makerDatetime=" + makerDatetime + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exVar1="
				+ exVar1 + ", exVar2=" + exVar2 + "]";
	}

}
