package com.ats.rusasoft.model.graph;

public class BudgetGraphDash {
	
	private int allocAmt;
	private int utilAmt;
	private String budgetTitle;
	public int getAllocAmt() {
		return allocAmt;
	}
	public void setAllocAmt(int allocAmt) {
		this.allocAmt = allocAmt;
	}
	public int getUtilAmt() {
		return utilAmt;
	}
	public void setUtilAmt(int utilAmt) {
		this.utilAmt = utilAmt;
	}
	public String getBudgetTitle() {
		return budgetTitle;
	}
	public void setBudgetTitle(String budgetTitle) {
		this.budgetTitle = budgetTitle;
	}
	@Override
	public String toString() {
		return "BudgetGraphDash [allocAmt=" + allocAmt + ", utilAmt=" + utilAmt + ", budgetTitle=" + budgetTitle + "]";
	}
	
	

}
