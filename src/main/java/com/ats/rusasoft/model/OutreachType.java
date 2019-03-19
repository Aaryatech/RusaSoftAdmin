package com.ats.rusasoft.model;





public class OutreachType {
	
	
	private int typeId;
	
	private int instituteId;
	
	private String typeName;
	
	private int delStatus;
	
	private int exInt1;
	
	private String exVar1;
	

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public int getInstituteId() {
		return instituteId;
	}

	public void setInstituteId(int instituteId) {
		this.instituteId = instituteId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	public int getExInt1() {
		return exInt1;
	}

	public void setExInt1(int exInt1) {
		this.exInt1 = exInt1;
	}

	public String getExVar1() {
		return exVar1;
	}

	public void setExVar1(String exVar1) {
		this.exVar1 = exVar1;
	}

	@Override
	public String toString() {
		return "OutreachType [typeId=" + typeId + ", instituteId=" + instituteId + ", typeName=" + typeName
				+ ", delStatus=" + delStatus + ", exInt1=" + exInt1 + ", exVar1=" + exVar1 + "]";
	}
	
	
	
	
	
	
	

}
