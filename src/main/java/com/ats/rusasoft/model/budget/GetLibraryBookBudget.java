package com.ats.rusasoft.model.budget;

public class GetLibraryBookBudget {

	private int libraryBookBudgetId;

	private int finYearId;
	private int acYearId;
	private int instituteId;
	private int exInt1;
	private String exVar1;
	private String exVar2;
	private int expenditureOnBookPurchase;
	private int expenditureOnJournalsPurchase;
	private int expenditureOnEjournalsPurchase;
	private int expenditureOnEresourcesPurchase;

	private String academicYear;
	private String finYear;
	
	
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
	public String getAcademicYear() {
		return academicYear;
	}
	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
	}
	public String getFinYear() {
		return finYear;
	}
	public void setFinYear(String finYear) {
		this.finYear = finYear;
	}
	public String getExVar2() {
		return exVar2;
	}
	public void setExVar2(String exVar2) {
		this.exVar2 = exVar2;
	}
	@Override
	public String toString() {
		return "GetLibraryBookBudget [libraryBookBudgetId=" + libraryBookBudgetId + ", finYearId=" + finYearId
				+ ", acYearId=" + acYearId + ", instituteId=" + instituteId + ", exInt1=" + exInt1 + ", exVar1="
				+ exVar1 + ", exVar2=" + exVar2 + ", expenditureOnBookPurchase=" + expenditureOnBookPurchase
				+ ", expenditureOnJournalsPurchase=" + expenditureOnJournalsPurchase
				+ ", expenditureOnEjournalsPurchase=" + expenditureOnEjournalsPurchase
				+ ", expenditureOnEresourcesPurchase=" + expenditureOnEresourcesPurchase + ", academicYear="
				+ academicYear + ", finYear=" + finYear + "]";
	}

	
}
