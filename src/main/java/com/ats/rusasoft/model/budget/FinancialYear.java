package com.ats.rusasoft.model.budget;


public class FinancialYear {
	
	
	private int finYearId ;
	
	private String finYear;
	
	private int isCurrentFy;
	
	private int delStatus;
	private int isActive;
	private int sequence;
	
	
	public int getFinYearId() {
		return finYearId;
	}
	public void setFinYearId(int finYearId) {
		this.finYearId = finYearId;
	}
	public String getFinYear() {
		return finYear;
	}
	public void setFinYear(String finYear) {
		this.finYear = finYear;
	}
	public int getIsCurrentFy() {
		return isCurrentFy;
	}
	public void setIsCurrentFy(int isCurrentFy) {
		this.isCurrentFy = isCurrentFy;
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
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	
	@Override
	public String toString() {
		return "FinancialYear [finYearId=" + finYearId + ", finYear=" + finYear + ", isCurrentFy=" + isCurrentFy
				+ ", delStatus=" + delStatus + ", isActive=" + isActive + ", sequence=" + sequence + "]";
	}
	
}
