package com.ats.rusasoft.model.infra;


public class GetInstInfraAreaInfo{
	
	
	private int  instInfraAreaId;

	private int instId;
	
	private int infraAreaTypeId;
	private int infraAreaId;
	
	private String areaName; //if area Id ==0 then get from this
	private String areaLoc;
	private String areaSqMtr;
	
	private int makerUserId;
	private String areaTypeName;
	
	public int getInstInfraAreaId() {
		return instInfraAreaId;
	}
	public void setInstInfraAreaId(int instInfraAreaId) {
		this.instInfraAreaId = instInfraAreaId;
	}
	public int getInstId() {
		return instId;
	}
	public void setInstId(int instId) {
		this.instId = instId;
	}
	public int getInfraAreaTypeId() {
		return infraAreaTypeId;
	}
	public void setInfraAreaTypeId(int infraAreaTypeId) {
		this.infraAreaTypeId = infraAreaTypeId;
	}
	public int getInfraAreaId() {
		return infraAreaId;
	}
	public void setInfraAreaId(int infraAreaId) {
		this.infraAreaId = infraAreaId;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getAreaLoc() {
		return areaLoc;
	}
	public void setAreaLoc(String areaLoc) {
		this.areaLoc = areaLoc;
	}
	public String getAreaSqMtr() {
		return areaSqMtr;
	}
	public void setAreaSqMtr(String areaSqMtr) {
		this.areaSqMtr = areaSqMtr;
	}
	public int getMakerUserId() {
		return makerUserId;
	}
	public void setMakerUserId(int makerUserId) {
		this.makerUserId = makerUserId;
	}
	public String getAreaTypeName() {
		return areaTypeName;
	}
	public void setAreaTypeName(String areaTypeName) {
		this.areaTypeName = areaTypeName;
	}
	
	@Override
	public String toString() {
		return "GetInstInfraAreaInfo [instInfraAreaId=" + instInfraAreaId + ", instId=" + instId + ", infraAreaTypeId="
				+ infraAreaTypeId + ", infraAreaId=" + infraAreaId + ", areaName=" + areaName + ", areaLoc=" + areaLoc
				+ ", areaSqMtr=" + areaSqMtr + ", makerUserId=" + makerUserId + ", areaTypeName=" + areaTypeName + "]";
	}

}
