package com.ats.rusasoft.model;

public class FacultyBook {
	private int bookId; 
	private int	facultyId;
	private int	yearId;
	private String bookTitle;
	private String bookEdition;
	private String bookAuthor;
	private String bookCoauther1;
	private String bookCoauther2;
	private String bookCoauther3;
	private String bookPublisher;
	private String bookIsbn;
	private String bookPubYear;
	private int delStatus;
	private int isActive;
	private int makerUserId;
	private int exInt1;
	private String exVar1;
	
	
	public int getExInt1() {
		return exInt1;
	}
	public void setExInt1(int exInt1) {
		this.exInt1 = exInt1;
	}
	public String getExVar1() {
		return exVar1;
	}
	public void setExVar1(String exVar1) {
		this.exVar1 = exVar1;
	}
	private String makerEnterDatetime;
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public int getFacultyId() {
		return facultyId;
	}
	public void setFacultyId(int facultyId) {
		this.facultyId = facultyId;
	}
	public int getYearId() {
		return yearId;
	}
	public void setYearId(int yearId) {
		this.yearId = yearId;
	}
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	public String getBookEdition() {
		return bookEdition;
	}
	public void setBookEdition(String bookEdition) {
		this.bookEdition = bookEdition;
	}
	public String getBookAuthor() {
		return bookAuthor;
	}
	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}
	public String getBookCoauther1() {
		return bookCoauther1;
	}
	public void setBookCoauther1(String bookCoauther1) {
		this.bookCoauther1 = bookCoauther1;
	}
	public String getBookCoauther2() {
		return bookCoauther2;
	}
	public void setBookCoauther2(String bookCoauther2) {
		this.bookCoauther2 = bookCoauther2;
	}
	public String getBookCoauther3() {
		return bookCoauther3;
	}
	public void setBookCoauther3(String bookCoauther3) {
		this.bookCoauther3 = bookCoauther3;
	}
	public String getBookPublisher() {
		return bookPublisher;
	}
	public void setBookPublisher(String bookPublisher) {
		this.bookPublisher = bookPublisher;
	}
	public String getBookIsbn() {
		return bookIsbn;
	}
	public void setBookIsbn(String bookIsbn) {
		this.bookIsbn = bookIsbn;
	}
	public String getBookPubYear() {
		return bookPubYear;
	}
	public void setBookPubYear(String bookPubYear) {
		this.bookPubYear = bookPubYear;
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
	public String getMakerEnterDatetime() {
		return makerEnterDatetime;
	}
	public void setMakerEnterDatetime(String makerEnterDatetime) {
		this.makerEnterDatetime = makerEnterDatetime;
	}
	@Override
	public String toString() {
		return "FacultyBook [bookId=" + bookId + ", facultyId=" + facultyId + ", yearId=" + yearId + ", bookTitle="
				+ bookTitle + ", bookEdition=" + bookEdition + ", bookAuthor=" + bookAuthor + ", bookCoauther1="
				+ bookCoauther1 + ", bookCoauther2=" + bookCoauther2 + ", bookCoauther3=" + bookCoauther3
				+ ", bookPublisher=" + bookPublisher + ", bookIsbn=" + bookIsbn + ", bookPubYear=" + bookPubYear
				+ ", delStatus=" + delStatus + ", isActive=" + isActive + ", makerUserId=" + makerUserId
				+ ", exInt1=" + exInt1 + ", exVar1=" + exVar1 + ", makerEnterDatetime=" + makerEnterDatetime + "]";
	}

}
		
