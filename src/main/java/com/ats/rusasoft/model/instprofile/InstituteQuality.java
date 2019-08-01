package com.ats.rusasoft.model.instprofile;



public class InstituteQuality {
	private int qualityId;
	
	private int instituteId;
	private int yearId;
	
	private int qualityInitiativeId; 
	
	private String qualityFromdt;
	private String qualityTodt;
	
	private int qualityPcount;
	
	private int delStatus;
	private int isActive;

	private int makerUserId;
	private String makerDatetime;
	
	private int exInt1;
	private int exInt2;
	private String exVar1;
	private String exVar2;
	
	private int isApplicable;
	private int isApplied;
	private int isCertiObt;
	
	private String autonomyValidity;	
	
	
	public int getIsApplicable() {
		return isApplicable;
	}
	public void setIsApplicable(int isApplicable) {
		this.isApplicable = isApplicable;
	}
	public int getIsApplied() {
		return isApplied;
	}
	public void setIsApplied(int isApplied) {
		this.isApplied = isApplied;
	}
	public int getIsCertiObt() {
		return isCertiObt;
	}
	public void setIsCertiObt(int isCertiObt) {
		this.isCertiObt = isCertiObt;
	}
	public int getQualityId() {
		return qualityId;
	}
	public void setQualityId(int qualityId) {
		this.qualityId = qualityId;
	}
	public int getInstituteId() {
		return instituteId;
	}
	public void setInstituteId(int instituteId) {
		this.instituteId = instituteId;
	}
	public int getYearId() {
		return yearId;
	}
	public void setYearId(int yearId) {
		this.yearId = yearId;
	}
	public int getQualityInitiativeId() {
		return qualityInitiativeId;
	}
	public void setQualityInitiativeId(int qualityInitiativeId) {
		this.qualityInitiativeId = qualityInitiativeId;
	}
	public String getQualityFromdt() {
		return qualityFromdt;
	}
	public void setQualityFromdt(String qualityFromdt) {
		this.qualityFromdt = qualityFromdt;
	}
	public String getQualityTodt() {
		return qualityTodt;
	}
	public void setQualityTodt(String qualityTodt) {
		this.qualityTodt = qualityTodt;
	}
	public int getQualityPcount() {
		return qualityPcount;
	}
	public void setQualityPcount(int qualityPcount) {
		this.qualityPcount = qualityPcount;
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
	
	public String getAutonomyValidity() {
		return autonomyValidity;
	}
	public void setAutonomyValidity(String autonomyValidity) {
		this.autonomyValidity = autonomyValidity;
	}
	@Override
	public String toString() {
		return "InstituteQuality [qualityId=" + qualityId + ", instituteId=" + instituteId + ", yearId=" + yearId
				+ ", qualityInitiativeId=" + qualityInitiativeId + ", qualityFromdt=" + qualityFromdt + ", qualityTodt="
				+ qualityTodt + ", qualityPcount=" + qualityPcount + ", delStatus=" + delStatus + ", isActive="
				+ isActive + ", makerUserId=" + makerUserId + ", makerDatetime=" + makerDatetime + ", exInt1=" + exInt1
				+ ", exInt2=" + exInt2 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + ", isApplicable=" + isApplicable
				+ ", isApplied=" + isApplied + ", isCertiObt=" + isCertiObt + ", autonomyValidity=" + autonomyValidity
				+ "]";
	}
	
	
}
