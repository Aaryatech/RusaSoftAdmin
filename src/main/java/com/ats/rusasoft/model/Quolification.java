package com.ats.rusasoft.model;


public class Quolification {
	
	private int  qualificationId;
	
	private String  qualificationName ;
	
	private int  makerId;
	
	private String addDate;
 	private int delStatus;

 	private int type;

	public int getQualificationId() {
		return qualificationId;
	}

	public void setQualificationId(int qualificationId) {
		this.qualificationId = qualificationId;
	}

	public String getQualificationName() {
		return qualificationName;
	}

	public void setQualificationName(String qualificationName) {
		this.qualificationName = qualificationName;
	}

	public int getMakerId() {
		return makerId;
	}

	public void setMakerId(int makerId) {
		this.makerId = makerId;
	}

	public String getAddDate() {
		return addDate;
	}

	public void setAddDate(String addDate) {
		this.addDate = addDate;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Quolification [qualificationId=" + qualificationId + ", qualificationName=" + qualificationName
				+ ", makerId=" + makerId + ", addDate=" + addDate + ", delStatus=" + delStatus + ", type=" + type + "]";
	}
 	
 	
 	
	
 	

}
