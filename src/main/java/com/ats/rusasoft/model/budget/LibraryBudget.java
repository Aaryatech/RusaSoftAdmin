package com.ats.rusasoft.model.budget;


public class LibraryBudget {
	
	private int libBudgetId ;
	
	private int finYearId;
	private int acYearId;
	private int instituteId;
	
	private String libBudgetTitle;
	
	private int budgetAllocated ;
	private int budgetUtilized; 
	
	private int addBy; 
	private String addDatetime ;
	
	private int delStatus;
	private int isActive;
 	
 	private int exInt1;
 	private int exInt2;
	private String exVar1;
	private String exVar2;
	
	
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
	public int getLibBudgetId() {
		return libBudgetId;
	}
	public void setLibBudgetId(int libBudgetId) {
		this.libBudgetId = libBudgetId;
	}
	public String getLibBudgetTitle() {
		return libBudgetTitle;
	}
	public void setLibBudgetTitle(String libBudgetTitle) {
		this.libBudgetTitle = libBudgetTitle;
	}
	@Override
	public String toString() {
		return "LibraryBudget [libBudgetId=" + libBudgetId + ", finYearId=" + finYearId + ", acYearId=" + acYearId
				+ ", instituteId=" + instituteId + ", libBudgetTitle=" + libBudgetTitle + ", budgetAllocated="
				+ budgetAllocated + ", budgetUtilized=" + budgetUtilized + ", addBy=" + addBy + ", addDatetime="
				+ addDatetime + ", delStatus=" + delStatus + ", isActive=" + isActive + ", exInt1=" + exInt1
				+ ", exInt2=" + exInt2 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + "]";
	}

}
