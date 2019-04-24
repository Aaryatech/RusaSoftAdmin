package com.ats.rusasoft.model;

public class MExtensionActivity {

	private int extensionActivityId;
	private String activityTitle;
	private int delStatus;
	private int isActive;
	private int sequence;
	public int getExtensionActivityId() {
		return extensionActivityId;
	}
	public void setExtensionActivityId(int extensionActivityId) {
		this.extensionActivityId = extensionActivityId;
	}
	public String getActivityTitle() {
		return activityTitle;
	}
	public void setActivityTitle(String activityTitle) {
		this.activityTitle = activityTitle;
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
		return "MExtensionActivity [extensionActivityId=" + extensionActivityId + ", activityTitle=" + activityTitle
				+ ", delStatus=" + delStatus + ", isActive=" + isActive + ", sequence=" + sequence + "]";
	}
	
	
}
