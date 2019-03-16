package com.ats.rusasoft.model;
 
public class ProgramOutcome {
	
	 
	private int poId; 
	private int instituteId; 
	private int programId; 
	private String poText; 
	private String poRemark;  
	private String psoMapId;  
	private String psoMapSatisfyingValue;  
	private int psoMapping; 
	private int isActive; 
	private int delStatus; 
	private String makerdatetime; 
	private int makerUserId; 
	private String mapDatetime; 
	private int mapUserId;
	public int getPoId() {
		return poId;
	}
	public void setPoId(int poId) {
		this.poId = poId;
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
	public String getPoText() {
		return poText;
	}
	public void setPoText(String poText) {
		this.poText = poText;
	}
	public String getPoRemark() {
		return poRemark;
	}
	public void setPoRemark(String poRemark) {
		this.poRemark = poRemark;
	}
	public String getPsoMapId() {
		return psoMapId;
	}
	public void setPsoMapId(String psoMapId) {
		this.psoMapId = psoMapId;
	}
	public String getPsoMapSatisfyingValue() {
		return psoMapSatisfyingValue;
	}
	public void setPsoMapSatisfyingValue(String psoMapSatisfyingValue) {
		this.psoMapSatisfyingValue = psoMapSatisfyingValue;
	}
	public int getPsoMapping() {
		return psoMapping;
	}
	public void setPsoMapping(int psoMapping) {
		this.psoMapping = psoMapping;
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
	public String getMapDatetime() {
		return mapDatetime;
	}
	public void setMapDatetime(String mapDatetime) {
		this.mapDatetime = mapDatetime;
	}
	public int getMapUserId() {
		return mapUserId;
	}
	public void setMapUserId(int mapUserId) {
		this.mapUserId = mapUserId;
	}
	@Override
	public String toString() {
		return "ProgramOutcome [poId=" + poId + ", instituteId=" + instituteId + ", programId=" + programId
				+ ", poText=" + poText + ", poRemark=" + poRemark + ", psoMapId=" + psoMapId
				+ ", psoMapSatisfyingValue=" + psoMapSatisfyingValue + ", psoMapping=" + psoMapping + ", isActive="
				+ isActive + ", delStatus=" + delStatus + ", makerdatetime=" + makerdatetime + ", makerUserId="
				+ makerUserId + ", mapDatetime=" + mapDatetime + ", mapUserId=" + mapUserId + "]";
	}
	
	

}
