package com.ats.rusasoft.master.model.prodetail;

public class StudQualifyingExam {
	
	private int studExmId;
	private String nameQualifExam;
	private String levelExam;
	private int noStudAppeared;
	private int noStudQualified;
	private int instId;
	private int acYearId;
	private int isActive;
	private int delStatus;
	private int makerUserId;
	private String makerEnterDatetime;
	private int exInt1;
	private int exInt2;	
	private String exVar1;
	private String exVar2;
	
	public int getStudExmId() {
		return studExmId;
	}
	public void setStudExmId(int studExmId) {
		this.studExmId = studExmId;
	}
	public String getNameQualifExam() {
		return nameQualifExam;
	}
	public void setNameQualifExam(String nameQualifExam) {
		this.nameQualifExam = nameQualifExam;
	}
	
	public String getLevelExam() {
		return levelExam;
	}
	public void setLevelExam(String levelExam) {
		this.levelExam = levelExam;
	}
	public int getNoStudAppeared() {
		return noStudAppeared;
	}
	public void setNoStudAppeared(int noStudAppeared) {
		this.noStudAppeared = noStudAppeared;
	}
	public int getNoStudQualified() {
		return noStudQualified;
	}
	public void setNoStudQualified(int noStudQualified) {
		this.noStudQualified = noStudQualified;
	}
	public int getInstId() {
		return instId;
	}
	public void setInstId(int instId) {
		this.instId = instId;
	}
	public int getAcYearId() {
		return acYearId;
	}
	public void setAcYearId(int acYearId) {
		this.acYearId = acYearId;
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
		return "StudQualifyingExam [studExmId=" + studExmId + ", nameQualifExam=" + nameQualifExam + ", levelExam="
				+ levelExam + ", noStudAppeared=" + noStudAppeared + ", noStudQualified=" + noStudQualified
				+ ", instId=" + instId + ", acYearId=" + acYearId + ", isActive=" + isActive + ", delStatus="
				+ delStatus + ", makerUserId=" + makerUserId + ", makerEnterDatetime=" + makerEnterDatetime
				+ ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + "]";
	}
	
}
