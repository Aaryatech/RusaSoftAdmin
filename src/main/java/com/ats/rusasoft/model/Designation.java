package com.ats.rusasoft.model;

import java.util.Date;


public class Designation {

	
	private int designationId;
	private String designationName;
	private int delStatus;
	private int isActive;
	private String addDate;
	private int markerUserId;
	public int getDesignationId() {
		return designationId;
	}
	public void setDesignationId(int designationId) {
		this.designationId = designationId;
	}
	public String getDesignationName() {
		return designationName;
	}
	public void setDesignationName(String designationName) {
		this.designationName = designationName;
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
	public String getAddDate() {
		return addDate;
	}
	public void setAddDate(String addDate) {
		this.addDate = addDate;
	}
	public int getMarkerUserId() {
		return markerUserId;
	}
	public void setMarkerUserId(int markerUserId) {
		this.markerUserId = markerUserId;
	}
	@Override
	public String toString() {
		return "Designation [designationId=" + designationId + ", designationName=" + designationName + ", delStatus="
				+ delStatus + ", isActive=" + isActive + ", addDate=" + addDate + ", markerUserId=" + markerUserId
				+ "]";
	}
	
	
	
}
