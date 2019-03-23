package com.ats.rusasoft.model;
 
public class InstituteYesNo {
	 
    private int instYesnoId; 
	private int instituteId; 
	private int yearId; 
	private int yesnoId; 
	private String yesnoPagecode; 
	private String yesnoDynamicTitle; 
	private String instYesnoResponse;  
	private int delStatus; 
	private int isActive; 
	private String makerDatetime; 
	private int makerUserId; 
	private int yesnoValue;
	private String sectionCode;
	public int getInstYesnoId() {
		return instYesnoId;
	}
	public void setInstYesnoId(int instYesnoId) {
		this.instYesnoId = instYesnoId;
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
	public int getYesnoId() {
		return yesnoId;
	}
	public void setYesnoId(int yesnoId) {
		this.yesnoId = yesnoId;
	}
	public String getYesnoPagecode() {
		return yesnoPagecode;
	}
	public void setYesnoPagecode(String yesnoPagecode) {
		this.yesnoPagecode = yesnoPagecode;
	}
	public String getYesnoDynamicTitle() {
		return yesnoDynamicTitle;
	}
	public void setYesnoDynamicTitle(String yesnoDynamicTitle) {
		this.yesnoDynamicTitle = yesnoDynamicTitle;
	}
	public String getInstYesnoResponse() {
		return instYesnoResponse;
	}
	public void setInstYesnoResponse(String instYesnoResponse) {
		this.instYesnoResponse = instYesnoResponse;
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
	public String getMakerDatetime() {
		return makerDatetime;
	}
	public void setMakerDatetime(String makerDatetime) {
		this.makerDatetime = makerDatetime;
	}
	public int getMakerUserId() {
		return makerUserId;
	}
	public void setMakerUserId(int makerUserId) {
		this.makerUserId = makerUserId;
	}
	public int getYesnoValue() {
		return yesnoValue;
	}
	public void setYesnoValue(int yesnoValue) {
		this.yesnoValue = yesnoValue;
	}
	public String getSectionCode() {
		return sectionCode;
	}
	public void setSectionCode(String sectionCode) {
		this.sectionCode = sectionCode;
	}
	@Override
	public String toString() {
		return "InstituteYesNo [instYesnoId=" + instYesnoId + ", instituteId=" + instituteId + ", yearId=" + yearId
				+ ", yesnoId=" + yesnoId + ", yesnoPagecode=" + yesnoPagecode + ", yesnoDynamicTitle="
				+ yesnoDynamicTitle + ", instYesnoResponse=" + instYesnoResponse + ", delStatus=" + delStatus
				+ ", isActive=" + isActive + ", makerDatetime=" + makerDatetime + ", makerUserId=" + makerUserId
				+ ", yesnoValue=" + yesnoValue + ", sectionCode=" + sectionCode + "]";
	}
	
	

}
