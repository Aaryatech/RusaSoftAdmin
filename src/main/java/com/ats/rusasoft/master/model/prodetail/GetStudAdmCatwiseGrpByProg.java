package com.ats.rusasoft.master.model.prodetail;


public class GetStudAdmCatwiseGrpByProg {
	
	
	private int programId;
	
	private int maleStudent;
	private int femaleStudent;
	private int transStudent;
	private int totStudent;
	private int exInt1;
	
	private String progType;
	private String progName;
	
	
	public int getExInt1() {
		return exInt1;
	}
	public void setExInt1(int exInt1) {
		this.exInt1 = exInt1;
	}
	public int getProgramId() {
		return programId;
	}
	public void setProgramId(int programId) {
		this.programId = programId;
	}
	public int getMaleStudent() {
		return maleStudent;
	}
	public void setMaleStudent(int maleStudent) {
		this.maleStudent = maleStudent;
	}
	public int getFemaleStudent() {
		return femaleStudent;
	}
	public void setFemaleStudent(int femaleStudent) {
		this.femaleStudent = femaleStudent;
	}
	public int getTransStudent() {
		return transStudent;
	}
	public void setTransStudent(int transStudent) {
		this.transStudent = transStudent;
	}
	
	public int getTotStudent() {
		return totStudent;
	}
	public void setTotStudent(int totStudent) {
		this.totStudent = totStudent;
	}
	public String getProgType() {
		return progType;
	}
	public void setProgType(String progType) {
		this.progType = progType;
	}
	public String getProgName() {
		return progName;
	}
	public void setProgName(String progName) {
		this.progName = progName;
	}
	@Override
	public String toString() {
		return "GetStudAdmCatwiseGrpByProg [programId=" + programId + ", maleStudent=" + maleStudent
				+ ", femaleStudent=" + femaleStudent + ", transStudent=" + transStudent + ", totStudent=" + totStudent
				+ ", exInt1=" + exInt1 + ", progType=" + progType + ", progName=" + progName + ", getExInt1()="
				+ getExInt1() + ", getProgramId()=" + getProgramId() + ", getMaleStudent()=" + getMaleStudent()
				+ ", getFemaleStudent()=" + getFemaleStudent() + ", getTransStudent()=" + getTransStudent()
				+ ", getTotStudent()=" + getTotStudent() + ", getProgType()=" + getProgType() + ", getProgName()="
				+ getProgName() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	

	
}
