package com.ats.rusasoft.master.model.prodetail;


public class GetHigherEduDetail {
	
	private int educationDetailId;

	private int noStudent;

	private String fromProgType;
	
	private String toProgType;

	public int getEducationDetailId() {
		return educationDetailId;
	}

	public void setEducationDetailId(int educationDetailId) {
		this.educationDetailId = educationDetailId;
	}

	public int getNoStudent() {
		return noStudent;
	}

	public void setNoStudent(int noStudent) {
		this.noStudent = noStudent;
	}

	public String getFromProgType() {
		return fromProgType;
	}

	public void setFromProgType(String fromProgType) {
		this.fromProgType = fromProgType;
	}

	public String getToProgType() {
		return toProgType;
	}

	public void setToProgType(String toProgType) {
		this.toProgType = toProgType;
	}

	@Override
	public String toString() {
		return "GetHigherEduDetail [educationDetailId=" + educationDetailId + ", noStudent=" + noStudent
				+ ", fromProgType=" + fromProgType + ", toProgType=" + toProgType + "]";
	}
	
}
