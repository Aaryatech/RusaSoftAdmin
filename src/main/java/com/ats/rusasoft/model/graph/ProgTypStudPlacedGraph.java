package com.ats.rusasoft.model.graph;

public class ProgTypStudPlacedGraph {
	
	private int programId;
	private String progTypeName;
	private String programName;
	private int noStudentPlaced;
	private int noStudPass;
	public int getProgramId() {
		return programId;
	}
	public void setProgramId(int programId) {
		this.programId = programId;
	}
	public String getProgTypeName() {
		return progTypeName;
	}
	public void setProgTypeName(String progTypeName) {
		this.progTypeName = progTypeName;
	}
	public String getProgramName() {
		return programName;
	}
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	public int getNoStudentPlaced() {
		return noStudentPlaced;
	}
	public void setNoStudentPlaced(int noStudentPlaced) {
		this.noStudentPlaced = noStudentPlaced;
	}
	public int getNoStudPass() {
		return noStudPass;
	}
	public void setNoStudPass(int noStudPass) {
		this.noStudPass = noStudPass;
	}
	@Override
	public String toString() {
		return "ProgTypStudPlacedGraph [programId=" + programId + ", progTypeName=" + progTypeName + ", programName="
				+ programName + ", noStudentPlaced=" + noStudentPlaced + ", noStudPass=" + noStudPass + "]";
	}
	
	

}
