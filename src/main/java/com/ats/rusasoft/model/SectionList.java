package com.ats.rusasoft.model;
 

public class SectionList {
	  
    private int yesnoId; 
	private String yesnoPagecode; 
	private String yesnoSeccode; 
	private String yesnoSecname;
	
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
	public String getYesnoSecname() {
		return yesnoSecname;
	}
	public void setYesnoSecname(String yesnoSecname) {
		this.yesnoSecname = yesnoSecname;
	}
	public String getYesnoSeccode() {
		return yesnoSeccode;
	}
	public void setYesnoSeccode(String yesnoSeccode) {
		this.yesnoSeccode = yesnoSeccode;
	}
	@Override
	public String toString() {
		return "SectionList [yesnoId=" + yesnoId + ", yesnoPagecode=" + yesnoPagecode + ", yesnoSeccode=" + yesnoSeccode
				+ ", yesnoSecname=" + yesnoSecname + "]";
	}
	
	

}
