package com.ats.rusasoft.model.instprofile;


public class GetInstituteLinkage {
	
	private int linkId;
	
	private String  linknameText ;
	private String linkAgency ;
	
	private String linkNature ;
	
	private String linkBeneficiary ;
	
 	private int linkBeneficiaryNos;

	public int getLinkId() {
		return linkId;
	}

	public void setLinkId(int linkId) {
		this.linkId = linkId;
	}

	public String getLinknameText() {
		return linknameText;
	}

	public void setLinknameText(String linknameText) {
		this.linknameText = linknameText;
	}

	public String getLinkAgency() {
		return linkAgency;
	}

	public void setLinkAgency(String linkAgency) {
		this.linkAgency = linkAgency;
	}

	public String getLinkNature() {
		return linkNature;
	}

	public void setLinkNature(String linkNature) {
		this.linkNature = linkNature;
	}

	public String getLinkBeneficiary() {
		return linkBeneficiary;
	}

	public void setLinkBeneficiary(String linkBeneficiary) {
		this.linkBeneficiary = linkBeneficiary;
	}

	public int getLinkBeneficiaryNos() {
		return linkBeneficiaryNos;
	}

	public void setLinkBeneficiaryNos(int linkBeneficiaryNos) {
		this.linkBeneficiaryNos = linkBeneficiaryNos;
	}

	@Override
	public String toString() {
		return "GetInstituteLinkage [linkId=" + linkId + ", linknameText=" + linknameText + ", linkAgency=" + linkAgency
				+ ", linkNature=" + linkNature + ", linkBeneficiary=" + linkBeneficiary + ", linkBeneficiaryNos="
				+ linkBeneficiaryNos + ", getLinkId()=" + getLinkId() + ", getLinknameText()=" + getLinknameText()
				+ ", getLinkAgency()=" + getLinkAgency() + ", getLinkNature()=" + getLinkNature()
				+ ", getLinkBeneficiary()=" + getLinkBeneficiary() + ", getLinkBeneficiaryNos()="
				+ getLinkBeneficiaryNos() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	} 
 	
 	
 	

}
