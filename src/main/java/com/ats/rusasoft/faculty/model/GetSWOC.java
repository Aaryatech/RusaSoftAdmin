package com.ats.rusasoft.faculty.model;

public class GetSWOC {

	private int swocId;

	private int facultyId;
	private int yearId;
	private int swocType;

	private String swocText;

	private int delStatus;

	private int isActive;

	private int makerUserId;
	private String makerEnterDatetime;

	private int exInt1;
	private int exInt2;
	private String exVar1;
	private String exVar2;
	private String deptName;
	private String facultyFirstName;

	public int getSwocId() {
		return swocId;
	}

	public void setSwocId(int swocId) {
		this.swocId = swocId;
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

	public int getSwocType() {
		return swocType;
	}

	public void setSwocType(int swocType) {
		this.swocType = swocType;
	}

	public String getSwocText() {
		return swocText;
	}

	public void setSwocText(String swocText) {
		this.swocText = swocText;
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
		return "GetSWOC [swocId=" + swocId + ", facultyId=" + facultyId + ", yearId=" + yearId + ", swocType="
				+ swocType + ", swocText=" + swocText + ", delStatus=" + delStatus + ", isActive=" + isActive
				+ ", makerUserId=" + makerUserId + ", makerEnterDatetime=" + makerEnterDatetime + ", exInt1=" + exInt1
				+ ", exInt2=" + exInt2 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + ", deptName=" + deptName
				+ ", facultyFirstName=" + facultyFirstName + "]";
	}

}
