package com.ats.rusasoft.model;

public class FacultyPhdGuide {

	private int phdId;
	private int facultyId;
	private int yearId;
	private int	isPhdGuide;
	private int	isCoGuide;
	private String coGuideName;
	private String phdScholarName;
	private String aadhaarNo;
	private String placeOfWork;
	private String phdRegYear;
	private String phdTopic;
	private String university;
	private int isPhdAwarded;
	private int	phdAwardedYear;
	private int delStatus;
	private int	isActive;
	private int makerUserId;
	private String makerEnterDatetime;
	private int exInt1;
	private String exVar1;
	
	public int getPhdId() {
		return phdId;
	}
	public void setPhdId(int phdId) {
		this.phdId = phdId;
	}
	public int getFacultyId() {
		return facultyId;
	}
	public void setFacultyId(int facultyId) {
		this.facultyId = facultyId;
	}
	public int getYearId() {
		return yearId;
	}
	public void setYearId(int yearId) {
		this.yearId = yearId;
	}
	public int getIsPhdGuide() {
		return isPhdGuide;
	}
	public void setIsPhdGuide(int isPhdGuide) {
		this.isPhdGuide = isPhdGuide;
	}
	public int getIsCoGuide() {
		return isCoGuide;
	}
	public void setIsCoGuide(int isCoGuide) {
		this.isCoGuide = isCoGuide;
	}
	public String getCoGuideName() {
		return coGuideName;
	}
	public void setCoGuideName(String coGuideName) {
		this.coGuideName = coGuideName;
	}
	public String getPhdScholarName() {
		return phdScholarName;
	}
	public void setPhdScholarName(String phdScholarName) {
		this.phdScholarName = phdScholarName;
	}
	
	public String getAadhaarNo() {
		return aadhaarNo;
	}
	public void setAadhaarNo(String aadhaarNo) {
		this.aadhaarNo = aadhaarNo;
	}
	public String getPlaceOfWork() {
		return placeOfWork;
	}
	public void setPlaceOfWork(String placeOfWork) {
		this.placeOfWork = placeOfWork;
	}
	public String getPhdRegYear() {
		return phdRegYear;
	}
	public void setPhdRegYear(String phdRegYear) {
		this.phdRegYear = phdRegYear;
	}
	public String getPhdTopic() {
		return phdTopic;
	}
	public void setPhdTopic(String phdTopic) {
		this.phdTopic = phdTopic;
	}
	
	public String getUniversity() {
		return university;
	}
	public void setUniversity(String university) {
		this.university = university;
	}
	public int getIsPhdAwarded() {
		return isPhdAwarded;
	}
	public void setIsPhdAwarded(int isPhdAwarded) {
		this.isPhdAwarded = isPhdAwarded;
	}
	public int getPhdAwardedYear() {
		return phdAwardedYear;
	}
	public void setPhdAwardedYear(int phdAwardedYear) {
		this.phdAwardedYear = phdAwardedYear;
	}
	public int getDelStatus() {
		return delStatus;
	}
	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}
	public int getIsActive() {
		return isActive;
	}
	public void setIsActive(int isActive) {
		this.isActive = isActive;
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
	public int getExInt1() {
		return exInt1;
	}
	public void setExInt1(int exInt1) {
		this.exInt1 = exInt1;
	}
	public String getExVar1() {
		return exVar1;
	}
	public void setExVar1(String exVar1) {
		this.exVar1 = exVar1;
	}
	@Override
	public String toString() {
		return "FacultyPhdGuide [phdId=" + phdId + ", facultyId=" + facultyId + ", yearId=" + yearId + ", isPhdGuide="
				+ isPhdGuide + ", isCoGuide=" + isCoGuide + ", coGuideName=" + coGuideName + ", phdScholarName="
				+ phdScholarName + ", aadhaarNo=" + aadhaarNo + ", placeOfWork=" + placeOfWork + ", phdRegYear="
				+ phdRegYear + ", phdTopic=" + phdTopic + ", university=" + university + ", isPhdAwarded="
				+ isPhdAwarded + ", phdAwardedYear=" + phdAwardedYear + ", delStatus=" + delStatus + ", isActive="
				+ isActive + ", makerUserId=" + makerUserId + ", makerEnterDatetime=" + makerEnterDatetime + ", exInt1="
				+ exInt1 + ", exVar1=" + exVar1 + "]";
	}

	
}
