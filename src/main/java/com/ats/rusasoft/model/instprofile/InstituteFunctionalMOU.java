package com.ats.rusasoft.model.instprofile;

public class InstituteFunctionalMOU {

	private int mouId;

	private int instituteId;

	private int yearId;

	private String mouTitle;

	private String mouFromdt;

	private String mouTodt;

	private String mouInstitute;

	private String mouBeneficiary;

	private int mouBeneficiaryNos;

	private int delStatus;
	private int isActive;

	private int makerUserId;
	private String makerDatetime;
	private String mouAgency;

	private int exInt1;
	private int exInt2;
	private String exVar1;
	private String exVar2;

	public int getMouId() {
		return mouId;
	}

	public void setMouId(int mouId) {
		this.mouId = mouId;
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

	public String getMouTitle() {
		return mouTitle;
	}

	public void setMouTitle(String mouTitle) {
		this.mouTitle = mouTitle;
	}

	public String getMouFromdt() {
		return mouFromdt;
	}

	public void setMouFromdt(String mouFromdt) {
		this.mouFromdt = mouFromdt;
	}

	public String getMouTodt() {
		return mouTodt;
	}

	public void setMouTodt(String mouTodt) {
		this.mouTodt = mouTodt;
	}

	public String getMouInstitute() {
		return mouInstitute;
	}

	public void setMouInstitute(String mouInstitute) {
		this.mouInstitute = mouInstitute;
	}

	public String getMouBeneficiary() {
		return mouBeneficiary;
	}

	public void setMouBeneficiary(String mouBeneficiary) {
		this.mouBeneficiary = mouBeneficiary;
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
		return "InstituteFunctionalMOU [mouId=" + mouId + ", instituteId=" + instituteId + ", yearId=" + yearId
				+ ", mouTitle=" + mouTitle + ", mouFromdt=" + mouFromdt + ", mouTodt=" + mouTodt + ", mouInstitute="
				+ mouInstitute + ", mouBeneficiary=" + mouBeneficiary + ", mouBeneficiaryNos=" + mouBeneficiaryNos
				+ ", delStatus=" + delStatus + ", isActive=" + isActive + ", makerUserId=" + makerUserId
				+ ", makerDatetime=" + makerDatetime + ", mouAgency=" + mouAgency + ", exInt1=" + exInt1 + ", exInt2="
				+ exInt2 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + "]";
	}

	public String getMouAgency() {
		return mouAgency;
	}

	public void setMouAgency(String mouAgency) {
		this.mouAgency = mouAgency;
	}

	public int getMouBeneficiaryNos() {
		return mouBeneficiaryNos;
	}

	public void setMouBeneficiaryNos(int mouBeneficiaryNos) {
		this.mouBeneficiaryNos = mouBeneficiaryNos;
	}

}
