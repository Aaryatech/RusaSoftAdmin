package com.ats.rusasoft.faculty.model;

public class FacultyPhdDetails {
	
	private int facultyId;
	private int isPhdGuide;
	
	private String phdRecognitionDt; //date
	private String phdValidDt;//date

	private int phdStuPg;
	private int phdStuMphill;
	private int phdStuPhd;
	
	private int makerPhdUserId;
	private String makerPhdDatetime;
	private int isIctUsed;
	
	public int getFacultyId() {
		return facultyId;
	}
	public void setFacultyId(int facultyId) {
		this.facultyId = facultyId;
	}
	public int getIsPhdGuide() {
		return isPhdGuide;
	}
	public void setIsPhdGuide(int isPhdGuide) {
		this.isPhdGuide = isPhdGuide;
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
	public int getPhdStuPg() {
		return phdStuPg;
	}
	public void setPhdStuPg(int phdStuPg) {
		this.phdStuPg = phdStuPg;
	}
	public int getPhdStuMphill() {
		return phdStuMphill;
	}
	public void setPhdStuMphill(int phdStuMphill) {
		this.phdStuMphill = phdStuMphill;
	}
	public int getPhdStuPhd() {
		return phdStuPhd;
	}
	public void setPhdStuPhd(int phdStuPhd) {
		this.phdStuPhd = phdStuPhd;
	}
	public int getMakerPhdUserId() {
		return makerPhdUserId;
	}
	public void setMakerPhdUserId(int makerPhdUserId) {
		this.makerPhdUserId = makerPhdUserId;
	}
	public String getMakerPhdDatetime() {
		return makerPhdDatetime;
	}
	public void setMakerPhdDatetime(String makerPhdDatetime) {
		this.makerPhdDatetime = makerPhdDatetime;
	}
	public int getIsIctUsed() {
		return isIctUsed;
	}
	public void setIsIctUsed(int isIctUsed) {
		this.isIctUsed = isIctUsed;
	}
	
	@Override
	public String toString() {
		return "FacultyPhdDetails [facultyId=" + facultyId + ", isPhdGuide=" + isPhdGuide + ", phdRecognitionDt="
				+ phdRecognitionDt + ", phdValidDt=" + phdValidDt + ", phdStuPg=" + phdStuPg + ", phdStuMphill="
				+ phdStuMphill + ", phdStuPhd=" + phdStuPhd + ", makerPhdUserId=" + makerPhdUserId
				+ ", makerPhdDatetime=" + makerPhdDatetime + ", isIctUsed=" + isIctUsed + "]";
	}

}
