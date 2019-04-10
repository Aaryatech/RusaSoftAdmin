package com.ats.rusasoft.model;

import java.util.Date;

public class GetFacultyActivity {

	private int activityId;
	private int facultyId;
	private int yearId;
	private String activityType;
	private String activityName;
	private String activityLevel;
	private String activityDate;
	private String activityParticipants;
	private String activityFundedBy;
	private int activityAmountSanctioned;
	private int activityAmountUtilised;
	private int delStatus;
	private int isActive;
	private int makerUserId;
	private String makerEnterDatetime;
	private int exInt1;
	private String exVar1;

	private String deptName;
	private String facultyFirstName;
	private String typeName;

	public int getActivityId() {
		return activityId;
	}

	public void setActivityId(int activityId) {
		this.activityId = activityId;
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

	public String getActivityType() {
		return activityType;
	}

	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getActivityLevel() {
		return activityLevel;
	}

	public void setActivityLevel(String activityLevel) {
		this.activityLevel = activityLevel;
	}

	public String getActivityDate() {
		return activityDate;
	}

	public void setActivityDate(String activityDate) {
		this.activityDate = activityDate;
	}

	public String getActivityParticipants() {
		return activityParticipants;
	}

	public void setActivityParticipants(String activityParticipants) {
		this.activityParticipants = activityParticipants;
	}

	public String getActivityFundedBy() {
		return activityFundedBy;
	}

	public void setActivityFundedBy(String activityFundedBy) {
		this.activityFundedBy = activityFundedBy;
	}

	public int getActivityAmountSanctioned() {
		return activityAmountSanctioned;
	}

	public void setActivityAmountSanctioned(int activityAmountSanctioned) {
		this.activityAmountSanctioned = activityAmountSanctioned;
	}

	public int getActivityAmountUtilised() {
		return activityAmountUtilised;
	}

	public void setActivityAmountUtilised(int activityAmountUtilised) {
		this.activityAmountUtilised = activityAmountUtilised;
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

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getFacultyFirstName() {
		return facultyFirstName;
	}

	public void setFacultyFirstName(String facultyFirstName) {
		this.facultyFirstName = facultyFirstName;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Override
	public String toString() {
		return "GetFacultyActivity [activityId=" + activityId + ", facultyId=" + facultyId + ", yearId=" + yearId
				+ ", activityType=" + activityType + ", activityName=" + activityName + ", activityLevel="
				+ activityLevel + ", activityDate=" + activityDate + ", activityParticipants=" + activityParticipants
				+ ", activityFundedBy=" + activityFundedBy + ", activityAmountSanctioned=" + activityAmountSanctioned
				+ ", activityAmountUtilised=" + activityAmountUtilised + ", delStatus=" + delStatus + ", isActive="
				+ isActive + ", makerUserId=" + makerUserId + ", makerEnterDatetime=" + makerEnterDatetime + ", exInt1="
				+ exInt1 + ", exVar1=" + exVar1 + ", deptName=" + deptName + ", facultyFirstName=" + facultyFirstName
				+ ", typeName=" + typeName + "]";
	}

}
