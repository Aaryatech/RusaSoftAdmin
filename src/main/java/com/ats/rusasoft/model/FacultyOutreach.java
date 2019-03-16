package com.ats.rusasoft.model;

public class FacultyOutreach {
	
	private int outreachId;
	
	private int facultyId ;
	
	private int yearId;
	
	private String outreachType;
	
	private String outreachName;
	
	private String outreachLevel;
	
	private String outreachDate;
	
	private int makerUserId;
	
	private String makerEnterDatetime;
	
	private int delStatus;
	
	private int isActive ;
	
	private int exInt1;
	
	private int exInt2;
	
	private String exVar1;
	
	private String exVar2;

	public int getOutreachId() {
		return outreachId;
	}

	public void setOutreachId(int outreachId) {
		this.outreachId = outreachId;
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

	public String getOutreachType() {
		return outreachType;
	}

	public void setOutreachType(String outreachType) {
		this.outreachType = outreachType;
	}

	public String getOutreachName() {
		return outreachName;
	}

	public void setOutreachName(String outreachName) {
		this.outreachName = outreachName;
	}

	public String getOutreachLevel() {
		return outreachLevel;
	}

	public void setOutreachLevel(String outreachLevel) {
		this.outreachLevel = outreachLevel;
	}

	public String getOutreachDate() {
		return outreachDate;
	}

	public void setOutreachDate(String outreachDate) {
		this.outreachDate = outreachDate;
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
		return "FacultyOutreach [outreachId=" + outreachId + ", facultyId=" + facultyId + ", yearId=" + yearId
				+ ", outreachType=" + outreachType + ", outreachName=" + outreachName + ", outreachLevel="
				+ outreachLevel + ", outreachDate=" + outreachDate + ", makerUserId=" + makerUserId
				+ ", makerEnterDatetime=" + makerEnterDatetime + ", delStatus=" + delStatus + ", isActive=" + isActive
				+ ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + "]";
	}
	
	

}
