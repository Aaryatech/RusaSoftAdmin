package com.ats.rusasoft.faculty.model;

public class FacultyAcademic {
	
	private int fAcaId;
	
	private int facultyId;
	private int fQualificationId;
	
	private String fClass;
	private String  fUniversity;
	private String fPassingYear;

	private int delStatus;
	private int isActive;

	private int makerUserId;
	private String makerEnterDatetime;

	private int exInt1;
	private int exInt2;
	private String exVar1;
	private String exVar2;
	
	public int getfAcaId() {
		return fAcaId;
	}
	public void setfAcaId(int fAcaId) {
		this.fAcaId = fAcaId;
	}
	public int getFacultyId() {
		return facultyId;
	}
	public void setFacultyId(int facultyId) {
		this.facultyId = facultyId;
	}
	public int getfQualificationId() {
		return fQualificationId;
	}
	public void setfQualificationId(int fQualificationId) {
		this.fQualificationId = fQualificationId;
	}
	public String getfClass() {
		return fClass;
	}
	public void setfClass(String fClass) {
		this.fClass = fClass;
	}
	public String getfUniversity() {
		return fUniversity;
	}
	public void setfUniversity(String fUniversity) {
		this.fUniversity = fUniversity;
	}
	public String getfPassingYear() {
		return fPassingYear;
	}
	public void setfPassingYear(String fPassingYear) {
		this.fPassingYear = fPassingYear;
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
	public String getMakerEnterDatetime() {
		return makerEnterDatetime;
	}
	public void setMakerEnterDatetime(String makerEnterDatetime) {
		this.makerEnterDatetime = makerEnterDatetime;
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
		return "FacultyAcademic [fAcaId=" + fAcaId + ", facultyId=" + facultyId + ", fQualificationId="
				+ fQualificationId + ", fClass=" + fClass + ", fUniversity=" + fUniversity + ", fPassingYear="
				+ fPassingYear + ", delStatus=" + delStatus + ", isActive=" + isActive + ", makerUserId=" + makerUserId
				+ ", makerEnterDatetime=" + makerEnterDatetime + ", exInt1=" + exInt1 + ", exInt2=" + exInt2
				+ ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + "]";
	}
	
}
