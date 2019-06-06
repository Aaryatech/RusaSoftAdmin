package com.ats.rusasoft.model.budget;

public class GetAcademicBudget {

	private int academicBudgetId;

	private int finYearId;
	private int acYearId;
	private int instituteId;
	private int exInt1;

	private String academicBudgetTitle;
	private String exVar1;

	private int budgetAllocated;
	private int budgetUtilized;

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

	public int getAcademicBudgetId() {
		return academicBudgetId;
	}

	public void setAcademicBudgetId(int academicBudgetId) {
		this.academicBudgetId = academicBudgetId;
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

	public String getAcademicBudgetTitle() {
		return academicBudgetTitle;
	}

	public void setAcademicBudgetTitle(String academicBudgetTitle) {
		this.academicBudgetTitle = academicBudgetTitle;
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
		return "GetAcademicBudget [academicBudgetId=" + academicBudgetId + ", finYearId=" + finYearId + ", acYearId="
				+ acYearId + ", instituteId=" + instituteId + ", exInt1=" + exInt1 + ", academicBudgetTitle="
				+ academicBudgetTitle + ", exVar1=" + exVar1 + ", budgetAllocated=" + budgetAllocated
				+ ", budgetUtilized=" + budgetUtilized + ", academicYear=" + academicYear + ", finYear=" + finYear
				+ "]";
	}

}
