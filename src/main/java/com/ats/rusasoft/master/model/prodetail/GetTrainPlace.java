package com.ats.rusasoft.master.model.prodetail;

public class GetTrainPlace {

	private int placementId ;
	private String empyrName;
	private String programName;
	private String progTypeName;
	private String exVar1;
	private int noStudentPlaced ;
	
	private String empyrAdd ;
	private String	contactDetail ;
	private String  pakageOfferd ;
	public int getPlacementId() {
		return placementId;
	}
	public void setPlacementId(int placementId) {
		this.placementId = placementId;
	}
	public String getEmpyrName() {
		return empyrName;
	}
	
	public String getExVar1() {
		return exVar1;
	}
	public void setExVar1(String exVar1) {
		this.exVar1 = exVar1;
	}
	public void setEmpyrName(String empyrName) {
		this.empyrName = empyrName;
	}
	public String getProgramName() {
		return programName;
	}
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	public String getProgTypeName() {
		return progTypeName;
	}
	public void setProgTypeName(String progTypeName) {
		this.progTypeName = progTypeName;
	}
	public int getNoStudentPlaced() {
		return noStudentPlaced;
	}
	public void setNoStudentPlaced(int noStudentPlaced) {
		this.noStudentPlaced = noStudentPlaced;
	}
	public String getEmpyrAdd() {
		return empyrAdd;
	}
	public void setEmpyrAdd(String empyrAdd) {
		this.empyrAdd = empyrAdd;
	}
	public String getContactDetail() {
		return contactDetail;
	}
	public void setContactDetail(String contactDetail) {
		this.contactDetail = contactDetail;
	}
	public String getPakageOfferd() {
		return pakageOfferd;
	}
	public void setPakageOfferd(String pakageOfferd) {
		this.pakageOfferd = pakageOfferd;
	}
	@Override
	public String toString() {
		return "GetTrainPlace [placementId=" + placementId + ", empyrName=" + empyrName + ", programName=" + programName
				+ ", progTypeName=" + progTypeName + ", exVar1=" + exVar1 + ", noStudentPlaced=" + noStudentPlaced
				+ ", empyrAdd=" + empyrAdd + ", contactDetail=" + contactDetail + ", pakageOfferd=" + pakageOfferd
				+ "]";
	}
	
	
}
