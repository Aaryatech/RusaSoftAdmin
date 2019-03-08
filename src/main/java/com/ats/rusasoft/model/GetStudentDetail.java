package com.ats.rusasoft.model;



public class GetStudentDetail {
	

    private int studentId;
	
	private int instituteId;
	
	private int deptId;
	
	private String studentName;
	
	private String idNo;
	
	private String contactNo;
	
	private String email;
	
	private String instituteName;
	
	private String deptName;
	
	private String academicYear;

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getInstituteId() {
		return instituteId;
	}

	public void setInstituteId(int instituteId) {
		this.instituteId = instituteId;
	}

	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
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

	public String getInstituteName() {
		return instituteName;
	}

	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getAcademicYear() {
		return academicYear;
	}

	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
	}

	@Override
	public String toString() {
		return "getStudentDetail [studentId=" + studentId + ", instituteId=" + instituteId + ", deptId=" + deptId
				+ ", studentName=" + studentName + ", idNo=" + idNo + ", contactNo=" + contactNo + ", email=" + email
				+ ", instituteName=" + instituteName + ", deptName=" + deptName + ", academicYear=" + academicYear
				+ ", getStudentId()=" + getStudentId() + ", getInstituteId()=" + getInstituteId() + ", getDeptId()="
				+ getDeptId() + ", getStudentName()=" + getStudentName() + ", getIdNo()=" + getIdNo()
				+ ", getContactNo()=" + getContactNo() + ", getEmail()=" + getEmail() + ", getInstituteName()="
				+ getInstituteName() + ", getDeptName()=" + getDeptName() + ", getAcademicYear()=" + getAcademicYear()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

	
	
	
	
	
	

}
