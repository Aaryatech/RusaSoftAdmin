package com.ats.rusasoft.model;

public class StudentSchemeList {

	
	private int sprtSchmId;
	private String academicYear;
	private String schemeName;
	private String level;
	private String type;
	private int noStudentBenifited;
	private String supportAgencyName;
	
	
	public int getSprtSchmId() {
		return sprtSchmId;
	}
	public void setSprtSchmId(int sprtSchmId) {
		this.sprtSchmId = sprtSchmId;
	}
	public String getAcademicYear() {
		return academicYear;
	}
	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
	}
	public String getSchemeName() {
		return schemeName;
	}
	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getNoStudentBenifited() {
		return noStudentBenifited;
	}
	public void setNoStudentBenifited(int noStudentBenifited) {
		this.noStudentBenifited = noStudentBenifited;
	}
	public String getSupportAgencyName() {
		return supportAgencyName;
	}
	public void setSupportAgencyName(String supportAgencyName) {
		this.supportAgencyName = supportAgencyName;
	}
	
	
	
	@Override
	public String toString() {
		return "StudentSchemeList [sprtSchmId=" + sprtSchmId + ", academicYear=" + academicYear + ", schemeName="
				+ schemeName + ", level=" + level + ", type=" + type + ", noStudentBenifited=" + noStudentBenifited
				+ ", supportAgencyName=" + supportAgencyName + "]";
	}
	
	
}
