package com.ats.rusasoft.model.graph;

public class TeacherStudUsingLibrary {
	
	private int acYearId;
	
	private String acYear;
	
	private int libInfoId;
	
	private int avgTeacher;
	
	private int avgStudent;

	public int getAcYearId() {
		return acYearId;
	}

	public void setAcYearId(int acYearId) {
		this.acYearId = acYearId;
	}

	public int getLibInfoId() {
		return libInfoId;
	}

	public void setLibInfoId(int libInfoId) {
		this.libInfoId = libInfoId;
	}

	public int getAvgTeacher() {
		return avgTeacher;
	}

	public void setAvgTeacher(int avgTeacher) {
		this.avgTeacher = avgTeacher;
	}

	public int getAvgStudent() {
		return avgStudent;
	}

	public void setAvgStudent(int avgStudent) {
		this.avgStudent = avgStudent;
	}

	public String getAcYear() {
		return acYear;
	}

	public void setAcYear(String acYear) {
		this.acYear = acYear;
	}

	@Override
	public String toString() {
		return "TeacherStudUsingLibrary [acYearId=" + acYearId + ", acYear=" + acYear + ", libInfoId=" + libInfoId
				+ ", avgTeacher=" + avgTeacher + ", avgStudent=" + avgStudent + "]";
	}
	
	

}
