package com.ats.rusasoft.controller;

public class GetResearchProject {
	private int projId;

	private int facultyId;
	private int yearId;

	private String projName;
	private String projYear;

	private String projSponsor;

	private String projInvName;

	private String projInvName2;
	private String projInvDept;

	private String projInvDept2;
	private int projGrant;

	private float projTotalAmt;
	private float projAmtRec;

	private String projFrdt;
	private String projTodt;

	private int delStatus;

	private int isActive;

	private int makerUserId;
	private String makerEnterDatetime;

	private int exInt1;
	private int exInt2;
	private String exVar1;
	private String exVar2;

	public int getProjId() {
		return projId;
	}

	public void setProjId(int projId) {
		this.projId = projId;
	}

	public int getFacultyId() {
		return facultyId;
	}

	public void setFacultyId(int facultyId) {
		this.facultyId = facultyId;
	}

	public int getYearId() {
		return yearId;
	}

	public void setYearId(int yearId) {
		this.yearId = yearId;
	}

	public String getProjName() {
		return projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}

	public String getProjYear() {
		return projYear;
	}

	public void setProjYear(String projYear) {
		this.projYear = projYear;
	}

	public String getProjSponsor() {
		return projSponsor;
	}

	public void setProjSponsor(String projSponsor) {
		this.projSponsor = projSponsor;
	}

	public String getProjInvName() {
		return projInvName;
	}

	public void setProjInvName(String projInvName) {
		this.projInvName = projInvName;
	}

	public String getProjInvName2() {
		return projInvName2;
	}

	public void setProjInvName2(String projInvName2) {
		this.projInvName2 = projInvName2;
	}

	public String getProjInvDept() {
		return projInvDept;
	}

	public void setProjInvDept(String projInvDept) {
		this.projInvDept = projInvDept;
	}

	public String getProjInvDept2() {
		return projInvDept2;
	}

	public void setProjInvDept2(String projInvDept2) {
		this.projInvDept2 = projInvDept2;
	}

	public int getProjGrant() {
		return projGrant;
	}

	public void setProjGrant(int projGrant) {
		this.projGrant = projGrant;
	}

	public float getProjTotalAmt() {
		return projTotalAmt;
	}

	public void setProjTotalAmt(float projTotalAmt) {
		this.projTotalAmt = projTotalAmt;
	}

	public float getProjAmtRec() {
		return projAmtRec;
	}

	public void setProjAmtRec(float projAmtRec) {
		this.projAmtRec = projAmtRec;
	}

	public String getProjFrdt() {
		return projFrdt;
	}

	public void setProjFrdt(String projFrdt) {
		this.projFrdt = projFrdt;
	}

	public String getProjTodt() {
		return projTodt;
	}

	public void setProjTodt(String projTodt) {
		this.projTodt = projTodt;
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

	public String getMakerEnterDatetime() {
		return makerEnterDatetime;
	}

	public void setMakerEnterDatetime(String makerEnterDatetime) {
		this.makerEnterDatetime = makerEnterDatetime;
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
		return "ResearchProject [projId=" + projId + ", facultyId=" + facultyId + ", yearId=" + yearId + ", projName="
				+ projName + ", projYear=" + projYear + ", projSponsor=" + projSponsor + ", projInvName=" + projInvName
				+ ", projInvName2=" + projInvName2 + ", projInvDept=" + projInvDept + ", projInvDept2=" + projInvDept2
				+ ", projGrant=" + projGrant + ", projTotalAmt=" + projTotalAmt + ", projAmtRec=" + projAmtRec
				+ ", projFrdt=" + projFrdt + ", projTodt=" + projTodt + ", delStatus=" + delStatus + ", isActive="
				+ isActive + ", makerUserId=" + makerUserId + ", makerEnterDatetime=" + makerEnterDatetime + ", exInt1="
				+ exInt1 + ", exInt2=" + exInt2 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + "]";
	}

}
