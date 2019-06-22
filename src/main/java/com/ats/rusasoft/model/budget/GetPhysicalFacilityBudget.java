package com.ats.rusasoft.model.budget;

public class GetPhysicalFacilityBudget {

	private int physicalFacilityBudgetId;

	private int finYearId;
	private int acYearId;
	private int instituteId;
	private int exInt1;
	private String physicalFacilityBudgetTitle;

	private int budgetAllocated;
	private int budgetUtilized;

	private String academicYear;
	private String exVar1;
	private String exVar2;
	private String finYear;

	
	public int getExInt1() {
		return exInt1;
	}

	public void setExInt1(int exInt1) {
		this.exInt1 = exInt1;
	}

	public String getExVar2() {
		return exVar2;
	}

	public void setExVar2(String exVar2) {
		this.exVar2 = exVar2;
	}

	public String getExVar1() {
		return exVar1;
	}

	public void setExVar1(String exVar1) {
		this.exVar1 = exVar1;
	}

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
				+ finYearId + ", acYearId=" + acYearId + ", instituteId=" + instituteId + ", exInt1=" + exInt1
				+ ", physicalFacilityBudgetTitle=" + physicalFacilityBudgetTitle + ", budgetAllocated="
				+ budgetAllocated + ", budgetUtilized=" + budgetUtilized + ", academicYear=" + academicYear
				+ ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + ", finYear=" + finYear + "]";
	}

}
