package myor.matrix.master.entity;

public class ProsesNooDetailDto {

	private String docNo = "";
	private String docDate = "";
	private String slsNo = "";
	private String slsName = "";
	private String custName = "";
	private String custAdd = "";
	private String slsType = "";
	public ProsesNooDetailDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProsesNooDetailDto(String docNo, String docDate, String slsNo, String slsName, String custName,
			String custAdd, String slsType) {
		super();
		this.docNo = docNo;
		this.docDate = docDate;
		this.slsNo = slsNo;
		this.slsName = slsName;
		this.custName = custName;
		this.custAdd = custAdd;
		this.slsType = slsType;
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
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCustAdd() {
		return custAdd;
	}
	public void setCustAdd(String custAdd) {
		this.custAdd = custAdd;
	}
	public String getSlsType() {
		return slsType;
	}
	public void setSlsType(String slsType) {
		this.slsType = slsType;
	}
}
