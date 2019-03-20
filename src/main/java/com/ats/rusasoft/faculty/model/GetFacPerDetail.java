package com.ats.rusasoft.faculty.model;

public class GetFacPerDetail {

	private int facultyId;
	private float fPastExp;
	private String facultyName;
	private String  contactNo;
	private String email;
	private String designationName;
	private float currentExperience;
	public int getFacultyId() {
		return facultyId;
	}
	public void setFacultyId(int facultyId) {
		this.facultyId = facultyId;
	}
	public float getfPastExp() {
		return fPastExp;
	}
	public void setfPastExp(float fPastExp) {
		this.fPastExp = fPastExp;
	}
	public String getFacultyName() {
		return facultyName;
	}
	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDesignationName() {
		return designationName;
	}
	public void setDesignationName(String designationName) {
		this.designationName = designationName;
	}
	public float getCurrentExperience() {
		return currentExperience;
	}
	public void setCurrentExperience(float currentExperience) {
		this.currentExperience = currentExperience;
	}
	
	@Override
	public String toString() {
		return "GetFacPerDetail [facultyId=" + facultyId + ", fPastExp=" + fPastExp + ", facultyName=" + facultyName
				+ ", contactNo=" + contactNo + ", email=" + email + ", designationName=" + designationName
				+ ", currentExperience=" + currentExperience + "]";
	}
	
	/*SELECT m_faculty.faculty_id,m_faculty.faculty_name,m_faculty.contact_no,m_faculty.email,m_designation.designation_name,TIMESTAMPDIFF(Month,m_faculty.joining_date,'2019-03-19') as current_experience
	 * ,m_faculty.f_past_exp,
FROM m_designation,m_faculty WHERE m_designation.designation_id=m_faculty.current_designation_id
 AND m_faculty.institute_id=:instId AND m_faculty.del_status=1 AND m_faculty.is_active=1*/
}
