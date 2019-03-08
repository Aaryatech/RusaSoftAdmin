package com.ats.rusasoft.model;




public class AcademicYear {
	

	
	private int yearId;

	private String academicYear;
	
	private int makerId;
	
	private String makerDatetime;
	
	private int type;
	
	private int delStatus;

	public int getYearId() {
		return yearId;
	}

	public void setYearId(int yearId) {
		this.yearId = yearId;
	}

	public String getAcademicYear() {
		return academicYear;
	}

	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
	}

	public int getMakerId() {
		return makerId;
	}

	public void setMakerId(int makerId) {
		this.makerId = makerId;
	}

	public String getMakerDatetime() {
		return makerDatetime;
	}

	public void setMakerDatetime(String makerDatetime) {
		this.makerDatetime = makerDatetime;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	@Override
	public String toString() {
		return "AcademicYear [yearId=" + yearId + ", academicYear=" + academicYear + ", makerId=" + makerId
				+ ", makerDatetime=" + makerDatetime + ", type=" + type + ", delStatus=" + delStatus + ", getYearId()="
				+ getYearId() + ", getAcademicYear()=" + getAcademicYear() + ", getMakerId()=" + getMakerId()
				+ ", getMakerDatetime()=" + getMakerDatetime() + ", getType()=" + getType() + ", getDelStatus()="
				+ getDelStatus() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
	
	
	
}
