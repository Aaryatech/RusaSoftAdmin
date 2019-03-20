package com.ats.rusasoft.faculty.model;

public class GetFacAcademic {
	
	private int fAcaId;
	
	private int facultyId;
	private String facultyName;
	
	private String fClass;
	public String getFacultyName() {
		return facultyName;
	}

	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}

	private String  fUniversity;
	private String fPassingYear;
	
	private String qualificationName;

	public int getfAcaId() {
		return fAcaId;
	}

	public void setfAcaId(int fAcaId) {
		this.fAcaId = fAcaId;
	}

	public int getFacultyId() {
		return facultyId;
	}

	public void setFacultyId(int facultyId) {
		this.facultyId = facultyId;
	}

	public String getfClass() {
		return fClass;
	}

	public void setfClass(String fClass) {
		this.fClass = fClass;
	}

	public String getfUniversity() {
		return fUniversity;
	}

	public void setfUniversity(String fUniversity) {
		this.fUniversity = fUniversity;
	}

	public String getfPassingYear() {
		return fPassingYear;
	}

	public void setfPassingYear(String fPassingYear) {
		this.fPassingYear = fPassingYear;
	}

	public String getQualificationName() {
		return qualificationName;
	}

	public void setQualificationName(String qualificationName) {
		this.qualificationName = qualificationName;
	}

	@Override
	public String toString() {
		return "GetFacAcademic [fAcaId=" + fAcaId + ", facultyId=" + facultyId + ", facultyName=" + facultyName
				+ ", fClass=" + fClass + ", fUniversity=" + fUniversity + ", fPassingYear=" + fPassingYear
				+ ", qualificationName=" + qualificationName + "]";
	}
	
	/*
	 SELECT t_faculty_academic.f_aca_id, m_faculty.faculty_id,m_faculty.faculty_first_name,m_qualificatoin.qualification_name,
t_faculty_academic.f_class,t_faculty_academic.f_university,t_faculty_academic.f_passing_year
FROM m_faculty,t_faculty_academic,m_qualificatoin
WHERE m_faculty.faculty_id=t_faculty_academic.faculty_id AND t_faculty_academic.f_qualification_id=m_qualificatoin.qualification_id
AND t_faculty_academic.del_status=1 AND t_faculty_academic.is_active=1 AND m_faculty.institute_id=2  
ORDER BY m_qualificatoin.f_aca_id  DESC
	 */

}
