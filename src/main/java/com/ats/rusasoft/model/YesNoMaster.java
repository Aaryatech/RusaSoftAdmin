package com.ats.rusasoft.model;

import java.util.Arrays;

public class YesNoMaster {
	
	 
    private int yesnoId; 
	private String yesnoPagecode; 
	private String yesnoSeccode; 
	private String yesnoSecname; 
	private int yesnoOrderby; 
	private int yesnoType; 
	private String yesnoTitle; 
	private String yesnoResponseTitle;  
	private int yesnoResponseType; 
	private String yesnoResponseContent; 
	private int delStatus; 
	private int isActive; 
	private String makerDatetime; 
	private int makerUserId; 
	String[] jsonArray;
	public int getYesnoId() {
		return yesnoId;
	}
	public void setYesnoId(int yesnoId) {
		this.yesnoId = yesnoId;
	}
	public String getYesnoPagecode() {
		return yesnoPagecode;
	}
	public void setYesnoPagecode(String yesnoPagecode) {
		this.yesnoPagecode = yesnoPagecode;
	}
	public String getYesnoSeccode() {
		return yesnoSeccode;
	}
	public void setYesnoSeccode(String yesnoSeccode) {
		this.yesnoSeccode = yesnoSeccode;
	}
	public String getYesnoSecname() {
		return yesnoSecname;
	}
	public void setYesnoSecname(String yesnoSecname) {
		this.yesnoSecname = yesnoSecname;
	}
	public int getYesnoOrderby() {
		return yesnoOrderby;
	}
	public void setYesnoOrderby(int yesnoOrderby) {
		this.yesnoOrderby = yesnoOrderby;
	}
	public int getYesnoType() {
		return yesnoType;
	}
	public void setYesnoType(int yesnoType) {
		this.yesnoType = yesnoType;
	}
	public String getYesnoTitle() {
		return yesnoTitle;
	}
	public void setYesnoTitle(String yesnoTitle) {
		this.yesnoTitle = yesnoTitle;
	}
	public String getYesnoResponseTitle() {
		return yesnoResponseTitle;
	}
	public void setYesnoResponseTitle(String yesnoResponseTitle) {
		this.yesnoResponseTitle = yesnoResponseTitle;
	}
	public int getYesnoResponseType() {
		return yesnoResponseType;
	}
	public void setYesnoResponseType(int yesnoResponseType) {
		this.yesnoResponseType = yesnoResponseType;
	}
	public String getYesnoResponseContent() {
		return yesnoResponseContent;
	}
	public void setYesnoResponseContent(String yesnoResponseContent) {
		this.yesnoResponseContent = yesnoResponseContent;
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
	public String getMakerDatetime() {
		return makerDatetime;
	}
	public void setMakerDatetime(String makerDatetime) {
		this.makerDatetime = makerDatetime;
	}
	public int getMakerUserId() {
		return makerUserId;
	}
	public void setMakerUserId(int makerUserId) {
		this.makerUserId = makerUserId;
	}
	public String[] getJsonArray() {
		return jsonArray;
	}
	public void setJsonArray(String[] jsonArray) {
		this.jsonArray = jsonArray;
	}
	@Override
	public String toString() {
		return "YesNoMaster [yesnoId=" + yesnoId + ", yesnoPagecode=" + yesnoPagecode + ", yesnoSeccode=" + yesnoSeccode
				+ ", yesnoSecname=" + yesnoSecname + ", yesnoOrderby=" + yesnoOrderby + ", yesnoType=" + yesnoType
				+ ", yesnoTitle=" + yesnoTitle + ", yesnoResponseTitle=" + yesnoResponseTitle + ", yesnoResponseType="
				+ yesnoResponseType + ", yesnoResponseContent=" + yesnoResponseContent + ", delStatus=" + delStatus
				+ ", isActive=" + isActive + ", makerDatetime=" + makerDatetime + ", makerUserId=" + makerUserId
				+ ", jsonArray=" + Arrays.toString(jsonArray) + "]";
	}
	
	

}
