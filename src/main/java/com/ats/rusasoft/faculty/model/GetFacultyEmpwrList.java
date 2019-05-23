package com.ats.rusasoft.faculty.model;

public class GetFacultyEmpwrList {
	private int facultyEmpwrmntId;
	private String nameOfAcitvity;
	private String title;
	private String amt_recvd_from;
	private String fromDate;
	private String toDate;
	private String amount;
	private String facultyName;
	private String department;
	
	//private int yearId;
	public int getFacultyEmpwrmntId() {
		return facultyEmpwrmntId;
	}
	public void setFacultyEmpwrmntId(int facultyEmpwrmntId) {
		this.facultyEmpwrmntId = facultyEmpwrmntId;
	}
	public String getNameOfAcitvity() {
		return nameOfAcitvity;
	}
	public void setNameOfAcitvity(String nameOfAcitvity) {
		this.nameOfAcitvity = nameOfAcitvity;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getAmt_recvd_from() {
		return amt_recvd_from;
	}
	public void setAmt_recvd_from(String amt_recvd_from) {
		this.amt_recvd_from = amt_recvd_from;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	public String getFacultyName() {
		return facultyName;
	}
	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	@Override
	public String toString() {
		return "GetFacultyEmpwrList [facultyEmpwrmntId=" + facultyEmpwrmntId + ", nameOfAcitvity=" + nameOfAcitvity
				+ ", title=" + title + ", amt_recvd_from=" + amt_recvd_from + ", fromDate=" + fromDate + ", toDate="
				+ toDate + ", amount=" + amount + ", facultyName=" + facultyName + ", department=" + department + "]";
	}
	
	
}
