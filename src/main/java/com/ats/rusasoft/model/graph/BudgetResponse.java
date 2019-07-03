package com.ats.rusasoft.model.graph;

import java.util.List;
 

public class BudgetResponse {
	
	
	List<AllBudgetGraph> libRes;
 	
 	List<AllBudgetGraph> bookRes;
	
 	List<AllBudgetGraph> infraRes;
	
 	List<AllBudgetGraph> academicRes;

	public List<AllBudgetGraph> getLibRes() {
		return libRes;
	}

	public void setLibRes(List<AllBudgetGraph> libRes) {
		this.libRes = libRes;
	}

	public List<AllBudgetGraph> getBookRes() {
		return bookRes;
	}

	public void setBookRes(List<AllBudgetGraph> bookRes) {
		this.bookRes = bookRes;
	}

	public List<AllBudgetGraph> getInfraRes() {
		return infraRes;
	}

	public void setInfraRes(List<AllBudgetGraph> infraRes) {
		this.infraRes = infraRes;
	}

	public List<AllBudgetGraph> getAcademicRes() {
		return academicRes;
	}

	public void setAcademicRes(List<AllBudgetGraph> academicRes) {
		this.academicRes = academicRes;
	}

	@Override
	public String toString() {
		return "BudgetResponse [libRes=" + libRes + ", bookRes=" + bookRes + ", infraRes=" + infraRes + ", academicRes="
				+ academicRes + "]";
	}
 	
 	

}
