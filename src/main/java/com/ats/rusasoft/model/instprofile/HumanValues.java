package com.ats.rusasoft.model.instprofile;

public class HumanValues {

	private int valueId;

	private int instituteId;

	private int yearId;

	private String activityName;

	private String activityFromdt;

	private String activityTodt;

	private int activityPcount;

	private int delStatus;
	private int isActive;

	private int makerUserId;
	private String makerDatetime;

	private int exInt1;
	private int exInt2;
	private String exVar1;
	private String exVar2;

	public int getValueId() {
		return valueId;
	}

	public void setValueId(int valueId) {
		this.valueId = valueId;
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

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getActivityFromdt() {
		return activityFromdt;
	}

	public void setActivityFromdt(String activityFromdt) {
		this.activityFromdt = activityFromdt;
	}

	public String getActivityTodt() {
		return activityTodt;
	}

	public void setActivityTodt(String activityTodt) {
		this.activityTodt = activityTodt;
	}

	public int getActivityPcount() {
		return activityPcount;
	}

	public void setActivityPcount(int activityPcount) {
		this.activityPcount = activityPcount;
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
		return "HumanValues [valueId=" + valueId + ", instituteId=" + instituteId + ", yearId=" + yearId
				+ ", activityName=" + activityName + ", activityFromdt=" + activityFromdt + ", activityTodt="
				+ activityTodt + ", activityPcount=" + activityPcount + ", delStatus=" + delStatus + ", isActive="
				+ isActive + ", makerUserId=" + makerUserId + ", makerDatetime=" + makerDatetime + ", exInt1=" + exInt1
				+ ", exInt2=" + exInt2 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + "]";
	}

}
