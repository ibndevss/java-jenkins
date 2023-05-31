package myor.matrix.master.entity;

public class ListDocumentDto {

	private String docNo;
	private String docDate;
	private String salesNo;
	public ListDocumentDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ListDocumentDto(String docNo, String docDate, String salesNo) {
		super();
		this.docNo = docNo;
		this.docDate = docDate;
		this.salesNo = salesNo;
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
	public String getSalesNo() {
		return salesNo;
	}
	public void setSalesNo(String salesNo) {
		this.salesNo = salesNo;
	}
}
