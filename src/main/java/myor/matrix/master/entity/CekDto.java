package myor.matrix.master.entity;

import java.math.BigDecimal;

public class CekDto {

	private String checkNo;
	private String checkDate;
	private String checkDuedT;
	private String bank;
	private String accNo;
	private String flag;
	private String custNo;
	private BigDecimal checkAmount;
	private BigDecimal payCheck;
	
	public CekDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CekDto(String checkNo, String checkDate, String checkDuedT, String bank, String accNo, String flag,
			String custNo, BigDecimal checkAmount, BigDecimal payCheck) {
		super();
		this.checkNo = checkNo;
		this.checkDate = checkDate;
		this.checkDuedT = checkDuedT;
		this.bank = bank;
		this.accNo = accNo;
		this.flag = flag;
		this.custNo = custNo;
		this.checkAmount = checkAmount;
		this.payCheck = payCheck;
	}

	public String getCheckNo() {
		return checkNo;
	}

	public void setCheckNo(String checkNo) {
		this.checkNo = checkNo;
	}

	public String getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}

	public String getCheckDuedT() {
		return checkDuedT;
	}

	public void setCheckDuedT(String checkDuedT) {
		this.checkDuedT = checkDuedT;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getAccNo() {
		return accNo;
	}

	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getCustNo() {
		return custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	public BigDecimal getCheckAmount() {
		return checkAmount;
	}

	public void setCheckAmount(BigDecimal checkAmount) {
		this.checkAmount = checkAmount;
	}

	public BigDecimal getPayCheck() {
		return payCheck;
	}

	public void setPayCheck(BigDecimal payCheck) {
		this.payCheck = payCheck;
	}
	

	
	
	
}
