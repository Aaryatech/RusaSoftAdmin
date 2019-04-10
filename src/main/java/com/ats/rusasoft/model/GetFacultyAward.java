package com.ats.rusasoft.model;

public class GetFacultyAward {

	private int awardId;

	private int facultyId;

	private int yearId;

	private String awardName;

	private String awardAuthority;

	private String awardNature;

	private String awardDate;

	private int awardValidity;

	private String awardValidityFrom;

	private String awardValidityTo;

	private int makerUserId;

	private String makerEnterDatetime;

	private int delStatus;

	private int isActive;

	private int exInt1;

	private int exInt2;

	private String exVar1;

	private String exVar2;

	private String deptName;
	private String facultyFirstName;

	public int getAwardId() {
		return awardId;
	}

	public void setAwardId(int awardId) {
		this.awardId = awardId;
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

	public String getAwardName() {
		return awardName;
	}

	public void setAwardName(String awardName) {
		this.awardName = awardName;
	}

	public String getAwardAuthority() {
		return awardAuthority;
	}

	public void setAwardAuthority(String awardAuthority) {
		this.awardAuthority = awardAuthority;
	}

	public String getAwardNature() {
		return awardNature;
	}

	public void setAwardNature(String awardNature) {
		this.awardNature = awardNature;
	}

	public String getAwardDate() {
		return awardDate;
	}

	public void setAwardDate(String awardDate) {
		this.awardDate = awardDate;
	}

	public int getAwardValidity() {
		return awardValidity;
	}

	public void setAwardValidity(int awardValidity) {
		this.awardValidity = awardValidity;
	}

	public String getAwardValidityFrom() {
		return awardValidityFrom;
	}

	public void setAwardValidityFrom(String awardValidityFrom) {
		this.awardValidityFrom = awardValidityFrom;
	}

	public String getAwardValidityTo() {
		return awardValidityTo;
	}

	public void setAwardValidityTo(String awardValidityTo) {
		this.awardValidityTo = awardValidityTo;
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

	@Override
	public String toString() {
		return "GetFacultyAward [awardId=" + awardId + ", facultyId=" + facultyId + ", yearId=" + yearId
				+ ", awardName=" + awardName + ", awardAuthority=" + awardAuthority + ", awardNature=" + awardNature
				+ ", awardDate=" + awardDate + ", awardValidity=" + awardValidity + ", awardValidityFrom="
				+ awardValidityFrom + ", awardValidityTo=" + awardValidityTo + ", makerUserId=" + makerUserId
				+ ", makerEnterDatetime=" + makerEnterDatetime + ", delStatus=" + delStatus + ", isActive=" + isActive
				+ ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2
				+ ", deptName=" + deptName + ", facultyFirstName=" + facultyFirstName + "]";
	}

}
