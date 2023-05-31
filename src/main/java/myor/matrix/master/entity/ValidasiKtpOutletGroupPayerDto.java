package myor.matrix.master.entity;

public class ValidasiKtpOutletGroupPayerDto {
	String custno;
	String custName;
	String salesman;
	public ValidasiKtpOutletGroupPayerDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ValidasiKtpOutletGroupPayerDto(String custno, String custName, String salesman) {
		super();
		this.custno = custno;
		this.custName = custName;
		this.salesman = salesman;
	}
	public String getCustno() {
		return custno;
	}
	public void setCustno(String custno) {
		this.custno = custno;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getSalesman() {
		return salesman;
	}
	public void setSalesman(String salesman) {
		this.salesman = salesman;
	}
	
}
