package com.ats.rusasoft.model.infra;

public class InstInfraAreaInfo {
	
	private int  instInfraAreaId;

	private int instId;
	
	private int infraAreaTypeId;
	private int infraAreaId;
	
	private String areaName; //if area Id ==0 then get from this
	private String areaLoc;
	private String areaSqMtr;
	
	private int makerUserId;
	private String makerDatetime;
	
	private int isActive;
	private int delStatus;
	
	private int exInt1;
	private int exInt2;
	
	private String exVar1;
	private String exVar2;
	
	
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
	public String getMakerDatetime() {
		return makerDatetime;
	}
	public void setMakerDatetime(String makerDatetime) {
		this.makerDatetime = makerDatetime;
	}
	public int getIsActive() {
		return isActive;
	}
	public void setIsActive(int isActive) {
		this.isActive = isActive;
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
	
	@Override
	public String toString() {
		return "InstInfraAreaInfo [instInfraAreaId=" + instInfraAreaId + ", instId=" + instId + ", infraAreaTypeId="
				+ infraAreaTypeId + ", infraAreaId=" + infraAreaId + ", areaName=" + areaName + ", areaLoc=" + areaLoc
				+ ", areaSqMtr=" + areaSqMtr + ", makerUserId=" + makerUserId + ", makerDatetime=" + makerDatetime
				+ ", isActive=" + isActive + ", delStatus=" + delStatus + ", exInt1=" + exInt1 + ", exInt2=" + exInt2
				+ ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + "]";
	}
	
}
