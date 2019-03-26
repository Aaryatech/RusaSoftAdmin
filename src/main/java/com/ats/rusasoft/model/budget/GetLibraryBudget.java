package com.ats.rusasoft.model.budget;

public class GetLibraryBudget {

	private int libBudgetId ;
	
	private int finYearId;
	private int acYearId;
	private int instituteId;
	
	private String libBudgetTitle;
	
	private int budgetAllocated ;
	private int budgetUtilized; 
	
	private String academicYear;
	private String finYear ;
	
	public int getLibBudgetId() {
		return libBudgetId;
	}
	public void setLibBudgetId(int libBudgetId) {
		this.libBudgetId = libBudgetId;
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
	public String getLibBudgetTitle() {
		return libBudgetTitle;
	}
	public void setLibBudgetTitle(String libBudgetTitle) {
		this.libBudgetTitle = libBudgetTitle;
	}
	public int getBudgetAllocated() {
		return budgetAllocated;
	}
	public void setBudgetAllocated(int budgetAllocated) {
		this.budgetAllocated = budgetAllocated;
	}
	public int getBudgetUtilized() {
		return budgetUtilized;
	}
	public void setBudgetUtilized(int budgetUtilized) {
		this.budgetUtilized = budgetUtilized;
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
	
	@Override
	public String toString() {
		return "GetLibraryBudget [libBudgetId=" + libBudgetId + ", finYearId=" + finYearId + ", acYearId=" + acYearId
				+ ", instituteId=" + instituteId + ", libBudgetTitle=" + libBudgetTitle + ", budgetAllocated="
				+ budgetAllocated + ", budgetUtilized=" + budgetUtilized + ", academicYear=" + academicYear
				+ ", finYear=" + finYear + "]";
	}

}
