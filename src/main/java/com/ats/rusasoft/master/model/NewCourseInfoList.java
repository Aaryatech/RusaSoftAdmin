package com.ats.rusasoft.master.model;

public class NewCourseInfoList {
	private int courseId;
	private String progName;	
	private String progType;
	private String applicableYear;
	private String courseName;
	private int courseCode;
	private String introduceFrom;
	private String document;
	
	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getProgName() {
		return progName;
	}

	public void setProgName(String progName) {
		this.progName = progName;
	}

	public String getProgType() {
		return progType;
	}

	public void setProgType(String progType) {
		this.progType = progType;
	}

	public String getApplicableYear() {
		return applicableYear;
	}

	public void setApplicableYear(String applicableYear) {
		this.applicableYear = applicableYear;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(int courseCode) {
		this.courseCode = courseCode;
	}

	public String getIntroduceFrom() {
		return introduceFrom;
	}

	public void setIntroduceFrom(String introduceFrom) {
		this.introduceFrom = introduceFrom;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	@Override
	public String toString() {
		return "NewCourseInfoList [courseId=" + courseId + ", progName=" + progName + ", progType=" + progType
				+ ", applicableYear=" + applicableYear + ", courseName=" + courseName + ", courseCode=" + courseCode
				+ ", introduceFrom=" + introduceFrom + ", document=" + document + "]";
	}
	
	
}
