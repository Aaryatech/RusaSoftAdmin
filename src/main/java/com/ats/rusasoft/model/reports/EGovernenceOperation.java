package com.ats.rusasoft.model.reports;
 
public class EGovernenceOperation {
	
	 
private String id;
	
	private int yesnoId;
	
	private String yesnoTitle;
	
	private String academicYear;
	
	private String instituteName;
	
	private String yesnoDynamicTitle;
	
	private String instYesnoResponse;
	
	private int yesnoValue;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getYesnoId() {
		return yesnoId;
	}

	public void setYesnoId(int yesnoId) {
		this.yesnoId = yesnoId;
	}

	public String getYesnoTitle() {
		return yesnoTitle;
	}

	public void setYesnoTitle(String yesnoTitle) {
		this.yesnoTitle = yesnoTitle;
	}

	public String getAcademicYear() {
		return academicYear;
	}

	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
	}

	public String getInstituteName() {
		return instituteName;
	}

	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}

	public String getYesnoDynamicTitle() {
		return yesnoDynamicTitle;
	}

	public void setYesnoDynamicTitle(String yesnoDynamicTitle) {
		this.yesnoDynamicTitle = yesnoDynamicTitle;
	}

	public String getInstYesnoResponse() {
		return instYesnoResponse;
	}

	public void setInstYesnoResponse(String instYesnoResponse) {
		this.instYesnoResponse = instYesnoResponse;
	}

	
	public int getYesnoValue() {
		return yesnoValue;
	}

	public void setYesnoValue(int yesnoValue) {
		this.yesnoValue = yesnoValue;
	}

	@Override
	public String toString() {
		return "EGovernenceOperation [id=" + id + ", yesnoId=" + yesnoId + ", yesnoTitle=" + yesnoTitle
				+ ", academicYear=" + academicYear + ", instituteName=" + instituteName + ", yesnoDynamicTitle="
				+ yesnoDynamicTitle + ", instYesnoResponse=" + instYesnoResponse + ", yesnoValue=" + yesnoValue
				+ ", getId()=" + getId() + ", getYesnoId()=" + getYesnoId() + ", getYesnoTitle()=" + getYesnoTitle()
				+ ", getAcademicYear()=" + getAcademicYear() + ", getInstituteName()=" + getInstituteName()
				+ ", getYesnoDynamicTitle()=" + getYesnoDynamicTitle() + ", getInstYesnoResponse()="
				+ getInstYesnoResponse() + ", getYesnoValue()=" + getYesnoValue() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
 

}
