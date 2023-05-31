package myor.matrix.master.entity;

public class CekBankChoosen {

	private String bankNo;
	private String bankName;
	
	
	public CekBankChoosen() {
		super();
		// TODO Auto-generated constructor stub
	}


	public CekBankChoosen(String bankNo, String bankName) {
		super();
		this.bankNo = bankNo;
		this.bankName = bankName;
	}


	public String getBankNo() {
		return bankNo;
	}


	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}


	public String getBankName() {
		return bankName;
	}


	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	
	
}
