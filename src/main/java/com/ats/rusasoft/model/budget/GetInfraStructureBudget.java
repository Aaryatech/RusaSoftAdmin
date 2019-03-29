package com.ats.rusasoft.model.budget;

public class GetInfraStructureBudget {

	
	private int infraBudgetId ;
	
	private int finYearId;
	private int acYearId;
	private int instituteId;
	
	private String infraBudgetTitle ;
	
	private int budgetAllocated ;
	private int budgetUtilized; 
	
	private String academicYear;
	private String finYear ;
	
	
	
	public int getInfraBudgetId() {
		return infraBudgetId;
	}
	public void setInfraBudgetId(int infraBudgetId) {
		this.infraBudgetId = infraBudgetId;
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
	public String getInfraBudgetTitle() {
		return infraBudgetTitle;
	}
	public void setInfraBudgetTitle(String infraBudgetTitle) {
		this.infraBudgetTitle = infraBudgetTitle;
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
		return "GetInfraStructureBudget [infraBudgetId=" + infraBudgetId + ", finYearId=" + finYearId + ", acYearId="
				+ acYearId + ", instituteId=" + instituteId + ", infraBudgetTitle=" + infraBudgetTitle
				+ ", budgetAllocated=" + budgetAllocated + ", budgetUtilized=" + budgetUtilized + ", academicYear="
				+ academicYear + ", finYear=" + finYear + "]";
	}
	
	
}