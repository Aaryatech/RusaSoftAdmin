package com.ats.rusasoft.model.reports;

 
public class AdmissionsAgainstCategory {

	 
	private int castId;

	private int studentCatId;

	private float catTotStudent;

	private float seatsAvaailable;
	
	private String castName;
	
	private String academicYear;
	
	private String instituteName;
	
	private int yearId;

	public int getCastId() {
		return castId;
	}

	public void setCastId(int castId) {
		this.castId = castId;
	}

	public int getStudentCatId() {
		return studentCatId;
	}

	public void setStudentCatId(int studentCatId) {
		this.studentCatId = studentCatId;
	}

	public float getCatTotStudent() {
		return catTotStudent;
	}

	public void setCatTotStudent(float catTotStudent) {
		this.catTotStudent = catTotStudent;
	}

	public float getSeatsAvaailable() {
		return seatsAvaailable;
	}

	public void setSeatsAvaailable(float seatsAvaailable) {
		this.seatsAvaailable = seatsAvaailable;
	}

	public String getCastName() {
		return castName;
	}

	public void setCastName(String castName) {
		this.castName = castName;
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

	public int getYearId() {
		return yearId;
	}

	public void setYearId(int yearId) {
		this.yearId = yearId;
	}

	@Override
	public String toString() {
		return "AdmissionsAgainstCategory [castId=" + castId + ", studentCatId=" + studentCatId + ", catTotStudent="
				+ catTotStudent + ", seatsAvaailable=" + seatsAvaailable + ", castName=" + castName + ", academicYear="
				+ academicYear + ", instituteName=" + instituteName + ", yearId=" + yearId + ", getCastId()="
				+ getCastId() + ", getStudentCatId()=" + getStudentCatId() + ", getCatTotStudent()="
				+ getCatTotStudent() + ", getSeatsAvaailable()=" + getSeatsAvaailable() + ", getCastName()="
				+ getCastName() + ", getAcademicYear()=" + getAcademicYear() + ", getInstituteName()="
				+ getInstituteName() + ", getYearId()=" + getYearId() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	

}
