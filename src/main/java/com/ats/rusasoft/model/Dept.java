package com.ats.rusasoft.model;


public class Dept {
	
	
	private int deptId;
	
	private int instituteId;

	
	private String deptName;
	
	private int delStatus;
	private int isActive;
	private int makerUserId;

	private String addDate;
	
	
	// extra 4 columns
		private int exInt1;
		private int exInt2;

		private String exVar1;
		private String exVar2;
		public int getDeptId() {
			return deptId;
		}
		public void setDeptId(int deptId) {
			this.deptId = deptId;
		}
		public int getInstituteId() {
			return instituteId;
		}
		public void setInstituteId(int instituteId) {
			this.instituteId = instituteId;
		}
		public String getDeptName() {
			return deptName;
		}
		public void setDeptName(String deptName) {
			this.deptName = deptName;
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
		public int getMakerUserId() {
			return makerUserId;
		}
		public void setMakerUserId(int makerUserId) {
			this.makerUserId = makerUserId;
		}
		public String getAddDate() {
			return addDate;
		}
		public void setAddDate(String addDate) {
			this.addDate = addDate;
		}
		public int getExInt1() {
			return exInt1;
		}
		public void setExInt1(int exInt1) {
			this.exInt1 = exInt1;
		}
		public int getExInt2() {
			return exInt2;
		}
		public void setExInt2(int exInt2) {
			this.exInt2 = exInt2;
		}
		public String getExVar1() {
			return exVar1;
		}
		public void setExVar1(String exVar1) {
			this.exVar1 = exVar1;
		}
		public String getExVar2() {
			return exVar2;
		}
		public void setExVar2(String exVar2) {
			this.exVar2 = exVar2;
		}
		
		@Override
		public String toString() {
			return "Dept [deptId=" + deptId + ", instituteId=" + instituteId + ", deptName=" + deptName + ", delStatus="
					+ delStatus + ", isActive=" + isActive + ", makerUserId=" + makerUserId + ", addDate=" + addDate
					+ ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + "]";
		}


}
