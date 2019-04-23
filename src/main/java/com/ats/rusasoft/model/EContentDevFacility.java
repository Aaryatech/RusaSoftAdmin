package com.ats.rusasoft.model;

public class EContentDevFacility {
	
	private int instEContentDevFacilityId;
	private int instId;
	private String eContentDevFacility;
	private String nameEcontentDevFacility;
	private String videoLink;
	private int delStatus;
	private int isActive;
	private int makerUserId;
	private String makerDatetime;
	private int exInt1;
	private int exInt2;
	private String exVar1;
	private String exVar2;
	
	public int getInstEContentDevFacilityId() {
		return instEContentDevFacilityId;
	}
	public void setInstEContentDevFacilityId(int instEContentDevFacilityId) {
		this.instEContentDevFacilityId = instEContentDevFacilityId;
	}
	public int getInstId() {
		return instId;
	}
	public void setInstId(int instId) {
		this.instId = instId;
	}
	public String geteContentDevFacility() {
		return eContentDevFacility;
	}
	public void seteContentDevFacility(String eContentDevFacility) {
		this.eContentDevFacility = eContentDevFacility;
	}
	
	public String getNameEcontentDevFacility() {
		return nameEcontentDevFacility;
	}
	public void setNameEcontentDevFacility(String nameEcontentDevFacility) {
		this.nameEcontentDevFacility = nameEcontentDevFacility;
	}
	public String getVideoLink() {
		return videoLink;
	}
	public void setVideoLink(String videoLink) {
		this.videoLink = videoLink;
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
	@Override
	public String toString() {
		return "TInstEContentDevFacility [instEContentDevFacilityId=" + instEContentDevFacilityId + ", instId=" + instId
				+ ", eContentDevFacility=" + eContentDevFacility + ", nameEcontentDevFacility="
				+ nameEcontentDevFacility + ", videoLink=" + videoLink + ", delStatus=" + delStatus + ", isActive="
				+ isActive + ", makerUserId=" + makerUserId + ", makerDatetime=" + makerDatetime + ", exInt1=" + exInt1
				+ ", exInt2=" + exInt2 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + "]";
	}
	
	
}
