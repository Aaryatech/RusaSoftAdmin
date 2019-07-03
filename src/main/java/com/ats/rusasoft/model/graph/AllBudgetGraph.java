package com.ats.rusasoft.model.graph;

public class AllBudgetGraph {
	
	private int finYearId;
	
	private int budgetAllocated;
	
	private int budgetUtilized;
	
	private String finYear;

	public int getFinYearId() {
		return finYearId;
	}

	public void setFinYearId(int finYearId) {
		this.finYearId = finYearId;
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

	public String getFinYear() {
		return finYear;
	}

	public void setFinYear(String finYear) {
		this.finYear = finYear;
	}

	@Override
	public String toString() {
		return "AllBudgetGraph [finYearId=" + finYearId + ", budgetAllocated=" + budgetAllocated + ", budgetUtilized="
				+ budgetUtilized + ", finYear=" + finYear + "]";
	}
	
	

}
