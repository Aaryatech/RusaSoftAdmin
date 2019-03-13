package com.ats.rusasoft.model;

public class StudentSupprtScheme {

	private int sprtSchmId;
	private int instituteId;
	private int yearId;
	private String schemeName;
	private String level;
	private String type;
	private int noStudentBenifited;
	private String supportAgencyName;
	private String implementationYear;
	private int delStatus;
	private int isActive;
	private String addDate;
	private int makerUserId;
	private int extraInt1; //programme Id
	private int extraInt2;
	private String extraVarchar1;
	private String extraVarchar2;
	public int getSprtSchmId() {
		return sprtSchmId;
	}
	public void setSprtSchmId(int sprtSchmId) {
		this.sprtSchmId = sprtSchmId;
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
	public String getImplementationYear() {
		return implementationYear;
	}
	public void setImplementationYear(String implementationYear) {
		this.implementationYear = implementationYear;
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
	public int getExtraInt1() {
		return extraInt1;
	}
	public void setExtraInt1(int extraInt1) {
		this.extraInt1 = extraInt1;
	}
	public int getExtraInt2() {
		return extraInt2;
	}
	public void setExtraInt2(int extraInt2) {
		this.extraInt2 = extraInt2;
	}
	public String getExtraVarchar1() {
		return extraVarchar1;
	}
	public void setExtraVarchar1(String extraVarchar1) {
		this.extraVarchar1 = extraVarchar1;
	}
	public String getExtraVarchar2() {
		return extraVarchar2;
	}
	public void setExtraVarchar2(String extraVarchar2) {
		this.extraVarchar2 = extraVarchar2;
	}
	@Override
	public String toString() {
		return "StudentSupprtScheme [sprtSchmId=" + sprtSchmId + ", instituteId=" + instituteId + ", yearId=" + yearId
				+ ", schemeName=" + schemeName + ", level=" + level + ", type=" + type + ", noStudentBenifited="
				+ noStudentBenifited + ", supportAgencyName=" + supportAgencyName + ", implementationYear="
				+ implementationYear + ", delStatus=" + delStatus + ", isActive=" + isActive + ", addDate=" + addDate
				+ ", makerUserId=" + makerUserId + ", extraInt1=" + extraInt1 + ", extraInt2=" + extraInt2
				+ ", extraVarchar1=" + extraVarchar1 + ", extraVarchar2=" + extraVarchar2 + "]";
	}
	
	
	
}
