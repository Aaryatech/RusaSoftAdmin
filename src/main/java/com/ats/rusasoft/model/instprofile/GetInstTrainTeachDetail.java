package com.ats.rusasoft.model.instprofile;


public class GetInstTrainTeachDetail {

	private int trainingId;

	private int yearId;

	private int trainingType;
	private String trainingTitle;

	private String trainingFromdt;
	private String trainingTodt;
	private String exVar1;
	private int trainingPcount;

	
	public String getExVar1() {
		return exVar1;
	}

	public void setExVar1(String exVar1) {
		this.exVar1 = exVar1;
	}

	public int getTrainingId() {
		return trainingId;
	}

	public void setTrainingId(int trainingId) {
		this.trainingId = trainingId;
	}

	public int getYearId() {
		return yearId;
	}

	public void setYearId(int yearId) {
		this.yearId = yearId;
	}

	public int getTrainingType() {
		return trainingType;
	}

	public void setTrainingType(int trainingType) {
		this.trainingType = trainingType;
	}

	public String getTrainingTitle() {
		return trainingTitle;
	}

	public void setTrainingTitle(String trainingTitle) {
		this.trainingTitle = trainingTitle;
	}

	public String getTrainingFromdt() {
		return trainingFromdt;
	}

	public void setTrainingFromdt(String trainingFromdt) {
		this.trainingFromdt = trainingFromdt;
	}

	public String getTrainingTodt() {
		return trainingTodt;
	}

	public void setTrainingTodt(String trainingTodt) {
		this.trainingTodt = trainingTodt;
	}

	public int getTrainingPcount() {
		return trainingPcount;
	}

	public void setTrainingPcount(int trainingPcount) {
		this.trainingPcount = trainingPcount;
	}

	@Override
	public String toString() {
		return "GetInstTrainTeachDetail [trainingId=" + trainingId + ", yearId=" + yearId + ", trainingType="
				+ trainingType + ", trainingTitle=" + trainingTitle + ", trainingFromdt=" + trainingFromdt
				+ ", trainingTodt=" + trainingTodt + ", exVar1=" + exVar1 + ", trainingPcount=" + trainingPcount + "]";
	}

}
