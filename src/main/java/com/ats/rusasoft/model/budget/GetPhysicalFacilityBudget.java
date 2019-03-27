package com.ats.rusasoft.model.budget;

public class GetPhysicalFacilityBudget {

	private int physicalFacilityBudgetId;

	private int finYearId;
	private int acYearId;
	private int instituteId;

	private String physicalFacilityBudgetTitle;

	private int budgetAllocated;
	private int budgetUtilized;

	private String academicYear;
	private String finYear;

	public int getPhysicalFacilityBudgetId() {
		return physicalFacilityBudgetId;
	}

	public void setPhysicalFacilityBudgetId(int physicalFacilityBudgetId) {
		this.physicalFacilityBudgetId = physicalFacilityBudgetId;
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

	public String getPhysicalFacilityBudgetTitle() {
		return physicalFacilityBudgetTitle;
	}

	public void setPhysicalFacilityBudgetTitle(String physicalFacilityBudgetTitle) {
		this.physicalFacilityBudgetTitle = physicalFacilityBudgetTitle;
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
		return "GetPhysicalFacilityBudget [physicalFacilityBudgetId=" + physicalFacilityBudgetId + ", finYearId="
				+ finYearId + ", acYearId=" + acYearId + ", instituteId=" + instituteId
				+ ", physicalFacilityBudgetTitle=" + physicalFacilityBudgetTitle + ", budgetAllocated="
				+ budgetAllocated + ", budgetUtilized=" + budgetUtilized + ", academicYear=" + academicYear
				+ ", finYear=" + finYear + "]";
	}

}
