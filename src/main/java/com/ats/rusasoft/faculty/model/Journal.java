package com.ats.rusasoft.faculty.model;

public class Journal {

	private int journalId;

	private int facultyId;
	private int yearId;
	private String journalType;
	private String journalName;
	private String journalYear;
	private String journalIssue;
	private String journalVolume;
	private String journalPgFrom;

	private String journalPgTo;

	private int journalStandard;

	private int delStatus;

	private int isActive;

	private int makerUserId;
	private String makerEnterDatetime;

	private int exInt1;
	private int exInt2;
	private String exVar1;
	private String exVar2;

	public int getJournalId() {
		return journalId;
	}

	public void setJournalId(int journalId) {
		this.journalId = journalId;
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

	public String getJournalType() {
		return journalType;
	}

	public void setJournalType(String journalType) {
		this.journalType = journalType;
	}

	public String getJournalName() {
		return journalName;
	}

	public void setJournalName(String journalName) {
		this.journalName = journalName;
	}

	public String getJournalYear() {
		return journalYear;
	}

	public void setJournalYear(String journalYear) {
		this.journalYear = journalYear;
	}

	public String getJournalIssue() {
		return journalIssue;
	}

	public void setJournalIssue(String journalIssue) {
		this.journalIssue = journalIssue;
	}

	public String getJournalVolume() {
		return journalVolume;
	}

	public void setJournalVolume(String journalVolume) {
		this.journalVolume = journalVolume;
	}

	public String getJournalPgFrom() {
		return journalPgFrom;
	}

	public void setJournalPgFrom(String journalPgFrom) {
		this.journalPgFrom = journalPgFrom;
	}

	public String getJournalPgTo() {
		return journalPgTo;
	}

	public void setJournalPgTo(String journalPgTo) {
		this.journalPgTo = journalPgTo;
	}

	public int getJournalStandard() {
		return journalStandard;
	}

	public void setJournalStandard(int journalStandard) {
		this.journalStandard = journalStandard;
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
		return "Journal [journalId=" + journalId + ", facultyId=" + facultyId + ", yearId=" + yearId + ", journalType="
				+ journalType + ", journalName=" + journalName + ", journalYear=" + journalYear + ", journalIssue="
				+ journalIssue + ", journalVolume=" + journalVolume + ", journalPgFrom=" + journalPgFrom
				+ ", journalPgTo=" + journalPgTo + ", journalStandard=" + journalStandard + ", delStatus=" + delStatus
				+ ", isActive=" + isActive + ", makerUserId=" + makerUserId + ", makerEnterDatetime="
				+ makerEnterDatetime + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exVar1=" + exVar1 + ", exVar2="
				+ exVar2 + "]";
	}

}
