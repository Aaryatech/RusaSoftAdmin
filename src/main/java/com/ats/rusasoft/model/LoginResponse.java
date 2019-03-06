package com.ats.rusasoft.model;


import java.util.List;




public class LoginResponse {

	    private int userId;
		
		private int userType;
		
		private String userName;
		private String pass;
		private int isBlock;
		private int regPrimaryKey;
		private int roleId;
		
    
		GetUserDetail getData;

		public int getUserId() {
			return userId;
		}

		public void setUserId(int userId) {
			this.userId = userId;
		}

		public int getUserType() {
			return userType;
		}

		public void setUserType(int userType) {
			this.userType = userType;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getPass() {
			return pass;
		}

		public void setPass(String pass) {
			this.pass = pass;
		}

		public int getIsBlock() {
			return isBlock;
		}

		public void setIsBlock(int isBlock) {
			this.isBlock = isBlock;
		}

		public int getRegPrimaryKey() {
			return regPrimaryKey;
		}

		public void setRegPrimaryKey(int regPrimaryKey) {
			this.regPrimaryKey = regPrimaryKey;
		}

		public int getRoleId() {
			return roleId;
		}

		public void setRoleId(int roleId) {
			this.roleId = roleId;
		}

		public GetUserDetail getGetData() {
			return getData;
		}

		public void setGetData(GetUserDetail getData) {
			this.getData = getData;
		}

		@Override
		public String toString() {
			return "LoginResponse [userId=" + userId + ", userType=" + userType + ", userName=" + userName + ", pass="
					+ pass + ", isBlock=" + isBlock + ", regPrimaryKey=" + regPrimaryKey + ", roleId=" + roleId
					+ ", getData=" + getData + "]";
		}
		
	
      
      
	
	
	

}
