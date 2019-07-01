package com.ats.rusasoft.model.graph;

public class StudSupprtSchemGraph {
	
	private int sprtSchmId;
	private String schemeName;
	private int  noStudentBenifited;
	private String supportAgencyName;
	private int noCurrentAdmitedStnt;
	public int getSprtSchmId() {
		return sprtSchmId;
	}
	public void setSprtSchmId(int sprtSchmId) {
		this.sprtSchmId = sprtSchmId;
	}
	public String getSchemeName() {
		return schemeName;
	}
	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}
	public int getNoStudentBenifited() {
		return noStudentBenifited;
	}
	public void setNoStudentBenifited(int noStudentBenifited) {
		this.noStudentBenifited = noStudentBenifited;
	}
	public String getSupportAgencyName() {
		return supportAgencyName;
	}
	public void setSupportAgencyName(String supportAgencyName) {
		this.supportAgencyName = supportAgencyName;
	}
	public int getNoCurrentAdmitedStnt() {
		return noCurrentAdmitedStnt;
	}
	public void setNoCurrentAdmitedStnt(int noCurrentAdmitedStnt) {
		this.noCurrentAdmitedStnt = noCurrentAdmitedStnt;
	}
	@Override
	public String toString() {
		return "StudSupprtSchemGraph [sprtSchmId=" + sprtSchmId + ", schemeName=" + schemeName + ", noStudentBenifited="
				+ noStudentBenifited + ", supportAgencyName=" + supportAgencyName + ", noCurrentAdmitedStnt="
				+ noCurrentAdmitedStnt + "]";
	}
	
	

}
