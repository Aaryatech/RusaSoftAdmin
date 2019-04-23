package com.ats.rusasoft.model;

public class LibBookPurchase {
	
	private int bookPurchaseId;
	private int instituteId;
	private int academicYrid;
	private int noOfBooks;
	private int costOfBooks;
	private int noOfJournal;
	private int costOfJournal;
	private int noOfEjournal;
	private int costOfEjournal;
	private int delStatus;
	private int userId;
	private String exVar1;
	private int exInt1;
	
	public int getBookPurchaseId() {
		return bookPurchaseId;
	}
	public void setBookPurchaseId(int bookPurchaseId) {
		this.bookPurchaseId = bookPurchaseId;
	}
	public int getInstituteId() {
		return instituteId;
	}
	public void setInstituteId(int instituteId) {
		this.instituteId = instituteId;
	}
	
	public int getAcademicYrid() {
		return academicYrid;
	}
	public void setAcademicYrid(int academicYrid) {
		this.academicYrid = academicYrid;
	}
	public int getNoOfBooks() {
		return noOfBooks;
	}
	public void setNoOfBooks(int noOfBooks) {
		this.noOfBooks = noOfBooks;
	}
	public int getCostOfBooks() {
		return costOfBooks;
	}
	public void setCostOfBooks(int costOfBooks) {
		this.costOfBooks = costOfBooks;
	}
	public int getNoOfJournal() {
		return noOfJournal;
	}
	public void setNoOfJournal(int noOfJournal) {
		this.noOfJournal = noOfJournal;
	}
	public int getCostOfJournal() {
		return costOfJournal;
	}
	public void setCostOfJournal(int costOfJournal) {
		this.costOfJournal = costOfJournal;
	}
	public int getNoOfEjournal() {
		return noOfEjournal;
	}
	public void setNoOfEjournal(int noOfEjournal) {
		this.noOfEjournal = noOfEjournal;
	}
	public int getCostOfEjournal() {
		return costOfEjournal;
	}
	public void setCostOfEjournal(int costOfEjournal) {
		this.costOfEjournal = costOfEjournal;
	}
	public int getDelStatus() {
		return delStatus;
	}
	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getExVar1() {
		return exVar1;
	}
	public void setExVar1(String exVar1) {
		this.exVar1 = exVar1;
	}
	public int getExInt1() {
		return exInt1;
	}
	public void setExInt1(int exInt1) {
		this.exInt1 = exInt1;
	}
	@Override
	public String toString() {
		return "LibBookPurchase [bookPurchaseId=" + bookPurchaseId + ", instituteId=" + instituteId + ", academicYrid="
				+ academicYrid + ", noOfBooks=" + noOfBooks + ", costOfBooks=" + costOfBooks + ", noOfJournal="
				+ noOfJournal + ", costOfJournal=" + costOfJournal + ", noOfEjournal=" + noOfEjournal
				+ ", costOfEjournal=" + costOfEjournal + ", delStatus=" + delStatus + ", userId=" + userId + ", exVar1="
				+ exVar1 + ", exInt1=" + exInt1 + "]";
	}
	
}
