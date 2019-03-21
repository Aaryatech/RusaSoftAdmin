package com.ats.rusasoft.model.instprofile;

public class QualityInitiative {

	private int qualityInitiativeId;
	
	private String qualityInitiativeName;
	
	private int delStatus;
	private int isActive;
	
	private int makerUserId;
	private String makerDatetime;
	
	private String exVar1;
	
	public int getQualityInitiativeId() {
		return qualityInitiativeId;
	}
	public void setQualityInitiativeId(int qualityInitiativeId) {
		this.qualityInitiativeId = qualityInitiativeId;
	}
	public String getQualityInitiativeName() {
		return qualityInitiativeName;
	}
	public void setQualityInitiativeName(String qualityInitiativeName) {
		this.qualityInitiativeName = qualityInitiativeName;
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
	public String getExVar1() {
		return exVar1;
	}
	public void setExVar1(String exVar1) {
		this.exVar1 = exVar1;
	}
	
	@Override
	public String toString() {
		return "QualityInitiative [qualityInitiativeId=" + qualityInitiativeId + ", qualityInitiativeName="
				+ qualityInitiativeName + ", delStatus=" + delStatus + ", isActive=" + isActive + ", makerUserId="
				+ makerUserId + ", makerDatetime=" + makerDatetime + ", exVar1=" + exVar1 + "]";
	}
	
}
