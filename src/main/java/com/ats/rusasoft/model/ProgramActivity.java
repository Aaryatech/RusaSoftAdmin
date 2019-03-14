package com.ats.rusasoft.model;
 
public class ProgramActivity {
	 
	private int studentActivityId; 
	private int instituteId; 
	private int yearId; 
	private String activityName; 
	private String otherCompitition; 
	private String date; 
	private String year; 
	private String branch; 
	private int participatedStudent; 
	private String level;  
	private int isActive; 
	private int delStatus; 
	private String addDate; 
	private int makerUserId; 
	private int exInt1; 
	private int exInt2; 
	private String exVar1; 
	private String exVar2; 
	private int type;
	private String yearName;
	
	public int getStudentActivityId() {
		return studentActivityId;
	}
	public void setStudentActivityId(int studentActivityId) {
		this.studentActivityId = studentActivityId;
	}
	public int getInstituteId() {
		return instituteId;
	}
	public void setInstituteId(int instituteId) {
		this.instituteId = instituteId;
	}
	public int getYearId() {
		return yearId;
	}
	public void setYearId(int yearId) {
		this.yearId = yearId;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public String getOtherCompitition() {
		return otherCompitition;
	}
	public void setOtherCompitition(String otherCompitition) {
		this.otherCompitition = otherCompitition;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public int getParticipatedStudent() {
		return participatedStudent;
	}
	public void setParticipatedStudent(int participatedStudent) {
		this.participatedStudent = participatedStudent;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public int getIsActive() {
		return isActive;
	}
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	public int getDelStatus() {
		return delStatus;
	}
	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}
	public String getAddDate() {
		return addDate;
	}
	public void setAddDate(String addDate) {
		this.addDate = addDate;
	}
	public int getMakerUserId() {
		return makerUserId;
	}
	public void setMakerUserId(int makerUserId) {
		this.makerUserId = makerUserId;
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
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getYearName() {
		return yearName;
	}
	public void setYearName(String yearName) {
		this.yearName = yearName;
	}
	@Override
	public String toString() {
		return "ProgramActivity [studentActivityId=" + studentActivityId + ", instituteId=" + instituteId + ", yearId="
				+ yearId + ", activityName=" + activityName + ", otherCompitition=" + otherCompitition + ", date="
				+ date + ", year=" + year + ", branch=" + branch + ", participatedStudent=" + participatedStudent
				+ ", level=" + level + ", isActive=" + isActive + ", delStatus=" + delStatus + ", addDate=" + addDate
				+ ", makerUserId=" + makerUserId + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exVar1=" + exVar1
				+ ", exVar2=" + exVar2 + ", type=" + type + ", yearName=" + yearName + "]";
	}
	
	

}
