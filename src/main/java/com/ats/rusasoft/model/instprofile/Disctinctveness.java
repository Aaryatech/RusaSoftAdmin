package com.ats.rusasoft.model.instprofile;

public class Disctinctveness {

	private int distId;

	private int instituteId;

	private int yearId;

	private String distName;

	private String distBeneficiary;

	private String distApplicableFrom;

	private int delStatus;
	private int isActive;

	private int makerUserId;
	private String makerDatetime;

	private int exInt1;
	private int exInt2;
	private String exVar1;
	private String exVar2;

	public int getDistId() {
		return distId;
	}

	public void setDistId(int distId) {
		this.distId = distId;
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

	public String getDistName() {
		return distName;
	}

	public void setDistName(String distName) {
		this.distName = distName;
	}

	public String getDistBeneficiary() {
		return distBeneficiary;
	}

	public void setDistBeneficiary(String distBeneficiary) {
		this.distBeneficiary = distBeneficiary;
	}

	public String getDistApplicableFrom() {
		return distApplicableFrom;
	}

	public void setDistApplicableFrom(String distApplicableFrom) {
		this.distApplicableFrom = distApplicableFrom;
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
		return "Disctinctveness [distId=" + distId + ", instituteId=" + instituteId + ", yearId=" + yearId
				+ ", distName=" + distName + ", distBeneficiary=" + distBeneficiary + ", distApplicableFrom="
				+ distApplicableFrom + ", delStatus=" + delStatus + ", isActive=" + isActive + ", makerUserId="
				+ makerUserId + ", makerDatetime=" + makerDatetime + ", exInt1=" + exInt1 + ", exInt2=" + exInt2
				+ ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + "]";
	}

}
