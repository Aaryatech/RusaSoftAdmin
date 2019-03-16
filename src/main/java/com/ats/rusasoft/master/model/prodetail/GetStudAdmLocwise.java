package com.ats.rusasoft.master.model.prodetail;

public class GetStudAdmLocwise {
	
	private int studentLocId;
	
	private int maleStudent;
	private int femaleStudent;
	private int transStudent;
	private int locTotStudent;
	
	private int locationId;
	private String locationName;

	public int getStudentLocId() {
		return studentLocId;
	}

	public void setStudentLocId(int studentLocId) {
		this.studentLocId = studentLocId;
	}

	public int getMaleStudent() {
		return maleStudent;
	}

	public void setMaleStudent(int maleStudent) {
		this.maleStudent = maleStudent;
	}

	public int getFemaleStudent() {
		return femaleStudent;
	}

	public void setFemaleStudent(int femaleStudent) {
		this.femaleStudent = femaleStudent;
	}

	public int getTransStudent() {
		return transStudent;
	}

	public void setTransStudent(int transStudent) {
		this.transStudent = transStudent;
	}

	public int getLocTotStudent() {
		return locTotStudent;
	}

	public void setLocTotStudent(int locTotStudent) {
		this.locTotStudent = locTotStudent;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	@Override
	public String toString() {
		return "GetStudAdmLocwise [studentLocId=" + studentLocId + ", maleStudent=" + maleStudent + ", femaleStudent="
				+ femaleStudent + ", transStudent=" + transStudent + ", locTotStudent=" + locTotStudent
				+ ", locationId=" + locationId + ", locationName=" + locationName + "]";
	}
	
	
	
	
	/*
	 SELECT t_program_student_location.student_loc_id, m_location.location_name,
t_program_student_location.male_student,t_program_student_location.female_student,t_program_student_location.trans_student,t_program_student_location.loc_tot_student
FROM t_program_student_location
LEFT JOIN  m_location
ON t_program_student_location.location_id=m_location.location_id
WHERE t_program_student_location.is_active=1 AND t_program_student_location.del_status=1
AND t_program_student_location.year_id=1 AND t_program_student_location.institute_id=2
	 */

}
