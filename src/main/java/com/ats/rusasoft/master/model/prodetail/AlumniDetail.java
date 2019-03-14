package com.ats.rusasoft.master.model.prodetail;


public class AlumniDetail {

	private int alumniDetailId;
	
	private int programId;

	private int instituteId;

	private int yearId;

	private String alumniName;
	
	private String passingYear;
	
	private int contributionType;
	
	private String contributionYear;
	
	private String benefitTo;
	
	private int delStatus;
	private int isActive;
	
	private String addDate;
	
	private int makerUserId;
	private String makerDatetime;
	private int exInt1;
	private int exInt2;
	private String exVar1;
	private String exVar2;
	public int getAlumniDetailId() {
		return alumniDetailId;
	}
	public void setAlumniDetailId(int alumniDetailId) {
		this.alumniDetailId = alumniDetailId;
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
	public String getAlumniName() {
		return alumniName;
	}
	public void setAlumniName(String alumniName) {
		this.alumniName = alumniName;
	}
	public String getPassingYear() {
		return passingYear;
	}
	public void setPassingYear(String passingYear) {
		this.passingYear = passingYear;
	}
	public int getContributionType() {
		return contributionType;
	}
	public void setContributionType(int contributionType) {
		this.contributionType = contributionType;
	}
	public String getContributionYear() {
		return contributionYear;
	}
	public void setContributionYear(String contributionYear) {
		this.contributionYear = contributionYear;
	}
	public String getBenefitTo() {
		return benefitTo;
	}
	public void setBenefitTo(String benefitTo) {
		this.benefitTo = benefitTo;
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
	public String getAddDate() {
		return addDate;
	}
	public void setAddDate(String addDate) {
		this.addDate = addDate;
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
	
	public int getProgramId() {
		return programId;
	}
	public void setProgramId(int programId) {
		this.programId = programId;
	}
	
	@Override
	public String toString() {
		return "AlumniDetail [alumniDetailId=" + alumniDetailId + ", programId=" + programId + ", instituteId="
				+ instituteId + ", yearId=" + yearId + ", alumniName=" + alumniName + ", passingYear=" + passingYear
				+ ", contributionType=" + contributionType + ", contributionYear=" + contributionYear + ", benefitTo="
				+ benefitTo + ", delStatus=" + delStatus + ", isActive=" + isActive + ", addDate=" + addDate
				+ ", makerUserId=" + makerUserId + ", makerDatetime=" + makerDatetime + ", exInt1=" + exInt1
				+ ", exInt2=" + exInt2 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + "]";
	}
	
}
