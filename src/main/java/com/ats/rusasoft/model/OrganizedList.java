package com.ats.rusasoft.model;

import java.util.Date;

public class OrganizedList {
	private String typeName;
	private int	activityId;
	//private String activityType;
	private String activityName;
	private String activityLevel;
	private Date activityDate;
	private int	activityParticipants;
	private String	activityFundedBy;
	private int	activityAmountSanctioned	;
	private int	activityAmountUtilised;
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public int getActivityId() {
		return activityId;
	}
	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}

	/*
	 * public String getActivityType() { return activityType; } public void
	 * setActivityType(String activityType) { this.activityType = activityType; }
	 */
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public String getActivityLevel() {
		return activityLevel;
	}
	public void setActivityLevel(String activityLevel) {
		this.activityLevel = activityLevel;
	}
	public Date getActivityDate() {
		return activityDate;
	}
	public void setActivityDate(Date activityDate) {
		this.activityDate = activityDate;
	}
	public int getActivityParticipants() {
		return activityParticipants;
	}
	public void setActivityParticipants(int activityParticipants) {
		this.activityParticipants = activityParticipants;
	}
	public String getActivityFundedBy() {
		return activityFundedBy;
	}
	public void setActivityFundedBy(String activityFundedBy) {
		this.activityFundedBy = activityFundedBy;
	}
	public int getActivityAmountSanctioned() {
		return activityAmountSanctioned;
	}
	public void setActivityAmountSanctioned(int activityAmountSanctioned) {
		this.activityAmountSanctioned = activityAmountSanctioned;
	}
	public int getActivityAmountUtilised() {
		return activityAmountUtilised;
	}
	public void setActivityAmountUtilised(int activityAmountUtilised) {
		this.activityAmountUtilised = activityAmountUtilised;
	}
	@Override
	public String toString() {
		return "OrganizedList [typeName=" + typeName + ", activityId=" + activityId + ", activityName=" + activityName + ", activityLevel=" + activityLevel + ", activityDate="
				+ activityDate + ", activityParticipants=" + activityParticipants + ", activityFundedBy="
				+ activityFundedBy + ", activityAmountSanctioned=" + activityAmountSanctioned
				+ ", activityAmountUtilised=" + activityAmountUtilised + "]";
	}
	
	

}
