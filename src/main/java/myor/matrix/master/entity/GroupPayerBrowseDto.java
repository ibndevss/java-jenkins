package myor.matrix.master.entity;

public class GroupPayerBrowseDto {

    private String custNo;
    private String custName;
    private String custAddress;
    private String custDivisi;
	public GroupPayerBrowseDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public GroupPayerBrowseDto(String custNo, String custName, String custAddress, String custDivisi) {
		super();
		this.custNo = custNo;
		this.custName = custName;
		this.custAddress = custAddress;
		this.custDivisi = custDivisi;
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
	public String getCustAddress() {
		return custAddress;
	}
	public void setCustAddress(String custAddress) {
		this.custAddress = custAddress;
	}
	public String getCustDivisi() {
		return custDivisi;
	}
	public void setCustDivisi(String custDivisi) {
		this.custDivisi = custDivisi;
	}
}
