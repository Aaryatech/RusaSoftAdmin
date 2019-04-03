package com.ats.rusasoft.model;

public class InstituteActivity {
	
	private int instActivityId;
	private int instituteId;
	private int yearId;
	private String instActivityType;
	private String instActivityLevel;
	private String instActivityName;
	private String instActivityFromdt;
	private String instActivityTodt;
	private int instActivityParticipation;
	private int delStatus;
	private int isActive;
	private int makerUserId;
	private String makerDatetime;
	private int exInt1;
	private int exInt2;
	private String exVar1;
	private String exVar2;
	public int getInstActivityId() {
		return instActivityId;
	}
	public void setInstActivityId(int instActivityId) {
		this.instActivityId = instActivityId;
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
	public String getInstActivityType() {
		return instActivityType;
	}
	public void setInstActivityType(String instActivityType) {
		this.instActivityType = instActivityType;
	}
	public String getInstActivityLevel() {
		return instActivityLevel;
	}
	public void setInstActivityLevel(String	 instActivityLevel) {
		this.instActivityLevel = instActivityLevel;
	}
	public String getInstActivityName() {
		return instActivityName;
	}
	public void setInstActivityName(String instActivityName) {
		this.instActivityName = instActivityName;
	}
	public String getInstActivityFromdt() {
		return instActivityFromdt;
	}
	public void setInstActivityFromdt(String instActivityFromdt) {
		this.instActivityFromdt = instActivityFromdt;
	}
	public String getInstActivityTodt() {
		return instActivityTodt;
	}
	public void setInstActivityTodt(String instActivityTodt) {
		this.instActivityTodt = instActivityTodt;
	}
	public int getInstActivityParticipation() {
		return instActivityParticipation;
	}
	public void setInstActivityParticipation(int instActivityParticipation) {
		this.instActivityParticipation = instActivityParticipation;
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
		return "InstituteActivity [instActivityId=" + instActivityId + ", instituteId=" + instituteId + ", yearId="
				+ yearId + ", instActivityType=" + instActivityType + ", instActivityLevel=" + instActivityLevel
				+ ", instActivityName=" + instActivityName + ", instActivityFromdt=" + instActivityFromdt
				+ ", instActivityTodt=" + instActivityTodt + ", instActivityParticipation=" + instActivityParticipation
				+ ", delStatus=" + delStatus + ", isActive=" + isActive + ", makerUserId=" + makerUserId
				+ ", makerDatetime=" + makerDatetime + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exVar1="
				+ exVar1 + ", exVar2=" + exVar2 + "]";
	}
	
	

}
