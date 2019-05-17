package com.ats.rusasoft.master.model.prodetail;

public class DifrentlyAbledStudList {

	private int difAbleStudId;
	private String nameOfStud; 
	private int gender;
	private String udidCardNo; 
	private String typeOfDisability; 
	private int percntOfDisability;
	private String programName;
	private String nameOfProgram;
	private String yearOfEnrollement;
	public int getDifAbleStudId() {
		return difAbleStudId;
	}
	public void setDifAbleStudId(int difAbleStudId) {
		this.difAbleStudId = difAbleStudId;
	}
	public String getNameOfStud() {
		return nameOfStud;
	}
	public void setNameOfStud(String nameOfStud) {
		this.nameOfStud = nameOfStud;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public String getUdidCardNo() {
		return udidCardNo;
	}
	public void setUdidCardNo(String udidCardNo) {
		this.udidCardNo = udidCardNo;
	}
	public String getTypeOfDisability() {
		return typeOfDisability;
	}
	public void setTypeOfDisability(String typeOfDisability) {
		this.typeOfDisability = typeOfDisability;
	}
	public int getPercntOfDisability() {
		return percntOfDisability;
	}
	public void setPercntOfDisability(int percntOfDisability) {
		this.percntOfDisability = percntOfDisability;
	}
	
	public String getProgramName() {
		return programName;
	}
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	public String getNameOfProgram() {
		return nameOfProgram;
	}
	public void setNameOfProgram(String nameOfProgram) {
		this.nameOfProgram = nameOfProgram;
	}
	public String getYearOfEnrollement() {
		return yearOfEnrollement;
	}
	public void setYearOfEnrollement(String yearOfEnrollement) {
		this.yearOfEnrollement = yearOfEnrollement;
	}
	@Override
	public String toString() {
		return "DifrentlyAbledStudList [difAbleStudId=" + difAbleStudId + ", nameOfStud=" + nameOfStud + ", gender="
				+ gender + ", udidCardNo=" + udidCardNo + ", typeOfDisability=" + typeOfDisability
				+ ", percntOfDisability=" + percntOfDisability + ", programName=" + programName + ", nameOfProgram="
				+ nameOfProgram + ", yearOfEnrollement=" + yearOfEnrollement + "]";
	}
	
}
