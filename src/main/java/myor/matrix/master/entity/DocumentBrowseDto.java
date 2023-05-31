package myor.matrix.master.entity;

public class DocumentBrowseDto {

	private String docNo;
	private String docDate;
	private String slsNo;
	private String slsName;
	public DocumentBrowseDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DocumentBrowseDto(String docNo, String docDate, String slsNo, String slsName) {
		super();
		this.docNo = docNo;
		this.docDate = docDate;
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
