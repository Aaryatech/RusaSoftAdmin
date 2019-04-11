package com.ats.rusasoft.model;

public class StudMentorList {
	private int menId;
	private int yearId;
	private int facultyId;
	private String facultyFirstName;
	private String deptName;
	
	private int menStuCount;
	//private String academicYear;
	public int getMenId() {
		return menId;
	}
	public void setMenId(int menId) {
		this.menId = menId;
	}
	public int getYearId() {
		return yearId;
	}
	public void setYearId(int yearId) {
		this.yearId = yearId;
	}
	public int getMenStuCount() {
		return menStuCount;
	}
	public void setMenStuCount(int menStuCount) {
		this.menStuCount = menStuCount;
	}
	/*
	 * public String getAcademicYear() { return academicYear; } public void
	 * setAcademicYear(String academicYear) { this.academicYear = academicYear; }
	 */
	
	public int getFacultyId() {
		return facultyId;
	}
	public void setFacultyId(int facultyId) {
		this.facultyId = facultyId;
	}
	public String getFacultyFirstName() {
		return facultyFirstName;
	}
	public void setFacultyFirstName(String facultyFirstName) {
		this.facultyFirstName = facultyFirstName;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	@Override
	public String toString() {
		return "StudMentorList [menId=" + menId + ", yearId=" + yearId + ", facultyId=" + facultyId
				+ ", facultyFirstName=" + facultyFirstName + ", deptName=" + deptName + ", menStuCount=" + menStuCount
				+ "]";
	}
	
	
}
 	
