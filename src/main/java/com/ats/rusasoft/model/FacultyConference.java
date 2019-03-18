package com.ats.rusasoft.model;

public class FacultyConference {
	
	private int confId;
	private int facultyId;
	private int yearId;
	private String confName;
	private String confType;
	private String confDate;
	private String confVenue;
	private String 	confFundFrom;
	private int confFundAmt;
	private int delStatus;
	private int isActive;
	private int makerUserId;
	private String makerEnterDatetime;
	private int exInt1;
	private String exVar1;
	
	public int getConfId() {
		return confId;
	}
	public void setConfId(int confId) {
		this.confId = confId;
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
	public String getConfName() {
		return confName;
	}
	public void setConfName(String confName) {
		this.confName = confName;
	}
	public String getConfType() {
		return confType;
	}
	public void setConfType(String confType) {
		this.confType = confType;
	}
	public String getConfDate() {
		return confDate;
	}
	public void setConfDate(String confDate) {
		this.confDate = confDate;
	}
	public String getConfVenue() {
		return confVenue;
	}
	public void setConfVenue(String confVenue) {
		this.confVenue = confVenue;
	}
	public String getConfFundFrom() {
		return confFundFrom;
	}
	public void setConfFundFrom(String confFundFrom) {
		this.confFundFrom = confFundFrom;
	}
	public int getConfFundAmt() {
		return confFundAmt;
	}
	public void setConfFundAmt(int confFundAmt) {
		this.confFundAmt = confFundAmt;
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
	public String getExVar1() {
		return exVar1;
	}
	public void setExVar1(String exVar1) {
		this.exVar1 = exVar1;
	}
	@Override
	public String toString() {
		return "FacultyConference [confId=" + confId + ", facultyId=" + facultyId + ", yearId=" + yearId + ", confName="
				+ confName + ", confType=" + confType + ", confDate=" + confDate + ", confVenue=" + confVenue
				+ ", confFundFrom=" + confFundFrom + ", confFundAmt=" + confFundAmt + ", delStatus=" + delStatus
				+ ", isActive=" + isActive + ", makerUserId=" + makerUserId + ", makerEnterDatetime="
				+ makerEnterDatetime + ", exInt1=" + exInt1 + ", exVar1=" + exVar1 + "]";
	}
	
	
}
