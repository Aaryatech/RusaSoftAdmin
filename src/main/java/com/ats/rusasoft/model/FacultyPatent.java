package com.ats.rusasoft.model;

public class FacultyPatent {
	
	private int patentId;
	
	private int facultyId ;
	
	private int yearId;
	
	private String patentFileNo;
	
	private String patentTitle;
	
	private String patentFilingDate;
	
	private String patentGuideName;
	
	private String patentPubDate;
	
	private int delStatus;
	
	private int isActive ;
	
	private int makerUserId;
	
	private String makerEnterDatetime;
	
	private int exInt1;
	
	private int exInt2;
	
	private String exVar1;
	
	private String exVar2;

	public int getPatentId() {
		return patentId;
	}

	public void setPatentId(int patentId) {
		this.patentId = patentId;
	}

	public int getFacultyId() {
		return facultyId;
	}

	public void setFacultyId(int facultyId) {
		this.facultyId = facultyId;
	}

	public int getYearId() {
		return yearId;
	}

	public void setYearId(int yearId) {
		this.yearId = yearId;
	}

	public String getPatentFileNo() {
		return patentFileNo;
	}

	public void setPatentFileNo(String patentFileNo) {
		this.patentFileNo = patentFileNo;
	}

	public String getPatentTitle() {
		return patentTitle;
	}

	public void setPatentTitle(String patentTitle) {
		this.patentTitle = patentTitle;
	}

	public String getPatentFilingDate() {
		return patentFilingDate;
	}

	public void setPatentFilingDate(String patentFilingDate) {
		this.patentFilingDate = patentFilingDate;
	}

	public String getPatentGuideName() {
		return patentGuideName;
	}

	public void setPatentGuideName(String patentGuideName) {
		this.patentGuideName = patentGuideName;
	}

	public String getPatentPubDate() {
		return patentPubDate;
	}

	public void setPatentPubDate(String patentPubDate) {
		this.patentPubDate = patentPubDate;
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
		return "FacultyPatent [patentId=" + patentId + ", facultyId=" + facultyId + ", yearId=" + yearId
				+ ", patentFileNo=" + patentFileNo + ", patentTitle=" + patentTitle + ", patentFilingDate="
				+ patentFilingDate + ", patentGuideName=" + patentGuideName + ", patentPubDate=" + patentPubDate
				+ ", delStatus=" + delStatus + ", isActive=" + isActive + ", makerUserId=" + makerUserId
				+ ", makerEnterDatetime=" + makerEnterDatetime + ", exInt1=" + exInt1 + ", exInt2=" + exInt2
				+ ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + "]";
	}
	
	

}
