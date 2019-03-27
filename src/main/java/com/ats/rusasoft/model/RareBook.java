package com.ats.rusasoft.model;

public class RareBook {

	private int rareBookInfoId;
	private int instituteId;
	private int yearId;  //extra
	private int userId;
	private String rareBookname;
	private String publisher;
	private int bookCopies;;
	private int costOfBook;
	private String publicationYear;
	private int delStatus;
	private int exInt1;
	private String exVar1;
	public int getRareBookInfoId() {
		return rareBookInfoId;
	}
	public void setRareBookInfoId(int rareBookInfoId) {
		this.rareBookInfoId = rareBookInfoId;
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
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getRareBookname() {
		return rareBookname;
	}
	public void setRareBookname(String rareBookname) {
		this.rareBookname = rareBookname;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public int getBookCopies() {
		return bookCopies;
	}
	public void setBookCopies(int bookCopies) {
		this.bookCopies = bookCopies;
	}
	public int getCostOfBook() {
		return costOfBook;
	}
	public void setCostOfBook(int costOfBook) {
		this.costOfBook = costOfBook;
	}
	public String getPublicationYear() {
		return publicationYear;
	}
	public void setPublicationYear(String publicationYear) {
		this.publicationYear = publicationYear;
	}
	public int getDelStatus() {
		return delStatus;
	}
	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}
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
	@Override
	public String toString() {
		return "RareBook [rareBookInfoId=" + rareBookInfoId + ", instituteId=" + instituteId + ", yearId=" + yearId
				+ ", userId=" + userId + ", rareBookname=" + rareBookname + ", publisher=" + publisher + ", bookCopies="
				+ bookCopies + ", costOfBook=" + costOfBook + ", publicationYear=" + publicationYear + ", delStatus="
				+ delStatus + ", exInt1=" + exInt1 + ", exVar1=" + exVar1 + "]";
	}
	
	
}
