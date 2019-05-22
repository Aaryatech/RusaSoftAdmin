package com.ats.rusasoft.model;
 
 
public class StakeholderFb {
 
	private int feedbackId;
	
	private String feedbackFrom;
	
	private int delStatus;
	
	private int isActive;

	public int getFeedbackId() {
		return feedbackId;
	}

	public void setFeedbackId(int feedbackId) {
		this.feedbackId = feedbackId;
	}

	public String getFeedbackFrom() {
		return feedbackFrom;
	}

	public void setFeedbackFrom(String feedbackFrom) {
		this.feedbackFrom = feedbackFrom;
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

	@Override
	public String toString() {
		return "StakeholderFeedback [feedbackId=" + feedbackId + ", feedbackFrom=" + feedbackFrom + ", delStatus="
				+ delStatus + ", isActive=" + isActive + ", getFeedbackId()=" + getFeedbackId() + ", getFeedbackFrom()="
				+ getFeedbackFrom() + ", getDelStatus()=" + getDelStatus() + ", getIsActive()=" + getIsActive()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
	
	
	
}
