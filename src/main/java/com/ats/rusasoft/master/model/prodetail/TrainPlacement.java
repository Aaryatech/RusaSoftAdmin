package com.ats.rusasoft.master.model.prodetail;

public class TrainPlacement {
	
	private int placementId ;
	
	private int instituteId;

	private int yearId;
	
	private String empyrName;
	
	private int programType;
	
	private String programName;
	
	private int noStudentPlaced ;
	
	private String supportAgencyName;
	
	private String empyrAdd ;
	private String	contactDetail ;
	private String  pakageOfferd ;
	
	private int delStatus;
	private int isActive;
	
	private int makerUserId;
	private String makerDatetime;
	private int exInt1;
	private int exInt2;
	private String exVar1;
	private String exVar2;
	public int getPlacementId() {
		return placementId;
	}
	public void setPlacementId(int placementId) {
		this.placementId = placementId;
	}
	public int getInstituteId() {
		return instituteId;
	}
	public void setInstituteId(int instituteId) {
		this.instituteId = instituteId;
	}
	public int getYearId() {
		return yearId;
	}
	public void setYearId(int yearId) {
		this.yearId = yearId;
	}
	public String getEmpyrName() {
		return empyrName;
	}
	public void setEmpyrName(String empyrName) {
		this.empyrName = empyrName;
	}
	public int getProgramType() {
		return programType;
	}
	public void setProgramType(int programType) {
		this.programType = programType;
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
	public String getSupportAgencyName() {
		return supportAgencyName;
	}
	public void setSupportAgencyName(String supportAgencyName) {
		this.supportAgencyName = supportAgencyName;
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
	public int getMakerUserId() {
		return makerUserId;
	}
	public void setMakerUserId(int makerUserId) {
		this.makerUserId = makerUserId;
	}
	public String getMakerDatetime() {
		return makerDatetime;
	}
	public void setMakerDatetime(String makerDatetime) {
		this.makerDatetime = makerDatetime;
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
	
	@Override
	public String toString() {
		return "TrainPlacement [placementId=" + placementId + ", instituteId=" + instituteId + ", yearId=" + yearId
				+ ", empyrName=" + empyrName + ", programType=" + programType + ", programName=" + programName
				+ ", noStudentPlaced=" + noStudentPlaced + ", supportAgencyName=" + supportAgencyName + ", empyrAdd="
				+ empyrAdd + ", contactDetail=" + contactDetail + ", pakageOfferd=" + pakageOfferd + ", delStatus="
				+ delStatus + ", isActive=" + isActive + ", makerUserId=" + makerUserId + ", makerDatetime="
				+ makerDatetime + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exVar1=" + exVar1 + ", exVar2="
				+ exVar2 + "]";
	}
	
	
}
