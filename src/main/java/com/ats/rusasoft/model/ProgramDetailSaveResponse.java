package com.ats.rusasoft.model;

import java.util.List;

import com.ats.rusasoft.master.ProgramMission;

public class ProgramDetailSaveResponse {
	
	private Info info;
	List<ProgramVision> programVissionList;
	List<ProgramMission> programMissionList;
	List<ProgramEducationObjective>  programEducationObjectiveList;
	List<ProgramOutcome> programOutcomeList;
	
	public Info getInfo() {
		return info;
	}
	public void setInfo(Info info) {
		this.info = info;
	}
	public List<ProgramVision> getProgramVissionList() {
		return programVissionList;
	}
	public void setProgramVissionList(List<ProgramVision> programVissionList) {
		this.programVissionList = programVissionList;
	}
	public List<ProgramMission> getProgramMissionList() {
		return programMissionList;
	}
	public void setProgramMissionList(List<ProgramMission> programMissionList) {
		this.programMissionList = programMissionList;
	}
	public List<ProgramEducationObjective> getProgramEducationObjectiveList() {
		return programEducationObjectiveList;
	}
	public void setProgramEducationObjectiveList(List<ProgramEducationObjective> programEducationObjectiveList) {
		this.programEducationObjectiveList = programEducationObjectiveList;
	}
	public List<ProgramOutcome> getProgramOutcomeList() {
		return programOutcomeList;
	}
	public void setProgramOutcomeList(List<ProgramOutcome> programOutcomeList) {
		this.programOutcomeList = programOutcomeList;
	}
	@Override
	public String toString() {
		return "ProgramDetailSaveResponse [info=" + info + ", programVissionList=" + programVissionList
				+ ", programMissionList=" + programMissionList + ", programEducationObjectiveList="
				+ programEducationObjectiveList + ", programOutcomeList=" + programOutcomeList + "]";
	}
	 
	
	

}
