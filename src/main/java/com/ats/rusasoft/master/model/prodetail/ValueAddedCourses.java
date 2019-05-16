package com.ats.rusasoft.master.model.prodetail;

public class ValueAddedCourses {

	private int valueAddedCourseId;
	private String valueAddedCourseName;
	private String courseCode;
	private String yearOfOffering;
	private int noOfTimesOffer;
	private String yearOfDiscontinuation;
	private int noOfStudentsEnrolled;
	private int noOfStudentsCompletedCourse;
	private int delStatus;
	private int isActive;
	private int academicYearId;
	private int instId;
	private String makerEnterDatetime;
	private int makerUserId;
	private int exInt1;
	private int exInt2;
	private String exVar1;
	private String exVar2;
	
	
	public int getAcademicYearId() {
		return academicYearId;
	}
	public void setAcademicYearId(int academicYearId) {
		this.academicYearId = academicYearId;
	}
	public int getInstId() {
		return instId;
	}
	public void setInstId(int instId) {
		this.instId = instId;
	}
	public int getValueAddedCourseId() {
		return valueAddedCourseId;
	}
	public void setValueAddedCourseId(int valueAddedCourseId) {
		this.valueAddedCourseId = valueAddedCourseId;
	}
	public String getValueAddedCourseName() {
		return valueAddedCourseName;
	}
	public void setValueAddedCourseName(String valueAddedCourseName) {
		this.valueAddedCourseName = valueAddedCourseName;
	}
	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	public String getYearOfOffering() {
		return yearOfOffering;
	}
	public void setYearOfOffering(String yearOfOffering) {
		this.yearOfOffering = yearOfOffering;
	}
	public int getNoOfTimesOffer() {
		return noOfTimesOffer;
	}
	public void setNoOfTimesOffer(int noOfTimesOffer) {
		this.noOfTimesOffer = noOfTimesOffer;
	}
	public String getYearOfDiscontinuation() {
		return yearOfDiscontinuation;
	}
	public void setYearOfDiscontinuation(String yearOfDiscontinuation) {
		this.yearOfDiscontinuation = yearOfDiscontinuation;
	}
	public int getNoOfStudentsEnrolled() {
		return noOfStudentsEnrolled;
	}
	public void setNoOfStudentsEnrolled(int noOfStudentsEnrolled) {
		this.noOfStudentsEnrolled = noOfStudentsEnrolled;
	}
	public int getNoOfStudentsCompletedCourse() {
		return noOfStudentsCompletedCourse;
	}
	public void setNoOfStudentsCompletedCourse(int noOfStudentsCompletedCourse) {
		this.noOfStudentsCompletedCourse = noOfStudentsCompletedCourse;
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
	public String getMakerEnterDatetime() {
		return makerEnterDatetime;
	}
	public void setMakerEnterDatetime(String makerEnterDatetime) {
		this.makerEnterDatetime = makerEnterDatetime;
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
		return "ValueAddedCourses [valueAddedCourseId=" + valueAddedCourseId + ", valueAddedCourseName="
				+ valueAddedCourseName + ", courseCode=" + courseCode + ", yearOfOffering=" + yearOfOffering
				+ ", noOfTimesOffer=" + noOfTimesOffer + ", yearOfDiscontinuation=" + yearOfDiscontinuation
				+ ", noOfStudentsEnrolled=" + noOfStudentsEnrolled + ", noOfStudentsCompletedCourse="
				+ noOfStudentsCompletedCourse + ", delStatus=" + delStatus + ", isActive=" + isActive
				+ ", academicYearId=" + academicYearId + ", instId=" + instId + ", makerEnterDatetime="
				+ makerEnterDatetime + ", makerUserId=" + makerUserId + ", exInt1=" + exInt1 + ", exInt2=" + exInt2
				+ ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + "]";
	}
	
}
