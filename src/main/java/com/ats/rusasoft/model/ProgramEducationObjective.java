package com.ats.rusasoft.model;
 

public class ProgramEducationObjective {
	 
	private int peoId; 
	private int instituteId; 
	private int programId; 
	private String peoText; 
	private String peoRemark;  
	private int isActive; 
	private int delStatus; 
	private String makerdatetime; 
	private int makerUserId;
	public int getPeoId() {
		return peoId;
	}
	public void setPeoId(int peoId) {
		this.peoId = peoId;
	}
	public int getInstituteId() {
		return instituteId;
	}
	public void setInstituteId(int instituteId) {
		this.instituteId = instituteId;
	}
	public int getProgramId() {
		return programId;
	}
	public void setProgramId(int programId) {
		this.programId = programId;
	}
	public String getPeoText() {
		return peoText;
	}
	public void setPeoText(String peoText) {
		this.peoText = peoText;
	}
	public String getPeoRemark() {
		return peoRemark;
	}
	public void setPeoRemark(String peoRemark) {
		this.peoRemark = peoRemark;
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
	@Override
	public String toString() {
		return "ProgramEducationObjective [peoId=" + peoId + ", instituteId=" + instituteId + ", programId=" + programId
				+ ", peoText=" + peoText + ", peoRemark=" + peoRemark + ", isActive=" + isActive + ", delStatus="
				+ delStatus + ", makerdatetime=" + makerdatetime + ", makerUserId=" + makerUserId + "]";
	}
	
	

}
