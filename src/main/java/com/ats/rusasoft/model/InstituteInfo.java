package com.ats.rusasoft.model;




public class InstituteInfo {
	
	private int infoDetailId;
	
	private int instituteId;
	
	private int yearId;
	
	private int noOfFulltimeFaculty ;
	

	private int noNonteachingIncludingOfficeStaff;
	
	private int noSupportStaff ;
 	
	private int noCurrentAdmitedStnt ;
	
 	 
	private String  treasuryCode; 
  	
	private String rusaIdNo; 
  	
	private String addDatetime;
 	
	private String editDatetime; 
	
	private int editBy;
	
	private int addBy;
	
	private int delStatus;
	
	private int exInt1;
	
	private int exInt2;
	
	private String exVar1; 
	
	private String exVar2;
	
	private int autonStatus;
	

	public int getAutonStatus() {
		return autonStatus;
	}

	public void setAutonStatus(int autonStatus) {
		this.autonStatus = autonStatus;
	}

	public int getInfoDetailId() {
		return infoDetailId;
	}

	public void setInfoDetailId(int infoDetailId) {
		this.infoDetailId = infoDetailId;
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


	public int getNoNonteachingIncludingOfficeStaff() {
		return noNonteachingIncludingOfficeStaff;
	}

	public void setNoNonteachingIncludingOfficeStaff(int noNonteachingIncludingOfficeStaff) {
		this.noNonteachingIncludingOfficeStaff = noNonteachingIncludingOfficeStaff;
	}

	public int getNoSupportStaff() {
		return noSupportStaff;
	}

	public void setNoSupportStaff(int noSupportStaff) {
		this.noSupportStaff = noSupportStaff;
	}

	public int getNoCurrentAdmitedStnt() {
		return noCurrentAdmitedStnt;
	}

	public void setNoCurrentAdmitedStnt(int noCurrentAdmitedStnt) {
		this.noCurrentAdmitedStnt = noCurrentAdmitedStnt;
	}

	public String getTreasuryCode() {
		return treasuryCode;
	}

	public void setTreasuryCode(String treasuryCode) {
		this.treasuryCode = treasuryCode;
	}

	public String getRusaIdNo() {
		return rusaIdNo;
	}

	public void setRusaIdNo(String rusaIdNo) {
		this.rusaIdNo = rusaIdNo;
	}

	public String getAddDatetime() {
		return addDatetime;
	}

	public void setAddDatetime(String addDatetime) {
		this.addDatetime = addDatetime;
	}

	public String getEditDatetime() {
		return editDatetime;
	}

	public void setEditDatetime(String editDatetime) {
		this.editDatetime = editDatetime;
	}

	public int getEditBy() {
		return editBy;
	}

	public void setEditBy(int editBy) {
		this.editBy = editBy;
	}

	public int getAddBy() {
		return addBy;
	}

	public void setAddBy(int addBy) {
		this.addBy = addBy;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
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

	public int getNoOfFulltimeFaculty() {
		return noOfFulltimeFaculty;
	}

	public void setNoOfFulltimeFaculty(int noOfFulltimeFaculty) {
		this.noOfFulltimeFaculty = noOfFulltimeFaculty;
	}

	@Override
	public String toString() {
		return "InstituteInfo [infoDetailId=" + infoDetailId + ", instituteId=" + instituteId + ", yearId=" + yearId
				+ ", noOfFulltimeFaculty=" + noOfFulltimeFaculty + ", noNonteachingIncludingOfficeStaff="
				+ noNonteachingIncludingOfficeStaff + ", noSupportStaff=" + noSupportStaff + ", noCurrentAdmitedStnt="
				+ noCurrentAdmitedStnt + ", treasuryCode=" + treasuryCode + ", rusaIdNo=" + rusaIdNo + ", addDatetime="
				+ addDatetime + ", editDatetime=" + editDatetime + ", editBy=" + editBy + ", addBy=" + addBy
				+ ", delStatus=" + delStatus + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exVar1=" + exVar1
				+ ", exVar2=" + exVar2 + ", autonStatus=" + autonStatus + "]";
	} 
	

	

	
	
	

}
