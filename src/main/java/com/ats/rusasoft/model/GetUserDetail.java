package com.ats.rusasoft.model;




public class GetUserDetail {
	
private int userDetailId;
	
	private String subUserName;
	
	private String userConNumber;
	
	private String userEmail;
	
	private int userDesnId;
	
	private int userQualId;
	
	private int userInstituteId;
	
	private int isEnroll;
	
	private int deptId;
	

	public int getUserDetailId() {
		return userDetailId;
	}

	public void setUserDetailId(int userDetailId) {
		this.userDetailId = userDetailId;
	}

	public String getSubUserName() {
		return subUserName;
	}

	public void setSubUserName(String subUserName) {
		this.subUserName = subUserName;
	}

	public String getUserConNumber() {
		return userConNumber;
	}

	public void setUserConNumber(String userConNumber) {
		this.userConNumber = userConNumber;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public int getUserDesnId() {
		return userDesnId;
	}

	public void setUserDesnId(int userDesnId) {
		this.userDesnId = userDesnId;
	}

	public int getUserQualId() {
		return userQualId;
	}

	public void setUserQualId(int userQualId) {
		this.userQualId = userQualId;
	}

	public int getUserInstituteId() {
		return userInstituteId;
	}

	public void setUserInstituteId(int userInstituteId) {
		this.userInstituteId = userInstituteId;
	}

	public int getIsEnroll() {
		return isEnroll;
	}

	public void setIsEnroll(int isEnroll) {
		this.isEnroll = isEnroll;
	}
	
	

	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	@Override
	public String toString() {
		return "GetUserDetail [userDetailId=" + userDetailId + ", subUserName=" + subUserName + ", userConNumber="
				+ userConNumber + ", userEmail=" + userEmail + ", userDesnId=" + userDesnId + ", userQualId="
				+ userQualId + ", userInstituteId=" + userInstituteId + ", isEnroll=" + isEnroll + ", deptId=" + deptId
				+ ", getUserDetailId()=" + getUserDetailId() + ", getSubUserName()=" + getSubUserName()
				+ ", getUserConNumber()=" + getUserConNumber() + ", getUserEmail()=" + getUserEmail()
				+ ", getUserDesnId()=" + getUserDesnId() + ", getUserQualId()=" + getUserQualId()
				+ ", getUserInstituteId()=" + getUserInstituteId() + ", getIsEnroll()=" + getIsEnroll()
				+ ", getDeptId()=" + getDeptId() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

	


	
}
