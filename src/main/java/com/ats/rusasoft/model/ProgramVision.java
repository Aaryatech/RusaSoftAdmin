package com.ats.rusasoft.model;
 

public class ProgramVision {
 
	private int visionId; 
	private int instituteId; 
	private int programId; 
	private String visionText; 
	private String visionRemark;  
	private int isActive; 
	private int delStatus; 
	private String makerdatetime; 
	private int makerUserId;
	public int getVisionId() {
		return visionId;
	}
	public void setVisionId(int visionId) {
		this.visionId = visionId;
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
	public String getVisionText() {
		return visionText;
	}
	public void setVisionText(String visionText) {
		this.visionText = visionText;
	}
	public String getVisionRemark() {
		return visionRemark;
	}
	public void setVisionRemark(String visionRemark) {
		this.visionRemark = visionRemark;
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
		return "ProgramVision [visionId=" + visionId + ", instituteId=" + instituteId + ", programId=" + programId
				+ ", visionText=" + visionText + ", visionRemark=" + visionRemark + ", isActive=" + isActive
				+ ", delStatus=" + delStatus + ", makerdatetime=" + makerdatetime + ", makerUserId=" + makerUserId
				+ "]";
	}
	
	
	
}
