package com.ats.rusasoft.model.reports;
 
public class IQACQualInititive {
	
	 
	private int qualityInitiativeId;
	
	 private String qualityInitiativeName;
	 
	private  String instituteName;
	
	private  int qStatus;

	public int getQualityInitiativeId() {
		return qualityInitiativeId;
	}

	public void setQualityInitiativeId(int qualityInitiativeId) {
		this.qualityInitiativeId = qualityInitiativeId;
	}

	public String getQualityInitiativeName() {
		return qualityInitiativeName;
	}

	public void setQualityInitiativeName(String qualityInitiativeName) {
		this.qualityInitiativeName = qualityInitiativeName;
	}

	public String getInstituteName() {
		return instituteName;
	}

	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}

	public int getqStatus() {
		return qStatus;
	}

	public void setqStatus(int qStatus) {
		this.qStatus = qStatus;
	}

	@Override
	public String toString() {
		return "IQACQualInititive [qualityInitiativeId=" + qualityInitiativeId + ", qualityInitiativeName="
				+ qualityInitiativeName + ", instituteName=" + instituteName + ", qStatus=" + qStatus
				+ ", getQualityInitiativeId()=" + getQualityInitiativeId() + ", getQualityInitiativeName()="
				+ getQualityInitiativeName() + ", getInstituteName()=" + getInstituteName() + ", getqStatus()="
				+ getqStatus() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	 

}
