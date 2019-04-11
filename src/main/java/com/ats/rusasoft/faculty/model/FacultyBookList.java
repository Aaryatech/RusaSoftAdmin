package com.ats.rusasoft.faculty.model;

public class FacultyBookList {
	
	
	private int bookId; 
	private int	facultyId;
	private int	yearId;
	private String deptName;
	private String facultyFirstName;
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
	private String makerEnterDatetime;
	
	public String getMakerEnterDatetime() {
		return makerEnterDatetime;
	}
	public void setMakerEnterDatetime(String makerEnterDatetime) {
		this.makerEnterDatetime = makerEnterDatetime;
	}
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
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getFacultyFirstName() {
		return facultyFirstName;
	}
	public void setFacultyFirstName(String facultyFirstName) {
		this.facultyFirstName = facultyFirstName;
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
	@Override
	public String toString() {
		return "FacultyBookList [bookId=" + bookId + ", facultyId=" + facultyId + ", yearId=" + yearId + ", deptName="
				+ deptName + ", facultyFirstName=" + facultyFirstName + ", bookTitle=" + bookTitle + ", bookEdition="
				+ bookEdition + ", bookAuthor=" + bookAuthor + ", bookCoauther1=" + bookCoauther1 + ", bookCoauther2="
				+ bookCoauther2 + ", bookCoauther3=" + bookCoauther3 + ", bookPublisher=" + bookPublisher
				+ ", bookIsbn=" + bookIsbn + ", bookPubYear=" + bookPubYear + ", delStatus=" + delStatus + ", isActive="
				+ isActive + ", makerUserId=" + makerUserId + ", makerEnterDatetime=" + makerEnterDatetime + "]";
	}
	
	
}
