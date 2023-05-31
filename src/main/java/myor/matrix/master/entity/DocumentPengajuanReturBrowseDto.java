package myor.matrix.master.entity;

public class DocumentPengajuanReturBrowseDto {
private String docNo;
private String docDate;
public DocumentPengajuanReturBrowseDto() {
	super();
	// TODO Auto-generated constructor stub
}
public DocumentPengajuanReturBrowseDto(String docNo, String docDate) {
	super();
	this.docNo = docNo;
	this.docDate = docDate;
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

}
