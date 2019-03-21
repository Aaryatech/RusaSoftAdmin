package com.ats.rusasoft.model;

public class LinkageMaster {
	private int linknameId;

	private int instituteId;

	private String linknameText;

	private String linknameRemarks;

	private int delStatus;

	private int isActive;

	private int makerUserId;

	private String makerEnterDatetime;

	public int getLinknameId() {
		return linknameId;
	}

	public void setLinknameId(int linknameId) {
		this.linknameId = linknameId;
	}

	public int getInstituteId() {
		return instituteId;
	}

	public void setInstituteId(int instituteId) {
		this.instituteId = instituteId;
	}

	public String getLinknameText() {
		return linknameText;
	}

	public void setLinknameText(String linknameText) {
		this.linknameText = linknameText;
	}

	public String getLinknameRemarks() {
		return linknameRemarks;
	}

	public void setLinknameRemarks(String linknameRemarks) {
		this.linknameRemarks = linknameRemarks;
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

	public String getMakerEnterDatetime() {
		return makerEnterDatetime;
	}

	public void setMakerEnterDatetime(String makerEnterDatetime) {
		this.makerEnterDatetime = makerEnterDatetime;
	}

	@Override
	public String toString() {
		return "LinkageMaster [linknameId=" + linknameId + ", instituteId=" + instituteId + ", linknameText="
				+ linknameText + ", linknameRemarks=" + linknameRemarks + ", delStatus=" + delStatus + ", isActive="
				+ isActive + ", makerUserId=" + makerUserId + ", makerEnterDatetime=" + makerEnterDatetime + "]";
	}

}
