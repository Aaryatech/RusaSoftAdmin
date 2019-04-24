package com.ats.rusasoft.model.infra;

public class InfraAreaType {

	private int infraAreaTypeId;
	private String areaTypeName;
	
	private int  delStatus;
	private int exInt1;
	private String exVar1;
	
	
	public int getInfraAreaTypeId() {
		return infraAreaTypeId;
	}
	public void setInfraAreaTypeId(int infraAreaTypeId) {
		this.infraAreaTypeId = infraAreaTypeId;
	}
	public String getAreaTypeName() {
		return areaTypeName;
	}
	public void setAreaTypeName(String areaTypeName) {
		this.areaTypeName = areaTypeName;
	}
	public int getDelStatus() {
		return delStatus;
	}
	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}
	public int getExInt1() {
		return exInt1;
	}
	public void setExInt1(int exInt1) {
		this.exInt1 = exInt1;
	}
	public String getExVar1() {
		return exVar1;
	}
	public void setExVar1(String exVar1) {
		this.exVar1 = exVar1;
	}
	
	@Override
	public String toString() {
		return "InfraAreaType [infraAreaTypeId=" + infraAreaTypeId + ", areaTypeName=" + areaTypeName + ", delStatus="
				+ delStatus + ", exInt1=" + exInt1 + ", exVar1=" + exVar1 + "]";
	}
	
	
}
