package com.ats.rusasoft.model;

import java.util.Date;


public class Dean {
	
	
	private int deanId;
	private int instituteId;
	private String deanName;
	private String contactNo;
	private String email;
	private int qualificationId;
	private String joiningDate;
	private String realivingDate;
	private int makerUserId;
	private String makerEnterDatetime;
	private int extraint1;
	private String extravarchar1;
	private int delStatus;
	
	public int getDeanId() {
		return deanId;
	}
	public void setDeanId(int deanId) {
		this.deanId = deanId;
	}
	
	public int getInstituteId() {
		return instituteId;
	}
	public void setInstituteId(int instituteId) {
		this.instituteId = instituteId;
	}
	public String getDeanName() {
		return deanName;
	}
	public void setDeanName(String deanName) {
		this.deanName = deanName;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getQualificationId() {
		return qualificationId;
	}
	public void setQualificationId(int qualificationId) {
		this.qualificationId = qualificationId;
	}
	
	public int getMakerUserId() {
		return makerUserId;
	}
	public void setMakerUserId(int makerUserId) {
		this.makerUserId = makerUserId;
	}
	public String getJoiningDate() {
		return joiningDate;
	}
	public void setJoiningDate(String joiningDate) {
		this.joiningDate = joiningDate;
	}
	public String getRealivingDate() {
		return realivingDate;
	}
	public void setRealivingDate(String realivingDate) {
		this.realivingDate = realivingDate;
	}
	public String getMakerEnterDatetime() {
		return makerEnterDatetime;
	}
	public void setMakerEnterDatetime(String makerEnterDatetime) {
		this.makerEnterDatetime = makerEnterDatetime;
	}
	public int getExtraint1() {
		return extraint1;
	}
	public void setExtraint1(int extraint1) {
		this.extraint1 = extraint1;
	}
	public String getExtravarchar1() {
		return extravarchar1;
	}
	public void setExtravarchar1(String extravarchar1) {
		this.extravarchar1 = extravarchar1;
	}
	
	public int getDelStatus() {
		return delStatus;
	}
	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}
	@Override
	public String toString() {
		return "Dean [deanId=" + deanId + ", instituteId=" + instituteId + ", deanName=" + deanName + ", contactNo="
				+ contactNo + ", email=" + email + ", qualificationId=" + qualificationId + ", joiningDate="
				+ joiningDate + ", realivingDate=" + realivingDate + ", makerUserId=" + makerUserId
				+ ", makerEnterDatetime=" + makerEnterDatetime + ", extraint1=" + extraint1 + ", extravarchar1="
				+ extravarchar1 + ", delStatus=" + delStatus + "]";
	}
	

}
