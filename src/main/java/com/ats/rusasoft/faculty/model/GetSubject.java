package com.ats.rusasoft.faculty.model;

public class GetSubject {
	private int subId;

	private int facultyId;
	private int yearId;
	private int progId;
	private String subCode;
	private String subSem;
	private String subName;
	private String subType;
	private float subPassPer;

	private int subIsCbse;
	private int subStuAppear;
	private int subStuPassed;

	private int delStatus;

	private int isActive;

	private int makerUserId;
	private String makerEnterDatetime;

	private int exInt1;
	private int exInt2;
	private String exVar1;
	private String exVar2;

	private String nameOfProgram;
	private String deptName;
	private String facultyFirstName;

	public int getSubId() {
		return subId;
	}

	public void setSubId(int subId) {
		this.subId = subId;
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

	public int getProgId() {
		return progId;
	}

	public void setProgId(int progId) {
		this.progId = progId;
	}

	public String getSubCode() {
		return subCode;
	}

	public void setSubCode(String subCode) {
		this.subCode = subCode;
	}

	public String getSubSem() {
		return subSem;
	}

	public void setSubSem(String subSem) {
		this.subSem = subSem;
	}

	public String getSubName() {
		return subName;
	}

	public void setSubName(String subName) {
		this.subName = subName;
	}

	public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

	public float getSubPassPer() {
		return subPassPer;
	}

	public void setSubPassPer(float subPassPer) {
		this.subPassPer = subPassPer;
	}

	public int getSubIsCbse() {
		return subIsCbse;
	}

	public void setSubIsCbse(int subIsCbse) {
		this.subIsCbse = subIsCbse;
	}

	public int getSubStuAppear() {
		return subStuAppear;
	}

	public void setSubStuAppear(int subStuAppear) {
		this.subStuAppear = subStuAppear;
	}

	public int getSubStuPassed() {
		return subStuPassed;
	}

	public void setSubStuPassed(int subStuPassed) {
		this.subStuPassed = subStuPassed;
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

	public String getNameOfProgram() {
		return nameOfProgram;
	}

	public void setNameOfProgram(String nameOfProgram) {
		this.nameOfProgram = nameOfProgram;
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
		return "GetSubject [subId=" + subId + ", facultyId=" + facultyId + ", yearId=" + yearId + ", progId=" + progId
				+ ", subCode=" + subCode + ", subSem=" + subSem + ", subName=" + subName + ", subType=" + subType
				+ ", subPassPer=" + subPassPer + ", subIsCbse=" + subIsCbse + ", subStuAppear=" + subStuAppear
				+ ", subStuPassed=" + subStuPassed + ", delStatus=" + delStatus + ", isActive=" + isActive
				+ ", makerUserId=" + makerUserId + ", makerEnterDatetime=" + makerEnterDatetime + ", exInt1=" + exInt1
				+ ", exInt2=" + exInt2 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + ", nameOfProgram="
				+ nameOfProgram + ", deptName=" + deptName + ", facultyFirstName=" + facultyFirstName + "]";
	}

}
