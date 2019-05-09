package com.ats.rusasoft.model;

public class GovtScholarships {
	
	private int govtScholarId;
	private String nameOfScheme;
	private int noOfStudBenftd;
	private String schmOffrdBy;
	private int delStatus;
	private int isActive;
	private int instId;
	private int acYearId;
	private int makerUserId;
	private String makerEntrDatetime;
	private int exInt1;
	private int exInt2;
	private String exVar1;
	private String exVar2;
	public int getGovtScholarId() {
		return govtScholarId;
	}
	public void setGovtScholarId(int govtScholarId) {
		this.govtScholarId = govtScholarId;
	}
	public String getNameOfScheme() {
		return nameOfScheme;
	}
	public void setNameOfScheme(String nameOfScheme) {
		this.nameOfScheme = nameOfScheme;
	}
	public int getNoOfStudBenftd() {
		return noOfStudBenftd;
	}
	public void setNoOfStudBenftd(int noOfStudBenftd) {
		this.noOfStudBenftd = noOfStudBenftd;
	}
	public String getSchmOffrdBy() {
		return schmOffrdBy;
	}
	public void setSchmOffrdBy(String schmOffrdBy) {
		this.schmOffrdBy = schmOffrdBy;
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
	public int getInstId() {
		return instId;
	}
	public void setInstId(int instId) {
		this.instId = instId;
	}
	public int getAcYearId() {
		return acYearId;
	}
	public void setAcYearId(int acYearId) {
		this.acYearId = acYearId;
	}
	public int getMakerUserId() {
		return makerUserId;
	}
	public void setMakerUserId(int makerUserId) {
		this.makerUserId = makerUserId;
	}
	public String getMakerEntrDatetime() {
		return makerEntrDatetime;
	}
	public void setMakerEntrDatetime(String makerEntrDatetime) {
		this.makerEntrDatetime = makerEntrDatetime;
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
		return "GovtScholarships [govtScholarId=" + govtScholarId + ", nameOfScheme=" + nameOfScheme
				+ ", noOfStudBenftd=" + noOfStudBenftd + ", schmOffrdBy=" + schmOffrdBy + ", delStatus=" + delStatus
				+ ", isActive=" + isActive + ", instId=" + instId + ", acYearId=" + acYearId + ", makerUserId="
				+ makerUserId + ", makerEntrDatetime=" + makerEntrDatetime + ", exInt1=" + exInt1 + ", exInt2=" + exInt2
				+ ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + "]";
	}
	
	

}
