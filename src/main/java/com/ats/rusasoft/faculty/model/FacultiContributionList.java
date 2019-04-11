package com.ats.rusasoft.faculty.model;


public class FacultiContributionList {
	
	private int conId;
	private int facultyId;
	private int yearId;
	private String conLevel;
	private String deptName;
	private String facultyFirstName;
	private String conName;
	private String conUniversity;
	private String conFrom;
	private String conTo;
	private int conExamSetting;
	private int conAsEvaluation;
	private int conAsModeration;
	private int delStatus;
	private int isActive;
	private int makerUserId;
	private String makerEnterDatetime;
	public int getConId() {
		return conId;
	}
	public void setConId(int conId) {
		this.conId = conId;
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
	public String getConLevel() {
		return conLevel;
	}
	public void setConLevel(String conLevel) {
		this.conLevel = conLevel;
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
	public String getConName() {
		return conName;
	}
	public void setConName(String conName) {
		this.conName = conName;
	}
	public String getConUniversity() {
		return conUniversity;
	}
	public void setConUniversity(String conUniversity) {
		this.conUniversity = conUniversity;
	}
	
	public String getConFrom() {
		return conFrom;
	}
	public void setConFrom(String conFrom) {
		this.conFrom = conFrom;
	}
	
	public String getConTo() {
		return conTo;
	}
	public void setConTo(String conTo) {
		this.conTo = conTo;
	}
	public int getConExamSetting() {
		return conExamSetting;
	}
	public void setConExamSetting(int conExamSetting) {
		this.conExamSetting = conExamSetting;
	}
	public int getConAsEvaluation() {
		return conAsEvaluation;
	}
	public void setConAsEvaluation(int conAsEvaluation) {
		this.conAsEvaluation = conAsEvaluation;
	}
	public int getConAsModeration() {
		return conAsModeration;
	}
	public void setConAsModeration(int conAsModeration) {
		this.conAsModeration = conAsModeration;
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
	@Override
	public String toString() {
		return "FacultiContributionList [conId=" + conId + ", facultyId=" + facultyId + ", yearId=" + yearId
				+ ", conLevel=" + conLevel + ", deptName=" + deptName + ", facultyFirstName=" + facultyFirstName
				+ ", conName=" + conName + ", conUniversity=" + conUniversity + ", conFrom=" + conFrom + ", conTo="
				+ conTo + ", conExamSetting=" + conExamSetting + ", conAsEvaluation=" + conAsEvaluation
				+ ", conAsModeration=" + conAsModeration + ", delStatus=" + delStatus + ", isActive=" + isActive
				+ ", makerUserId=" + makerUserId + ", makerEnterDatetime=" + makerEnterDatetime + "]";
	}
	
	
}
