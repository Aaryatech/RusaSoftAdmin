package com.ats.rusasoft.model.graph;
 
public class SancIntakeStudAdmittedGraph {
 	private int yearId;
	
	private  float  noCurrentAdmitedStnt;
	
	private String instituteName;
	
	private  float totalSancIntake;
	
	
	private String  academicYear;


	public int getYearId() {
		return yearId;
	}


	public void setYearId(int yearId) {
		this.yearId = yearId;
	}


	public float getNoCurrentAdmitedStnt() {
		return noCurrentAdmitedStnt;
	}


	public void setNoCurrentAdmitedStnt(float noCurrentAdmitedStnt) {
		this.noCurrentAdmitedStnt = noCurrentAdmitedStnt;
	}


	public String getInstituteName() {
		return instituteName;
	}


	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}


	public float getTotalSancIntake() {
		return totalSancIntake;
	}


	public void setTotalSancIntake(float totalSancIntake) {
		this.totalSancIntake = totalSancIntake;
	}


	public String getAcademicYear() {
		return academicYear;
	}


	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
	}


	@Override
	public String toString() {
		return "SancIntakeStudAdmittedGraph [yearId=" + yearId + ", noCurrentAdmitedStnt=" + noCurrentAdmitedStnt
				+ ", instituteName=" + instituteName + ", totalSancIntake=" + totalSancIntake + ", academicYear="
				+ academicYear + ", getYearId()=" + getYearId() + ", getNoCurrentAdmitedStnt()="
				+ getNoCurrentAdmitedStnt() + ", getInstituteName()=" + getInstituteName() + ", getTotalSancIntake()="
				+ getTotalSancIntake() + ", getAcademicYear()=" + getAcademicYear() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	
	

}
