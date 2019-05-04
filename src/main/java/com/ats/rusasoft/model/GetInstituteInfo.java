package com.ats.rusasoft.model;



public class GetInstituteInfo {
	
	
    private int infoDetailId;
	
	private int instituteId;
	

	
	private int noOfFulltimeFaculty ;
	
	private int noNonteachingIncludingOfficeStaff;
	
	private int noSupportStaff ;
 	
	private int noCurrentAdmitedStnt ;
	
	private String academicYear;
	
    private String  treasuryCode; 
  	
	private String rusaIdNo;
	private int exInt1;

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

	public String getAcademicYear() {
		return academicYear;
	}

	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
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

	public int getNoOfFulltimeFaculty() {
		return noOfFulltimeFaculty;
	}

	public void setNoOfFulltimeFaculty(int noOfFulltimeFaculty) {
		this.noOfFulltimeFaculty = noOfFulltimeFaculty;
	}
	public int getExInt1() {
		return exInt1;
	}

	public void setExInt1(int exInt1) {
		this.exInt1 = exInt1;
	}

	@Override
	public String toString() {
		return "GetInstituteInfo [infoDetailId=" + infoDetailId + ", instituteId=" + instituteId
				+ ", noOfFulltimeFaculty=" + noOfFulltimeFaculty + ", noNonteachingIncludingOfficeStaff="
				+ noNonteachingIncludingOfficeStaff + ", noSupportStaff=" + noSupportStaff + ", noCurrentAdmitedStnt="
				+ noCurrentAdmitedStnt + ", academicYear=" + academicYear + ", treasuryCode=" + treasuryCode
				+ ", rusaIdNo=" + rusaIdNo + ", exInt1=" + exInt1 + "]";
	}


	
	
	
	
	
  	
	
	

}
