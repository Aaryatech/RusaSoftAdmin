package com.ats.rusasoft.model;

public class ResearchDevMou {


	private int researchDevMouId;
	private String mouTitle;
	private int delStatus;
	private int Sequence;
	private int isActive;
	public int getResearchDevMouId() {
		return researchDevMouId;
	}
	public void setResearchDevMouId(int researchDevMouId) {
		this.researchDevMouId = researchDevMouId;
	}
	public String getMouTitle() {
		return mouTitle;
	}
	public void setMouTitle(String mouTitle) {
		this.mouTitle = mouTitle;
	}
	public int getDelStatus() {
		return delStatus;
	}
	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}
	public int getSequence() {
		return Sequence;
	}
	public void setSequence(int sequence) {
		Sequence = sequence;
	}
	public int getIsActive() {
		return isActive;
	}
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	@Override
	public String toString() {
		return "ResearchDevMou [researchDevMouId=" + researchDevMouId + ", mouTitle=" + mouTitle + ", delStatus="
				+ delStatus + ", Sequence=" + Sequence + ", isActive=" + isActive + "]";
	}
	
}
