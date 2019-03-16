package com.ats.rusasoft.model;
 

public class FacultyConsultancy {
	 
	private int consId; 
	private int facultyId; 
	private int yearId; 
	private String consNature; 
	private String consSponsor; 
	private float consAmount; 
	private String consPeriod; 
	private int isConsCompleted; 
	private int isActive; 
	private int delStatus; 
	private String makerdatetime; 
	private int makerUserId; 
	private int exInt1; 
	private int exInt2; 
	private String exVar1; 
	private String exVar2;
	public int getConsId() {
		return consId;
	}
	public void setConsId(int consId) {
		this.consId = consId;
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
	public String getConsNature() {
		return consNature;
	}
	public void setConsNature(String consNature) {
		this.consNature = consNature;
	}
	public String getConsSponsor() {
		return consSponsor;
	}
	public void setConsSponsor(String consSponsor) {
		this.consSponsor = consSponsor;
	}
	public float getConsAmount() {
		return consAmount;
	}
	public void setConsAmount(float consAmount) {
		this.consAmount = consAmount;
	}
	public String getConsPeriod() {
		return consPeriod;
	}
	public void setConsPeriod(String consPeriod) {
		this.consPeriod = consPeriod;
	}
	public int getIsConsCompleted() {
		return isConsCompleted;
	}
	public void setIsConsCompleted(int isConsCompleted) {
		this.isConsCompleted = isConsCompleted;
	}
	public int getIsActive() {
		return isActive;
	}
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	public int getDelStatus() {
		return delStatus;
	}
	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}
	public String getMakerdatetime() {
		return makerdatetime;
	}
	public void setMakerdatetime(String makerdatetime) {
		this.makerdatetime = makerdatetime;
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
		return "FacultyConsultancy [consId=" + consId + ", facultyId=" + facultyId + ", yearId=" + yearId
				+ ", consNature=" + consNature + ", consSponsor=" + consSponsor + ", consAmount=" + consAmount
				+ ", consPeriod=" + consPeriod + ", isConsCompleted=" + isConsCompleted + ", isActive=" + isActive
				+ ", delStatus=" + delStatus + ", makerdatetime=" + makerdatetime + ", makerUserId=" + makerUserId
				+ ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + "]";
	}
	
	

}
