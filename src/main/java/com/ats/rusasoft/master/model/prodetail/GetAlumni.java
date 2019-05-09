package com.ats.rusasoft.master.model.prodetail;

public class GetAlumni {

	private int alumniDetailId;

	private int instituteId;

	private String alumniName;

	private String passingYear;

	private int contributionType;

	private String contributionYear;

	private String benefitTo;
	private String exVar1;
	

	public String getExVar1() {
		return exVar1;
	}

	public void setExVar1(String exVar1) {
		this.exVar1 = exVar1;
	}

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

	@Override
	public String toString() {
		return "GetAlumni [alumniDetailId=" + alumniDetailId + ", instituteId=" + instituteId + ", alumniName="
				+ alumniName + ", passingYear=" + passingYear + ", contributionType=" + contributionType
				+ ", contributionYear=" + contributionYear + ", benefitTo=" + benefitTo + ", exVar1=" + exVar1 + "]";
	}

	
	

}
