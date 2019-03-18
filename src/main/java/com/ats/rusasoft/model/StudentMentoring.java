package com.ats.rusasoft.model;

public class StudentMentoring {
 private int menId;
 private int facultyId;
 private int yearId;
 private int menStuCount;
 private int delStatus;
 private int isActive;
 private int makerUserId;	
 private String makerEnterDatetime; 
 private int exInt1;
 private String exVar1;
public int getMenId() {
	return menId;
}
public void setMenId(int menId) {
	this.menId = menId;
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
public int getMenStuCount() {
	return menStuCount;
}
public void setMenStuCount(int menStuCount) {
	this.menStuCount = menStuCount;
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
@Override
public String toString() {
	return "StudentMentoring [menId=" + menId + ", facultyId=" + facultyId + ", yearId=" + yearId + ", menStuCount="
			+ menStuCount + ", delStatus=" + delStatus + ", isActive=" + isActive + ", makerUserId=" + makerUserId
			+ ", makerEnterDatetime=" + makerEnterDatetime + ", exInt1=" + exInt1 + ", exVar1=" + exVar1 + "]";
}
 
 
}
