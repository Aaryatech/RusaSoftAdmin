package com.ats.rusasoft.model;

import java.util.List;

public class ProgramDetailSaveResponse {
	
	private Info info;
	List<ProgramVision> programVissionList;
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
	@Override
	public String toString() {
		return "ProgramDetailSaveResponse [info=" + info + ", programVissionList=" + programVissionList + "]";
	}
	
	

}
