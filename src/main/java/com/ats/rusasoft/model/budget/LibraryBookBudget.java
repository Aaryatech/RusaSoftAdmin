package com.ats.rusasoft.model.budget;

public class LibraryBookBudget {
private int libraryBookBudgetId ;
	
	private int finYearId;
	private int acYearId;
	private int instituteId;
	
	private int expenditureOnBookPurchase;
	
	private int expenditureOnJournalsPurchase;
	private int expenditureOnEjournalsPurchase;
	private int expenditureOnEresourcesPurchase;
	
	
	private int addBy; 
	private String addDatetime ;
	
	private int delStatus;
	private int isActive;
 	
 	private int exInt1;
 	private int exInt2;
	private String exVar1;
	private String exVar2;
	
	public int getLibraryBookBudgetId() {
		return libraryBookBudgetId;
	}
	public void setLibraryBookBudgetId(int libraryBookBudgetId) {
		this.libraryBookBudgetId = libraryBookBudgetId;
	}
	public int getFinYearId() {
		return finYearId;
	}
	public void setFinYearId(int finYearId) {
		this.finYearId = finYearId;
	}
	public int getAcYearId() {
		return acYearId;
	}
	public void setAcYearId(int acYearId) {
		this.acYearId = acYearId;
	}
	public int getInstituteId() {
		return instituteId;
	}
	public void setInstituteId(int instituteId) {
		this.instituteId = instituteId;
	}
	public int getExpenditureOnBookPurchase() {
		return expenditureOnBookPurchase;
	}
	public void setExpenditureOnBookPurchase(int expenditureOnBookPurchase) {
		this.expenditureOnBookPurchase = expenditureOnBookPurchase;
	}
	public int getExpenditureOnJournalsPurchase() {
		return expenditureOnJournalsPurchase;
	}
	public void setExpenditureOnJournalsPurchase(int expenditureOnJournalsPurchase) {
		this.expenditureOnJournalsPurchase = expenditureOnJournalsPurchase;
	}
	public int getExpenditureOnEjournalsPurchase() {
		return expenditureOnEjournalsPurchase;
	}
	public void setExpenditureOnEjournalsPurchase(int expenditureOnEjournalsPurchase) {
		this.expenditureOnEjournalsPurchase = expenditureOnEjournalsPurchase;
	}
	public int getExpenditureOnEresourcesPurchase() {
		return expenditureOnEresourcesPurchase;
	}
	public void setExpenditureOnEresourcesPurchase(int expenditureOnEresourcesPurchase) {
		this.expenditureOnEresourcesPurchase = expenditureOnEresourcesPurchase;
	}
	public int getAddBy() {
		return addBy;
	}
	public void setAddBy(int addBy) {
		this.addBy = addBy;
	}
	public String getAddDatetime() {
		return addDatetime;
	}
	public void setAddDatetime(String addDatetime) {
		this.addDatetime = addDatetime;
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
		return "LibraryBookBudget [libraryBookBudgetId=" + libraryBookBudgetId + ", finYearId=" + finYearId
				+ ", acYearId=" + acYearId + ", instituteId=" + instituteId + ", expenditureOnBookPurchase="
				+ expenditureOnBookPurchase + ", expenditureOnJournalsPurchase=" + expenditureOnJournalsPurchase
				+ ", expenditureOnEjournalsPurchase=" + expenditureOnEjournalsPurchase
				+ ", expenditureOnEresourcesPurchase=" + expenditureOnEresourcesPurchase + ", addBy=" + addBy
				+ ", addDatetime=" + addDatetime + ", delStatus=" + delStatus + ", isActive=" + isActive + ", exInt1="
				+ exInt1 + ", exInt2=" + exInt2 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + "]";
	}

}
