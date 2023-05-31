package myor.matrix.master.entity;

public class InvoiceBrowseDto {
	private String invNo;
	private String invDate;
	private String slsNo;
	private String custNo;
	private String transType;
	
	public InvoiceBrowseDto() {
		// TODO Auto-generated constructor stub
	}

	public InvoiceBrowseDto(String invNo, String invDate, String slsNo, String custNo, String transType) {
		super();
		this.invNo = invNo;
		this.invDate = invDate;
		this.slsNo = slsNo;
		this.custNo = custNo;
		this.transType = transType;
	}

	public String getInvNo() {
		return invNo;
	}

	public void setInvNo(String invNo) {
		this.invNo = invNo;
	}

	public String getInvDate() {
		return invDate;
	}

	public void setInvDate(String invDate) {
		this.invDate = invDate;
	}

	public String getSlsNo() {
		return slsNo;
	}

	public void setSlsNo(String slsNo) {
		this.slsNo = slsNo;
	}

	public String getCustNo() {
		return custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}
	
	
}
