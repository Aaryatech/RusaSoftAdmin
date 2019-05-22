package com.ats.rusasoft.model.instprofile;

 
 
public class InstStakeholderFeedback {
  
	private int stakFbId;

	private int instituteId;

	private int acYearId;

	private int fbFromId;
	
	private int fbYesno;
	
	private int yrSem;

	private String fbProcess;

	private int delStatus;
	
	private int isActive;

	private int makerUserId;
	
	private String makerDatetime;

	private int exInt1;
	
	private int exInt2;
	
	private String exVar1;
	
	private String exVar2;

	public int getStakFbId() {
		return stakFbId;
	}

	public void setStakFbId(int stakFbId) {
		this.stakFbId = stakFbId;
	}

	public int getInstituteId() {
		return instituteId;
	}

	public void setInstituteId(int instituteId) {
		this.instituteId = instituteId;
	}

	public int getAcYearId() {
		return acYearId;
	}

	public void setAcYearId(int acYearId) {
		this.acYearId = acYearId;
	}

	public int getFbFromId() {
		return fbFromId;
	}

	public void setFbFromId(int fbFromId) {
		this.fbFromId = fbFromId;
	}

	public int getFbYesno() {
		return fbYesno;
	}

	public void setFbYesno(int fbYesno) {
		this.fbYesno = fbYesno;
	}

	public int getYrSem() {
		return yrSem;
	}

	public void setYrSem(int yrSem) {
		this.yrSem = yrSem;
	}

	public String getFbProcess() {
		return fbProcess;
	}

	public void setFbProcess(String fbProcess) {
		this.fbProcess = fbProcess;
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
		return "InstStakeholderFeedback [stakFbId=" + stakFbId + ", instituteId=" + instituteId + ", acYearId="
				+ acYearId + ", fbFromId=" + fbFromId + ", fbYesno=" + fbYesno + ", yrSem=" + yrSem + ", fbProcess="
				+ fbProcess + ", delStatus=" + delStatus + ", isActive=" + isActive + ", makerUserId=" + makerUserId
				+ ", makerDatetime=" + makerDatetime + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exVar1="
				+ exVar1 + ", exVar2=" + exVar2 + ", getStakFbId()=" + getStakFbId() + ", getInstituteId()="
				+ getInstituteId() + ", getAcYearId()=" + getAcYearId() + ", getFbFromId()=" + getFbFromId()
				+ ", getFbYesno()=" + getFbYesno() + ", getYrSem()=" + getYrSem() + ", getFbProcess()=" + getFbProcess()
				+ ", getDelStatus()=" + getDelStatus() + ", getIsActive()=" + getIsActive() + ", getMakerUserId()="
				+ getMakerUserId() + ", getMakerDatetime()=" + getMakerDatetime() + ", getExInt1()=" + getExInt1()
				+ ", getExInt2()=" + getExInt2() + ", getExVar1()=" + getExVar1() + ", getExVar2()=" + getExVar2()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
	
	
	
}
