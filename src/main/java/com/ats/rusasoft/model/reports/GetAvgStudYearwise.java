package com.ats.rusasoft.model.reports;


public class GetAvgStudYearwise {


 	private int locationId;

 	
	private String locationName;

	private String instituteName;

	private int acYearAdmiStud1;
	
	private int acYearAdmiStud2;
	
	private int acYearAdmiStud3;
	
	private int acYearAdmiStud4;
	
	private int acYearAdmiStud5;

	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getInstituteName() {
		return instituteName;
	}

	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}

	public int getAcYearAdmiStud1() {
		return acYearAdmiStud1;
	}

	public void setAcYearAdmiStud1(int acYearAdmiStud1) {
		this.acYearAdmiStud1 = acYearAdmiStud1;
	}

	public int getAcYearAdmiStud2() {
		return acYearAdmiStud2;
	}

	public void setAcYearAdmiStud2(int acYearAdmiStud2) {
		this.acYearAdmiStud2 = acYearAdmiStud2;
	}

	public int getAcYearAdmiStud3() {
		return acYearAdmiStud3;
	}

	public void setAcYearAdmiStud3(int acYearAdmiStud3) {
		this.acYearAdmiStud3 = acYearAdmiStud3;
	}

	public int getAcYearAdmiStud4() {
		return acYearAdmiStud4;
	}

	public void setAcYearAdmiStud4(int acYearAdmiStud4) {
		this.acYearAdmiStud4 = acYearAdmiStud4;
	}

	public int getAcYearAdmiStud5() {
		return acYearAdmiStud5;
	}

	public void setAcYearAdmiStud5(int acYearAdmiStud5) {
		this.acYearAdmiStud5 = acYearAdmiStud5;
	}

	@Override
	public String toString() {
		return "GetAvgStudYearwise [locationId=" + locationId + ", locationName=" + locationName + ", instituteName="
				+ instituteName + ", acYearAdmiStud1=" + acYearAdmiStud1 + ", acYearAdmiStud2=" + acYearAdmiStud2
				+ ", acYearAdmiStud3=" + acYearAdmiStud3 + ", acYearAdmiStud4=" + acYearAdmiStud4 + ", acYearAdmiStud5="
				+ acYearAdmiStud5 + "]";
	}
 

	
	
	
}
