package com.ats.rusasoft.model;

import java.util.Date;

public class IntelPrpoRight {

	private int conId;
	private int instituteId;
	private int yearId;
	private String conName;
	private String conFromdt;
	private String conTodt;
	private int conPcount;
	private int delStatus;
	private int isActive;
	private int makerUserId;
	private String makerDatetime;
	private int exInt1;
	private String exVar1;
	private String reportLink;
	private String establishDate;
	
	
	public String getReportLink() {
		return reportLink;
	}
	public void setReportLink(String reportLink) {
		this.reportLink = reportLink;
	}
	public String getEstablishDate() {
		return establishDate;
	}
	public void setEstablishDate(String establishDate) {
		this.establishDate = establishDate;
	}
	public int getConId() {
		return conId;
	}
	public void setConId(int conId) {
		this.conId = conId;
	}
	public int getInstituteId() {
		return instituteId;
	}
	public void setInstituteId(int instituteId) {
		this.instituteId = instituteId;
	}
	public int getYearId() {
		return yearId;
	}
	public void setYearId(int yearId) {
		this.yearId = yearId;
	}
	public String getConName() {
		return conName;
	}
	public void setConName(String conName) {
		this.conName = conName;
	}
	public String getConFromdt() {
		return conFromdt;
	}
	public void setConFromdt(String conFromdt) {
		this.conFromdt = conFromdt;
	}
	public String getConTodt() {
		return conTodt;
	}
	public void setConTodt(String conTodt) {
		this.conTodt = conTodt;
	}
	public int getConPcount() {
		return conPcount;
	}
	public void setConPcount(int conPcount) {
		this.conPcount = conPcount;
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
	public String getExVar1() {
		return exVar1;
	}
	public void setExVar1(String exVar1) {
		this.exVar1 = exVar1;
	}
	@Override
	public String toString() {
		return "IntelPrpoRight [conId=" + conId + ", instituteId=" + instituteId + ", yearId=" + yearId + ", conName="
				+ conName + ", conFromdt=" + conFromdt + ", conTodt=" + conTodt + ", conPcount=" + conPcount
				+ ", delStatus=" + delStatus + ", isActive=" + isActive + ", makerUserId=" + makerUserId
				+ ", makerDatetime=" + makerDatetime + ", exInt1=" + exInt1 + ", exVar1=" + exVar1 + ", reportLink="
				+ reportLink + ", establishDate=" + establishDate + "]";
	}
	
}
