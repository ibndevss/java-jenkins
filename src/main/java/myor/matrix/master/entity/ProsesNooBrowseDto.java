package myor.matrix.master.entity;

public class ProsesNooBrowseDto {

	private String docNo = "";
	private String docDate = "";
	private String reqKey = "";
	private String groupDivisi = "";
	private String slsNo = "";
	private String slsName = "";
	public ProsesNooBrowseDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProsesNooBrowseDto(String docNo, String docDate, String reqKey, String groupDivisi, String slsNo,
			String slsName) {
		super();
		this.docNo = docNo;
		this.docDate = docDate;
		this.reqKey = reqKey;
		this.groupDivisi = groupDivisi;
		this.slsNo = slsNo;
		this.slsName = slsName;
	}
	public String getDocNo() {
		return docNo;
	}
	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}
	public String getDocDate() {
		return docDate;
	}
	public void setDocDate(String docDate) {
		this.docDate = docDate;
	}
	public String getReqKey() {
		return reqKey;
	}
	public void setReqKey(String reqKey) {
		this.reqKey = reqKey;
	}
	public String getGroupDivisi() {
		return groupDivisi;
	}
	public void setGroupDivisi(String groupDivisi) {
		this.groupDivisi = groupDivisi;
	}
	public String getSlsNo() {
		return slsNo;
	}
	public void setSlsNo(String slsNo) {
		this.slsNo = slsNo;
	}
	public String getSlsName() {
		return slsName;
	}
	public void setSlsName(String slsName) {
		this.slsName = slsName;
	}
	
}