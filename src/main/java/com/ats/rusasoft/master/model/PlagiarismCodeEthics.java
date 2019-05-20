package com.ats.rusasoft.master.model;

public class PlagiarismCodeEthics {

	private int plagCodeEthcId;
	private int isSoftwrAvabl;
	private String nameOfSoftwr;
	private String mechDetectPlag;
	private String urlLink;
	private int isActive;
	private int delStatus;
	private int instId;
	private int acYearId;
	private int makerUserId;
	private String makerDatetime;
	private int exInt1;
	private int exInt2;
	private String exVar1;
	private String exVar2;
	
	public int getAcYearId() {
		return acYearId;
	}
	public void setAcYearId(int acYearId) {
		this.acYearId = acYearId;
	}
	public int getPlagCodeEthcId() {
		return plagCodeEthcId;
	}
	public void setPlagCodeEthcId(int plagCodeEthcId) {
		this.plagCodeEthcId = plagCodeEthcId;
	}
	public int getIsSoftwrAvabl() {
		return isSoftwrAvabl;
	}
	public void setIsSoftwrAvabl(int isSoftwrAvabl) {
		this.isSoftwrAvabl = isSoftwrAvabl;
	}

	public String getNameOfSoftwr() {
		return nameOfSoftwr;
	}
	public void setNameOfSoftwr(String nameOfSoftwr) {
		this.nameOfSoftwr = nameOfSoftwr;
	}
	public String getMechDetectPlag() {
		return mechDetectPlag;
	}
	public void setMechDetectPlag(String mechDetectPlag) {
		this.mechDetectPlag = mechDetectPlag;
	}
	public String getUrlLink() {
		return urlLink;
	}
	public void setUrlLink(String urlLink) {
		this.urlLink = urlLink;
	}
	public int getIsActive() {
		return isActive;
	}
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	public int getDelStatus() {
		return delStatus;
	}
	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}
	public int getInstId() {
		return instId;
	}
	public void setInstId(int instId) {
		this.instId = instId;
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
		return "PlagiarismCodeEthics [plagCodeEthcId=" + plagCodeEthcId + ", isSoftwrAvabl=" + isSoftwrAvabl
				+ ", nameOfSoftwr=" + nameOfSoftwr + ", mechDetectPlag=" + mechDetectPlag + ", urlLink=" + urlLink
				+ ", isActive=" + isActive + ", delStatus=" + delStatus + ", instId=" + instId + ", acYearId="
				+ acYearId + ", makerUserId=" + makerUserId + ", makerDatetime=" + makerDatetime + ", exInt1=" + exInt1
				+ ", exInt2=" + exInt2 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + "]";
	}
	
		
}
