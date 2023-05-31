package myor.matrix.master.entity;

public class MasterOutletBrowseDto {
	
	private String custNo;
	private String custName;
	private String custAdd1;
	public MasterOutletBrowseDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MasterOutletBrowseDto(String custNo, String custName, String custAdd1) {
		super();
		this.custNo = custNo;
		this.custName = custName;
		this.custAdd1 = custAdd1;
	}
	public String getCustNo() {
		return custNo;
	}
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCustAdd1() {
		return custAdd1;
	}
	public void setCustAdd1(String custAdd1) {
		this.custAdd1 = custAdd1;
	}
}
