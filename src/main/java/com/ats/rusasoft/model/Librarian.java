package com.ats.rusasoft.model;

public class Librarian {
	
	
      private int librarianId;
	
	private int instituteId;
	
	private String librarianName;
	
	private String contactNo;
	
	private String email;
	
	private int qualificationId;
	
	private String joiningDate;
	
	private String realivingDate;
	
	private int makerUserId;
	
	private String makerEnterDatetime;

	public int getLibrarianId() {
		return librarianId;
	}

	public void setLibrarianId(int librarianId) {
		this.librarianId = librarianId;
	}

	public int getInstituteId() {
		return instituteId;
	}

	public void setInstituteId(int instituteId) {
		this.instituteId = instituteId;
	}

	public String getLibrarianName() {
		return librarianName;
	}

	public void setLibrarianName(String librarianName) {
		this.librarianName = librarianName;
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

	public int getQualificationId() {
		return qualificationId;
	}

	public void setQualificationId(int qualificationId) {
		this.qualificationId = qualificationId;
	}

	public String getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(String joiningDate) {
		this.joiningDate = joiningDate;
	}

	public String getRealivingDate() {
		return realivingDate;
	}

	public void setRealivingDate(String realivingDate) {
		this.realivingDate = realivingDate;
	}

	public int getMakerUserId() {
		return makerUserId;
	}

	public void setMakerUserId(int makerUserId) {
		this.makerUserId = makerUserId;
	}

	public String getMakerEnterDatetime() {
		return makerEnterDatetime;
	}

	public void setMakerEnterDatetime(String makerEnterDatetime) {
		this.makerEnterDatetime = makerEnterDatetime;
	}

	@Override
	public String toString() {
		return "Librarian [librarianId=" + librarianId + ", instituteId=" + instituteId + ", librarianName="
				+ librarianName + ", contactNo=" + contactNo + ", email=" + email + ", qualificationId="
				+ qualificationId + ", joiningDate=" + joiningDate + ", realivingDate=" + realivingDate
				+ ", makerUserId=" + makerUserId + ", makerEnterDatetime=" + makerEnterDatetime + "]";
	}
	
	

}
