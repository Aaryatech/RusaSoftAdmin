package com.ats.rusasoft.model;


public class OrganizedList {
	private String typeName;
	private int	activityId;
	//private String activityType;
	private String activityName;
	private String activityLevel;
	private String activityDate;
	private String	activityParticipants;
	private String	activityFundedBy;
	private String	activityAmountSanctioned	;
	private String	activityAmountUtilised;
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
	public String getActivityDate() {
		return activityDate;
	}
	public void setActivityDate(String activityDate) {
		this.activityDate = activityDate;
	}
	public String getActivityParticipants() {
		return activityParticipants;
	}
	public void setActivityParticipants(String activityParticipants) {
		this.activityParticipants = activityParticipants;
	}
	public String getActivityFundedBy() {
		return activityFundedBy;
	}
	public void setActivityFundedBy(String activityFundedBy) {
		this.activityFundedBy = activityFundedBy;
	}
	public String getActivityAmountSanctioned() {
		return activityAmountSanctioned;
	}
	public void setActivityAmountSanctioned(String activityAmountSanctioned) {
		this.activityAmountSanctioned = activityAmountSanctioned;
	}
	public String getActivityAmountUtilised() {
		return activityAmountUtilised;
	}
	public void setActivityAmountUtilised(String activityAmountUtilised) {
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
