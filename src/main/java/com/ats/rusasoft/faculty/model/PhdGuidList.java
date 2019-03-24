package com.ats.rusasoft.faculty.model;



public class PhdGuidList {

	
	private int phdId;
	private String coGuideName;
	private String phdScholarName;
	private String phdRegYear;
	private String phdTopic;
	private String  phdAwardedYear;
	public int getPhdId() {
		return phdId;
	}
	public void setPhdId(int phdId) {
		this.phdId = phdId;
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
	public String getPhdAwardedYear() {
		return phdAwardedYear;
	}
	public void setPhdAwardedYear(String phdAwardedYear) {
		this.phdAwardedYear = phdAwardedYear;
	}
	@Override
	public String toString() {
		return "PhdGuidList [phdId=" + phdId + ", coGuideName=" + coGuideName + ", phdScholarName=" + phdScholarName
				+ ", phdRegYear=" + phdRegYear + ", phdTopic=" + phdTopic + ", phdAwardedYear=" + phdAwardedYear + "]";
	}
	
	
}
