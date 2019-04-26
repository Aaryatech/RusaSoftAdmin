package com.ats.rusasoft.model.infra;

public class TInstInternetInfo {

	private int instInternetInfoId;
	private int instId;
	private int noOfCompWithInternetAccess;
	private String leasedLineBandwidth;
	private String lanConfSpeed;
	private String ispName;
	private String purchaseDate;
	private int delStatus;
	private int isActive;
	private int makerUserId;
	private String makerDatetime;
	private int exInt1;
	private int exInt2;
	private String exVar1;
	private String exVar2;
	public int getInstInternetInfoId() {
		return instInternetInfoId;
	}
	public void setInstInternetInfoId(int instInternetInfoId) {
		this.instInternetInfoId = instInternetInfoId;
	}
	public int getInstId() {
		return instId;
	}
	public void setInstId(int instId) {
		this.instId = instId;
	}
	public int getNoOfCompWithInternetAccess() {
		return noOfCompWithInternetAccess;
	}
	public void setNoOfCompWithInternetAccess(int noOfCompWithInternetAccess) {
		this.noOfCompWithInternetAccess = noOfCompWithInternetAccess;
	}
	public String getLeasedLineBandwidth() {
		return leasedLineBandwidth;
	}
	public void setLeasedLineBandwidth(String leasedLineBandwidth) {
		this.leasedLineBandwidth = leasedLineBandwidth;
	}
	public String getLanConfSpeed() {
		return lanConfSpeed;
	}
	public void setLanConfSpeed(String lanConfSpeed) {
		this.lanConfSpeed = lanConfSpeed;
	}
	public String getIspName() {
		return ispName;
	}
	public void setIspName(String ispName) {
		this.ispName = ispName;
	}
	public String getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
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
		return "TInstInternetInfo [instInternetInfoId=" + instInternetInfoId + ", instId=" + instId
				+ ", noOfCompWithInternetAccess=" + noOfCompWithInternetAccess + ", leasedLineBandwidth="
				+ leasedLineBandwidth + ", lanConfSpeed=" + lanConfSpeed + ", ispName=" + ispName + ", purchaseDate="
				+ purchaseDate + ", delStatus=" + delStatus + ", isActive=" + isActive + ", makerUserId=" + makerUserId
				+ ", makerDatetime=" + makerDatetime + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exVar1="
				+ exVar1 + ", exVar2=" + exVar2 + "]";
	}
	
}
