package com.ats.rusasoft.faculty.model;

public class FacultyPersonalDetail {
	
	private int facultyId;
	
	private String fAddress;
	private String fVillage;
	private String fDistrict;
	private String fTaluka;
	private String fCity;
	private String fState;
	private String fPincode;
	private int isSame;
	
	
	private int isAddSame;
	private String fAddress2;
	private String fPhone;
	private String fResident;
	private String fAadhar;
	private String fPan;
	private String fDob;
	private float fPastExp;
	private int fGender;
	
	private int makerPersUserId;
	private String makerPersDatetime;
	
	public int getFacultyId() {
		return facultyId;
	}
	public void setFacultyId(int facultyId) {
		this.facultyId = facultyId;
	}
	public String getfAddress() {
		return fAddress;
	}
	public void setfAddress(String fAddress) {
		this.fAddress = fAddress;
	}
	public int getIsAddSame() {
		return isAddSame;
	}
	public void setIsAddSame(int isAddSame) {
		this.isAddSame = isAddSame;
	}
	public String getfAddress2() {
		return fAddress2;
	}
	public void setfAddress2(String fAddress2) {
		this.fAddress2 = fAddress2;
	}
	public String getfPhone() {
		return fPhone;
	}
	public void setfPhone(String fPhone) {
		this.fPhone = fPhone;
	}
	public String getfResident() {
		return fResident;
	}
	public void setfResident(String fResident) {
		this.fResident = fResident;
	}
	public String getfAadhar() {
		return fAadhar;
	}
	public void setfAadhar(String fAadhar) {
		this.fAadhar = fAadhar;
	}
	public String getfPan() {
		return fPan;
	}
	public void setfPan(String fPan) {
		this.fPan = fPan;
	}
	public String getfDob() {
		return fDob;
	}
	public void setfDob(String fDob) {
		this.fDob = fDob;
	}
	public float getfPastExp() {
		return fPastExp;
	}
	public void setfPastExp(float fPastExp) {
		this.fPastExp = fPastExp;
	}
	public int getMakerPersUserId() {
		return makerPersUserId;
	}
	public void setMakerPersUserId(int makerPersUserId) {
		this.makerPersUserId = makerPersUserId;
	}
	public String getMakerPersDatetime() {
		return makerPersDatetime;
	}
	public void setMakerPersDatetime(String makerPersDatetime) {
		this.makerPersDatetime = makerPersDatetime;
	}

	public int getfGender() {
		return fGender;
	}
	public void setfGender(int fGender) {
		this.fGender = fGender;
	}
	
	
	public String getfVillage() {
		return fVillage;
	}
	public void setfVillage(String fVillage) {
		this.fVillage = fVillage;
	}
	public String getfDistrict() {
		return fDistrict;
	}
	public void setfDistrict(String fDistrict) {
		this.fDistrict = fDistrict;
	}
	public String getfTaluka() {
		return fTaluka;
	}
	public void setfTaluka(String fTaluka) {
		this.fTaluka = fTaluka;
	}
	public String getfCity() {
		return fCity;
	}
	public void setfCity(String fCity) {
		this.fCity = fCity;
	}
	public String getfState() {
		return fState;
	}
	public void setfState(String fState) {
		this.fState = fState;
	}
	public String getfPincode() {
		return fPincode;
	}
	public void setfPincode(String fPincode) {
		this.fPincode = fPincode;
	}
	public int getIsSame() {
		return isSame;
	}
	public void setIsSame(int isSame) {
		this.isSame = isSame;
	}
	@Override
	public String toString() {
		return "FacultyPersonalDetail [facultyId=" + facultyId + ", fAddress=" + fAddress + ", fVillage=" + fVillage
				+ ", fDistrict=" + fDistrict + ", fTaluka=" + fTaluka + ", fCity=" + fCity + ", fState=" + fState
				+ ", fPincode=" + fPincode + ", isSame=" + isSame + ", isAddSame=" + isAddSame + ", fAddress2="
				+ fAddress2 + ", fPhone=" + fPhone + ", fResident=" + fResident + ", fAadhar=" + fAadhar + ", fPan="
				+ fPan + ", fDob=" + fDob + ", fPastExp=" + fPastExp + ", fGender=" + fGender + ", makerPersUserId="
				+ makerPersUserId + ", makerPersDatetime=" + makerPersDatetime + "]";
	}
	
}
