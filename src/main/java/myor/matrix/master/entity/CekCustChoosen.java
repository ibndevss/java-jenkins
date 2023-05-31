package myor.matrix.master.entity;

public class CekCustChoosen {

	private String custNo;
	private String custName;
	
	public CekCustChoosen() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CekCustChoosen(String custNo, String custName) {
		super();
		this.custNo = custNo;
		this.custName = custName;
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
	
	
	
}
