package com.ats.rusasoft.model.budget;

public class GetWasteMngtBudget {
	
	private int wasteMngtBudgetId ;
	
	private int finYearId;
	private int acYearId;
	private int instituteId;
	
	private String wasteMngtBudgetTitle;
	
	private int budgetAllocated ;
	private int budgetUtilized; 
	
	private String academicYear;
	private String finYear ;
	
	public int getWasteMngtBudgetId() {
		return wasteMngtBudgetId;
	}
	public void setWasteMngtBudgetId(int wasteMngtBudgetId) {
		this.wasteMngtBudgetId = wasteMngtBudgetId;
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
	public String getWasteMngtBudgetTitle() {
		return wasteMngtBudgetTitle;
	}
	public void setWasteMngtBudgetTitle(String wasteMngtBudgetTitle) {
		this.wasteMngtBudgetTitle = wasteMngtBudgetTitle;
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
		return "GetWasteMngtBudget [wasteMngtBudgetId=" + wasteMngtBudgetId + ", finYearId=" + finYearId + ", acYearId="
				+ acYearId + ", instituteId=" + instituteId + ", wasteMngtBudgetTitle=" + wasteMngtBudgetTitle
				+ ", budgetAllocated=" + budgetAllocated + ", budgetUtilized=" + budgetUtilized + ", academicYear="
				+ academicYear + ", finYear=" + finYear + "]";
	}

}
