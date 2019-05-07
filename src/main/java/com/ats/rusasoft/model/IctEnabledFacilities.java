package com.ats.rusasoft.model;

public class IctEnabledFacilities {
//ict_enabled_facilities
	
	private int ictEnbFacId;
	private int noOfClassroom;
	private int noClassromLcd;
	private int noClassroomWifi; 
	private int seminarHall;
	private int ictSeminarHall;
	private int delStatus;
	private int isActive;
	private int instId;
	private int makerUserId;
	private String makerEnterDatetime;
	private int yearId;
	private int exInt1;
	private int exInt2;
	private String exVar1;
	private String exVar2;
	public int getIctEnbFacId() {
		return ictEnbFacId;
	}
	public void setIctEnbFacId(int ictEnbFacId) {
		this.ictEnbFacId = ictEnbFacId;
	}
	public int getNoOfClassroom() {
		return noOfClassroom;
	}
	public void setNoOfClassroom(int noOfClassroom) {
		this.noOfClassroom = noOfClassroom;
	}
	public int getNoClassromLcd() {
		return noClassromLcd;
	}
	public void setNoClassromLcd(int noClassromLcd) {
		this.noClassromLcd = noClassromLcd;
	}
	public int getNoClassroomWifi() {
		return noClassroomWifi;
	}
	public void setNoClassroomWifi(int noClassroomWifi) {
		this.noClassroomWifi = noClassroomWifi;
	}
	public int getSeminarHall() {
		return seminarHall;
	}
	public void setSeminarHall(int seminarHall) {
		this.seminarHall = seminarHall;
	}
	public int getIctSeminarHall() {
		return ictSeminarHall;
	}
	public void setIctSeminarHall(int ictSeminarHall) {
		this.ictSeminarHall = ictSeminarHall;
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
	public int getInstId() {
		return instId;
	}
	public void setInstId(int instId) {
		this.instId = instId;
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
	public int getYearId() {
		return yearId;
	}
	public void setYearId(int yearId) {
		this.yearId = yearId;
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
		return "IctEnabledFacilities [ictEnbFacId=" + ictEnbFacId + ", noOfClassroom=" + noOfClassroom
				+ ", noClassromLcd=" + noClassromLcd + ", noClassroomWifi=" + noClassroomWifi + ", seminarHall="
				+ seminarHall + ", ictSeminarHall=" + ictSeminarHall + ", delStatus=" + delStatus + ", isActive="
				+ isActive + ", instId=" + instId + ", makerUserId=" + makerUserId + ", makerEnterDatetime="
				+ makerEnterDatetime + ", yearId=" + yearId + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exVar1="
				+ exVar1 + ", exVar2=" + exVar2 + "]";
	}
	
	
}
