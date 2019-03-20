package com.ats.rusasoft.faculty.model;

public class GetFacPhdGuideDetail {
	
	private int facultyId;
	
	private String facultyFirstName; 
	
	private String phdRecognitionDt; //date
	private String phdValidDt;//date
	
	private int noOfStud ;
	
	private int isIctUsed;

	public int getFacultyId() {
		return facultyId;
	}

	public void setFacultyId(int facultyId) {
		this.facultyId = facultyId;
	}

	public String getFacultyFirstName() {
		return facultyFirstName;
	}

	public void setFacultyFirstName(String facultyFirstName) {
		this.facultyFirstName = facultyFirstName;
	}

	public String getPhdRecognitionDt() {
		return phdRecognitionDt;
	}

	public void setPhdRecognitionDt(String phdRecognitionDt) {
		this.phdRecognitionDt = phdRecognitionDt;
	}

	public String getPhdValidDt() {
		return phdValidDt;
	}

	public void setPhdValidDt(String phdValidDt) {
		this.phdValidDt = phdValidDt;
	}

	public int getNoOfStud() {
		return noOfStud;
	}

	public void setNoOfStud(int noOfStud) {
		this.noOfStud = noOfStud;
	}

	public int getIsIctUsed() {
		return isIctUsed;
	}

	public void setIsIctUsed(int isIctUsed) {
		this.isIctUsed = isIctUsed;
	}

	@Override
	public String toString() {
		return "GetFacPhdGuideDetail [facultyId=" + facultyId + ", facultyFirstName=" + facultyFirstName
				+ ", phdRecognitionDt=" + phdRecognitionDt + ", phdValidDt=" + phdValidDt + ", noOfStud=" + noOfStud
				+ ", isIctUsed=" + isIctUsed + "]";
	} 
/*
 SELECT m_faculty.faculty_id,m_faculty.faculty_first_name,m_faculty.phd_recognition_dt,m_faculty.phd_stu_pg+m_faculty.phd_stu_mphill+m_faculty.phd_stu_phd as no_of_stud, m_faculty.phd_valid_dt, m_faculty.is_ict_used 
FROM m_faculty WHERE m_faculty.institute_id=2 AND m_faculty.del_status=1 AND m_faculty.is_active=1 AND m_faculty.is_phd_guide=1
 */
}
