package com.ats.rusasoft.model;

public class StudPerformFinalYrList {
	private int studPerformId;
	private String progName;
	private String progType;
	private int noStudAppear;
	private int noStudPass;
	private int passingPer;
	private int delStatus;
	public int getStudPerformId() {
		return studPerformId;
	}
	public void setStudPerformId(int studPerformId) {
		this.studPerformId = studPerformId;
	}
	public String getProgName() {
		return progName;
	}
	public void setProgName(String progName) {
		this.progName = progName;
	}
	public String getProgType() {
		return progType;
	}
	public void setProgType(String progType) {
		this.progType = progType;
	}
	public int getNoStudAppear() {
		return noStudAppear;
	}
	public void setNoStudAppear(int noStudAppear) {
		this.noStudAppear = noStudAppear;
	}
	public int getNoStudPass() {
		return noStudPass;
	}
	public void setNoStudPass(int noStudPass) {
		this.noStudPass = noStudPass;
	}
	public int getPassingPer() {
		return passingPer;
	}
	public void setPassingPer(int passingPer) {
		this.passingPer = passingPer;
	}
	public int getDelStatus() {
		return delStatus;
	}
	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}
	@Override
	public String toString() {
		return "StudPerformFinalYrList [studPerformId=" + studPerformId + ", progName=" + progName + ", progType="
				+ progType + ", noStudAppear=" + noStudAppear + ", noStudPass=" + noStudPass + ", passingPer="
				+ passingPer + ", delStatus=" + delStatus + "]";
	}
	
	
	
	
}
