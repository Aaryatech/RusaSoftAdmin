package com.ats.rusasoft.master.model.prodetail;

public class Location {
	
	private int locationId;
	
	private String locationName;
	private String locRemark;
	
	private int delStatus;
	private int isActive;
	public int getLocationId() {
		return locationId;
	}
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getLocRemark() {
		return locRemark;
	}
	public void setLocRemark(String locRemark) {
		this.locRemark = locRemark;
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
		return "Location [locationId=" + locationId + ", locationName=" + locationName + ", locRemark=" + locRemark
				+ ", delStatus=" + delStatus + ", isActive=" + isActive + "]";
	}

}
