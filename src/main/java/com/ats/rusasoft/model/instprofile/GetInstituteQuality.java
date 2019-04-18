package com.ats.rusasoft.model.instprofile;


public class GetInstituteQuality {
	
	private int qualityId;
	
	private int qualityInitiativeId; 
	
	private String qualityFromdt;
	private String qualityTodt;
	
	private int qualityPcount;
	
	private String qualityInitiativeName;
	private String exVar1;
	
	public String getExVar1() {
		return exVar1;
	}

	public void setExVar1(String exVar1) {
		this.exVar1 = exVar1;
	}
	public int getQualityId() {
		return qualityId;
	}

	public void setQualityId(int qualityId) {
		this.qualityId = qualityId;
	}

	public int getQualityInitiativeId() {
		return qualityInitiativeId;
	}

	public void setQualityInitiativeId(int qualityInitiativeId) {
		this.qualityInitiativeId = qualityInitiativeId;
	}

	public String getQualityFromdt() {
		return qualityFromdt;
	}

	public void setQualityFromdt(String qualityFromdt) {
		this.qualityFromdt = qualityFromdt;
	}

	public String getQualityTodt() {
		return qualityTodt;
	}

	public void setQualityTodt(String qualityTodt) {
		this.qualityTodt = qualityTodt;
	}

	public int getQualityPcount() {
		return qualityPcount;
	}

	public void setQualityPcount(int qualityPcount) {
		this.qualityPcount = qualityPcount;
	}

	public String getQualityInitiativeName() {
		return qualityInitiativeName;
	}

	public void setQualityInitiativeName(String qualityInitiativeName) {
		this.qualityInitiativeName = qualityInitiativeName;
	}

	@Override
	public String toString() {
		return "GetInstituteQuality [qualityId=" + qualityId + ", qualityInitiativeId=" + qualityInitiativeId
				+ ", qualityFromdt=" + qualityFromdt + ", qualityTodt=" + qualityTodt + ", qualityPcount="
				+ qualityPcount + ", qualityInitiativeName=" + qualityInitiativeName + "]";
	} 

}
