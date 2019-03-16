package com.ats.rusasoft.master;
 

public class ProgramMission {
	 
	private int missionId; 
	private int instituteId; 
	private int programId; 
	private String missionText; 
	private String missionRemark;  
	private int isActive; 
	private int delStatus; 
	private String makerdatetime; 
	private int makerUserId;
	public int getMissionId() {
		return missionId;
	}
	public void setMissionId(int missionId) {
		this.missionId = missionId;
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
	public String getMissionText() {
		return missionText;
	}
	public void setMissionText(String missionText) {
		this.missionText = missionText;
	}
	public String getMissionRemark() {
		return missionRemark;
	}
	public void setMissionRemark(String missionRemark) {
		this.missionRemark = missionRemark;
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
		return "ProgramMission [missionId=" + missionId + ", instituteId=" + instituteId + ", programId=" + programId
				+ ", missionText=" + missionText + ", missionRemark=" + missionRemark + ", isActive=" + isActive
				+ ", delStatus=" + delStatus + ", makerdatetime=" + makerdatetime + ", makerUserId=" + makerUserId
				+ "]";
	}
	
	

}
