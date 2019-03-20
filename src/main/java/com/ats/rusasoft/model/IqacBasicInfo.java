package com.ats.rusasoft.model;

public class IqacBasicInfo {

	private int	iqacInfoId;
	
	private int	instituteId ;
	
	private String	estabilishmentDate ;
	
	private String iqacAltName  ;
	
	private String	iqacAltMobile  ;
	
	private String	iqacAltFax  ;
	
	private String	iqacAltEmail1 ;
	
	private String	iqacAltPhone ;
	
	private String iqacAltEmail2;
	
	private int delStatus;
	
	private int isActive;
	
	private int exInt1;
	
	private int exInt2;
	
	private String exVar1;
	
	private String exVar2;
	
	
private int makerUserId;
	
	private String makerEnterDatetime;

	public int getIqacInfoId() {
		return iqacInfoId;
	}

	public void setIqacInfoId(int iqacInfoId) {
		this.iqacInfoId = iqacInfoId;
	}

	public int getInstituteId() {
		return instituteId;
	}

	public void setInstituteId(int instituteId) {
		this.instituteId = instituteId;
	}

	public String getEstabilishmentDate() {
		return estabilishmentDate;
	}

	public void setEstabilishmentDate(String estabilishmentDate) {
		this.estabilishmentDate = estabilishmentDate;
	}

	public String getIqacAltName() {
		return iqacAltName;
	}

	public void setIqacAltName(String iqacAltName) {
		this.iqacAltName = iqacAltName;
	}

	public String getIqacAltMobile() {
		return iqacAltMobile;
	}

	public void setIqacAltMobile(String iqacAltMobile) {
		this.iqacAltMobile = iqacAltMobile;
	}

	public String getIqacAltFax() {
		return iqacAltFax;
	}

	public void setIqacAltFax(String iqacAltFax) {
		this.iqacAltFax = iqacAltFax;
	}

	public String getIqacAltEmail1() {
		return iqacAltEmail1;
	}

	public void setIqacAltEmail1(String iqacAltEmail1) {
		this.iqacAltEmail1 = iqacAltEmail1;
	}

	public String getIqacAltPhone() {
		return iqacAltPhone;
	}

	public void setIqacAltPhone(String iqacAltPhone) {
		this.iqacAltPhone = iqacAltPhone;
	}

	public String getIqacAltEmail2() {
		return iqacAltEmail2;
	}

	public void setIqacAltEmail2(String iqacAltEmail2) {
		this.iqacAltEmail2 = iqacAltEmail2;
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

	
	
	public int getMakerUserId() {
		return makerUserId;
	}

	public void setMakerUserId(int makerUserId) {
		this.makerUserId = makerUserId;
	}

	public String getMakerEnterDatetime() {
		return makerEnterDatetime;
	}

	public void setMakerEnterDatetime(String makerEnterDatetime) {
		this.makerEnterDatetime = makerEnterDatetime;
	}

	@Override
	public String toString() {
		return "IqacBasicInfo [iqacInfoId=" + iqacInfoId + ", instituteId=" + instituteId + ", estabilishmentDate="
				+ estabilishmentDate + ", iqacAltName=" + iqacAltName + ", iqacAltMobile=" + iqacAltMobile
				+ ", iqacAltFax=" + iqacAltFax + ", iqacAltEmail1=" + iqacAltEmail1 + ", iqacAltPhone=" + iqacAltPhone
				+ ", iqacAltEmail2=" + iqacAltEmail2 + ", delStatus=" + delStatus + ", isActive=" + isActive
				+ ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2
				+ ", makerUserId=" + makerUserId + ", makerEnterDatetime=" + makerEnterDatetime + ", getIqacInfoId()="
				+ getIqacInfoId() + ", getInstituteId()=" + getInstituteId() + ", getEstabilishmentDate()="
				+ getEstabilishmentDate() + ", getIqacAltName()=" + getIqacAltName() + ", getIqacAltMobile()="
				+ getIqacAltMobile() + ", getIqacAltFax()=" + getIqacAltFax() + ", getIqacAltEmail1()="
				+ getIqacAltEmail1() + ", getIqacAltPhone()=" + getIqacAltPhone() + ", getIqacAltEmail2()="
				+ getIqacAltEmail2() + ", getDelStatus()=" + getDelStatus() + ", getIsActive()=" + getIsActive()
				+ ", getExInt1()=" + getExInt1() + ", getExInt2()=" + getExInt2() + ", getExVar1()=" + getExVar1()
				+ ", getExVar2()=" + getExVar2() + ", getMakerUserId()=" + getMakerUserId()
				+ ", getMakerEnterDatetime()=" + getMakerEnterDatetime() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

	
	
}
