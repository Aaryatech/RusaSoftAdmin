package com.ats.rusasoft.master.model.prodetail;

public class ProgSancIntake {

	private int progSancIntakeId;
	private int programId;
	private int sancIntake;
	private int instId;
	private int acYearId;
	private int delStatus;
	private int isActive; 
	private int makerUserId; 
	private String makerEnterDatetime;
	private int exInt1; 
	private String exVar1;
	
	
	
	public int getProgSancIntakeId() {
		return progSancIntakeId;
	}



	public int getProgramId() {
		return programId;
	}



	public int getSancIntake() {
		return sancIntake;
	}



	public int getInstId() {
		return instId;
	}



	public int getAcYearId() {
		return acYearId;
	}



	public int getDelStatus() {
		return delStatus;
	}



	public int getIsActive() {
		return isActive;
	}



	public int getMakerUserId() {
		return makerUserId;
	}



	public String getMakerEnterDatetime() {
		return makerEnterDatetime;
	}



	public int getExInt1() {
		return exInt1;
	}



	public String getExVar1() {
		return exVar1;
	}



	public void setProgSancIntakeId(int progSancIntakeId) {
		this.progSancIntakeId = progSancIntakeId;
	}



	public void setProgramId(int programId) {
		this.programId = programId;
	}



	public void setSancIntake(int sancIntake) {
		this.sancIntake = sancIntake;
	}



	public void setInstId(int instId) {
		this.instId = instId;
	}



	public void setAcYearId(int acYearId) {
		this.acYearId = acYearId;
	}



	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}



	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}



	public void setMakerUserId(int makerUserId) {
		this.makerUserId = makerUserId;
	}



	public void setMakerEnterDatetime(String makerEnterDatetime) {
		this.makerEnterDatetime = makerEnterDatetime;
	}



	public void setExInt1(int exInt1) {
		this.exInt1 = exInt1;
	}



	public void setExVar1(String exVar1) {
		this.exVar1 = exVar1;
	}



	@Override
	public String toString() {
		return "TProgSancIntake [progSancIntakeId=" + progSancIntakeId + ", programId=" + programId + ", sancIntake="
				+ sancIntake + ", instId=" + instId + ", acYearId=" + acYearId + ", delStatus=" + delStatus
				+ ", isActive=" + isActive + ", makerUserId=" + makerUserId + ", makerEnterDatetime="
				+ makerEnterDatetime + ", exInt1=" + exInt1 + ", exVar1=" + exVar1 + "]";
	}
	
	
}
