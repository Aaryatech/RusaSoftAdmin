package com.ats.rusasoft.model;
 

public class InstitueMission {
	 
	private int instMissionId; 
	private int instituteId; 
	private String instMissionText; 
	private String instMissionRemark;  
	private int isActive; 
	private int delStatus; 
	private String makerdatetime; 
	private int makerUserId; 
	private int exInt1; 
	private int exInt2; 
	private String exVar1; 
	private String exVar2;
	public int getInstMissionId() {
		return instMissionId;
	}
	public void setInstMissionId(int instMissionId) {
		this.instMissionId = instMissionId;
	}
	public int getInstituteId() {
		return instituteId;
	}
	public void setInstituteId(int instituteId) {
		this.instituteId = instituteId;
	}
	public String getInstMissionText() {
		return instMissionText;
	}
	public void setInstMissionText(String instMissionText) {
		this.instMissionText = instMissionText;
	}
	public String getInstMissionRemark() {
		return instMissionRemark;
	}
	public void setInstMissionRemark(String instMissionRemark) {
		this.instMissionRemark = instMissionRemark;
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
	public String getMakerdatetime() {
		return makerdatetime;
	}
	public void setMakerdatetime(String makerdatetime) {
		this.makerdatetime = makerdatetime;
	}
	public int getMakerUserId() {
		return makerUserId;
	}
	public void setMakerUserId(int makerUserId) {
		this.makerUserId = makerUserId;
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
		return "InstitueMission [instMissionId=" + instMissionId + ", instituteId=" + instituteId + ", instMissionText="
				+ instMissionText + ", instMissionRemark=" + instMissionRemark + ", isActive=" + isActive
				+ ", delStatus=" + delStatus + ", makerdatetime=" + makerdatetime + ", makerUserId=" + makerUserId
				+ ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + "]";
	}
	
	

}
