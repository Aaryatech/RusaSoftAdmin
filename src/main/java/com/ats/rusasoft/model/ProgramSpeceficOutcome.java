package com.ats.rusasoft.model;




 public class ProgramSpeceficOutcome {

 	private int psoId;
	
 	private int instituteId;
	
 	private int programId;
	
 	private String psoText;
	
 	private String psoRemark; 
	
 	private int isActive;
	
 	private int delStatus;
	
 	private String makerdatetime;
	
 	private int makerUserId;

	public int getPsoId() {
		return psoId;
	}

	public void setPsoId(int psoId) {
		this.psoId = psoId;
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

	public String getPsoText() {
		return psoText;
	}

	public void setPsoText(String psoText) {
		this.psoText = psoText;
	}

	public String getPsoRemark() {
		return psoRemark;
	}

	public void setPsoRemark(String psoRemark) {
		this.psoRemark = psoRemark;
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
		return "ProgramSpeceficOutcome [psoId=" + psoId + ", instituteId=" + instituteId + ", programId=" + programId
				+ ", psoText=" + psoText + ", psoRemark=" + psoRemark + ", isActive=" + isActive + ", delStatus="
				+ delStatus + ", makerdatetime=" + makerdatetime + ", makerUserId=" + makerUserId + "]";
	}
	
	
	
}
