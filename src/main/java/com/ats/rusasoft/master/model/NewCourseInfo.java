package com.ats.rusasoft.master.model;

public class NewCourseInfo {
	
	private int courseId;
	private int progName;	
	private int progType;
	private String applicableYear;
	private String courseName;
	private int courseCode;
	private String introduceFrom;
	private String document;
	private int delStatus;
	private int isActive;
	private int makerUserId;
	private String makerDatetime; 
	private int instId;
	private String exVar1;
	private String exVar2;
	private int exInt1;
	private int exInt2;
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public int getProgName() {
		return progName;
	}
	public void setProgName(int progName) {
		this.progName = progName;
	}
	public int getProgType() {
		return progType;
	}
	public void setProgType(int progType) {
		this.progType = progType;
	}
	public String getApplicableYear() {
		return applicableYear;
	}
	public void setApplicableYear(String applicableYear) {
		this.applicableYear = applicableYear;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public int getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(int courseCode) {
		this.courseCode = courseCode;
	}
	public String getIntroduceFrom() {
		return introduceFrom;
	}
	public void setIntroduceFrom(String introduceFrom) {
		this.introduceFrom = introduceFrom;
	}
	public String getDocument() {
		return document;
	}
	public void setDocument(String document) {
		this.document = document;
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
	public int getInstId() {
		return instId;
	}
	public void setInstId(int instId) {
		this.instId = instId;
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
	@Override
	public String toString() {
		return "NewCourseInfo [courseId=" + courseId + ", progName=" + progName + ", progType=" + progType
				+ ", applicableYear=" + applicableYear + ", courseName=" + courseName + ", courseCode=" + courseCode
				+ ", introduceFrom=" + introduceFrom + ", document=" + document + ", delStatus=" + delStatus
				+ ", isActive=" + isActive + ", makerUserId=" + makerUserId + ", makerDatetime=" + makerDatetime
				+ ", instId=" + instId + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + ", exInt1=" + exInt1
				+ ", exInt2=" + exInt2 + "]";
	}
	
}
