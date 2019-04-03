package com.ats.rusasoft.model;

public class InstituteSupport {
	
	private int instSchemeId;
	private int instituteId;
	private int yearId;
	private String instSchemeName;
	private int instStudentsBenefited;
	private String instSchmeOfferedby;
	private int delStatus;
	private int isActive;
	private int makerUserId;
	private String makerDatetime;
	private int exInt1;
	private int exInt2;
	private String exVar1;
	private String exVar2;
	public int getInstSchemeId() {
		return instSchemeId;
	}
	public void setInstSchemeId(int instSchemeId) {
		this.instSchemeId = instSchemeId;
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
	public String getInstSchemeName() {
		return instSchemeName;
	}
	public void setInstSchemeName(String instSchemeName) {
		this.instSchemeName = instSchemeName;
	}
	public int getInstStudentsBenefited() {
		return instStudentsBenefited;
	}
	public void setInstStudentsBenefited(int instStudentsBenefited) {
		this.instStudentsBenefited = instStudentsBenefited;
	}
	public String getInstSchmeOfferedby() {
		return instSchmeOfferedby;
	}
	public void setInstSchmeOfferedby(String instSchmeOfferedby) {
		this.instSchmeOfferedby = instSchmeOfferedby;
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
		return "IsntituteSupport [instSchemeId=" + instSchemeId + ", instituteId=" + instituteId + ", yearId=" + yearId
				+ ", instSchemeName=" + instSchemeName + ", instStudentsBenefited=" + instStudentsBenefited
				+ ", instSchmeOfferedby=" + instSchmeOfferedby + ", delStatus=" + delStatus + ", isActive=" + isActive
				+ ", makerUserId=" + makerUserId + ", makerDatetime=" + makerDatetime + ", exInt1=" + exInt1
				+ ", exInt2=" + exInt2 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + "]";
	}
	
	
}
