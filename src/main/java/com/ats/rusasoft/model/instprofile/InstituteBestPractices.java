package com.ats.rusasoft.model.instprofile;


public class InstituteBestPractices {
private int practicesId;
	
	private int instituteId;
	
	private int yearId;
		
	private String practicesName;
	
	private String practicesBeneficiary;
	
	private String practicesEffectiveFrom;
	
	private int delStatus;
	private int isActive;
	
	private int makerUserId;
	private String makerDatetime;

	
	
	private int exInt1;
	private int exInt2;
	private String exVar1;
	private String exVar2;
	public int getPracticesId() {
		return practicesId;
	}
	public void setPracticesId(int practicesId) {
		this.practicesId = practicesId;
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
	public String getPracticesName() {
		return practicesName;
	}
	public void setPracticesName(String practicesName) {
		this.practicesName = practicesName;
	}
	public String getPracticesBeneficiary() {
		return practicesBeneficiary;
	}
	public void setPracticesBeneficiary(String practicesBeneficiary) {
		this.practicesBeneficiary = practicesBeneficiary;
	}
	public String getPracticesEffectiveFrom() {
		return practicesEffectiveFrom;
	}
	public void setPracticesEffectiveFrom(String practicesEffectiveFrom) {
		this.practicesEffectiveFrom = practicesEffectiveFrom;
	}
	@Override
	public String toString() {
		return "InstituteBestPractices [practicesId=" + practicesId + ", instituteId=" + instituteId + ", yearId="
				+ yearId + ", practicesName=" + practicesName + ", practicesBeneficiary=" + practicesBeneficiary
				+ ", practicesEffectiveFrom=" + practicesEffectiveFrom + ", delStatus=" + delStatus + ", isActive="
				+ isActive + ", makerUserId=" + makerUserId + ", makerDatetime=" + makerDatetime + ", exInt1=" + exInt1
				+ ", exInt2=" + exInt2 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + ", getPracticesId()="
				+ getPracticesId() + ", getInstituteId()=" + getInstituteId() + ", getYearId()=" + getYearId()
				+ ", getDelStatus()=" + getDelStatus() + ", getIsActive()=" + getIsActive() + ", getMakerUserId()="
				+ getMakerUserId() + ", getMakerDatetime()=" + getMakerDatetime() + ", getExInt1()=" + getExInt1()
				+ ", getExInt2()=" + getExInt2() + ", getExVar1()=" + getExVar1() + ", getExVar2()=" + getExVar2()
				+ ", getPracticesName()=" + getPracticesName() + ", getPracticesBeneficiary()="
				+ getPracticesBeneficiary() + ", getPracticesEffectiveFrom()=" + getPracticesEffectiveFrom()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
	
	
	
	
	
	

}
