package com.ats.rusasoft.master.model.prodetail;

public class GetTrainPlace {

	private int placementId ;
	private String empyrName;
	private String programName;
	private String progTypeName;
	
	private int noStudentPlaced ;
	
	private String empyrAdd ;
	private String	contactDetail ;
	private String  pakageOfferd ;
	public int getPlacementId() {
		return placementId;
	}
	public void setPlacementId(int placementId) {
		this.placementId = placementId;
	}
	public String getEmpyrName() {
		return empyrName;
	}
	public void setEmpyrName(String empyrName) {
		this.empyrName = empyrName;
	}
	public String getProgramName() {
		return programName;
	}
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	public String getProgTypeName() {
		return progTypeName;
	}
	public void setProgTypeName(String progTypeName) {
		this.progTypeName = progTypeName;
	}
	public int getNoStudentPlaced() {
		return noStudentPlaced;
	}
	public void setNoStudentPlaced(int noStudentPlaced) {
		this.noStudentPlaced = noStudentPlaced;
	}
	public String getEmpyrAdd() {
		return empyrAdd;
	}
	public void setEmpyrAdd(String empyrAdd) {
		this.empyrAdd = empyrAdd;
	}
	public String getContactDetail() {
		return contactDetail;
	}
	public void setContactDetail(String contactDetail) {
		this.contactDetail = contactDetail;
	}
	public String getPakageOfferd() {
		return pakageOfferd;
	}
	public void setPakageOfferd(String pakageOfferd) {
		this.pakageOfferd = pakageOfferd;
	}
	
	@Override
	public String toString() {
		return "GetTrainPlace [placementId=" + placementId + ", empyrName=" + empyrName + ", programName=" + programName
				+ ", progTypeName=" + progTypeName + ", noStudentPlaced=" + noStudentPlaced + ", empyrAdd=" + empyrAdd
				+ ", contactDetail=" + contactDetail + ", pakageOfferd=" + pakageOfferd + "]";
	}
	
	/*SELECT t_traning_placement.placement_id,t_traning_placement.empyr_name,m_program_type.program_name as prog_type_name, t_traning_placement.program_name,t_traning_placement.no_student_placed,t_traning_placement.empyr_add, t_traning_placement.contact_detail,t_traning_placement.pakage_offerd FROM t_traning_placement INNER JOIN m_program_type ON t_traning_placement.program_type=m_program_type.program_id WHERE t_traning_placement.year_id=1 AND t_traning_placement.institute_id=2 */
}
