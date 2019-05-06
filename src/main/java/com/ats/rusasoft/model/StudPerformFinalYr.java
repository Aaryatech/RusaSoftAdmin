package com.ats.rusasoft.model;

public class StudPerformFinalYr {

	private int studPerformId;
	private int progName;
	private int progType;
	private int noStudAppear;
	private int noStudPass;
	private double passingPer;
	private int delStatus;
	private int isActive;
	private int instId;
	private int makerUserId;
	private String makingTime;
	private int exInt1;
	private int exInt2;
	private String exVar1;
	private String exVar2;
	public int getStudPerformId() {
		return studPerformId;
	}
	public void setStudPerformId(int studPerformId) {
		this.studPerformId = studPerformId;
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
	public int getNoStudAppear() {
		return noStudAppear;
	}
	public void setNoStudAppear(int noStudAppear) {
		this.noStudAppear = noStudAppear;
	}
	public int getNoStudPass() {
		return noStudPass;
	}
	public void setNoStudPass(int noStudPass) {
		this.noStudPass = noStudPass;
	}
	public double getPassingPer() {
		return passingPer;
	}
	public void setPassingPer(double passingPer) {
		this.passingPer = passingPer;
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
	public String getMakingTime() {
		return makingTime;
	}
	public void setMakingTime(String makingTime) {
		this.makingTime = makingTime;
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
		return "StudPerformFinalYr [studPerformId=" + studPerformId + ", progName=" + progName + ", progType="
				+ progType + ", noStudAppear=" + noStudAppear + ", noStudPass=" + noStudPass + ", passingPer="
				+ passingPer + ", delStatus=" + delStatus + ", isActive=" + isActive + ", instId=" + instId
				+ ", makerUserId=" + makerUserId + ", makingTime=" + makingTime + ", exInt1=" + exInt1 + ", exInt2="
				+ exInt2 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + "]";
	}
	
	
	
}
