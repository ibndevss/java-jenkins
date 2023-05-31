package myor.matrix.master.entity;

import java.math.BigDecimal;

public class CekChoosen {

	private String checkNo;
	private String checkDate;
	private String checkDuedT;
	private String bank_no;
	private String bank;
	private String accNo;
	private BigDecimal checkAmount;
	private String custNo;
	private String custName;
	private String flag;
	private BigDecimal payCheck;
	private String owner;
	private String extData;
	private String tglExt;
	private String bankCair;
	private String checkClearDt;
	private String cancelDate;
	private String bankTransfer;
	private String bankTransferName;
	private String bankCairName;
	
	
	public CekChoosen() {
		super();
		// TODO Auto-generated constructor stub
	}


	public CekChoosen(String checkNo, String checkDate, String checkDuedT, String bank_no, String bank, String accNo,
			BigDecimal checkAmount, String custNo, String custName, String flag, BigDecimal payCheck, String owner,
			String extData, String tglExt, String bankCair, String checkClearDt, String cancelDate, String bankTransfer,
			String bankTransferName, String bankCairName) {
		super();
		this.checkNo = checkNo;
		this.checkDate = checkDate;
		this.checkDuedT = checkDuedT;
		this.bank_no = bank_no;
		this.bank = bank;
		this.accNo = accNo;
		this.checkAmount = checkAmount;
		this.custNo = custNo;
		this.custName = custName;
		this.flag = flag;
		this.payCheck = payCheck;
		this.owner = owner;
		this.extData = extData;
		this.tglExt = tglExt;
		this.bankCair = bankCair;
		this.checkClearDt = checkClearDt;
		this.cancelDate = cancelDate;
		this.bankTransfer = bankTransfer;
		this.bankTransferName = bankTransferName;
		this.bankCairName = bankCairName;
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


	public String getBank_no() {
		return bank_no;
	}


	public void setBank_no(String bank_no) {
		this.bank_no = bank_no;
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


	public BigDecimal getCheckAmount() {
		return checkAmount;
	}


	public void setCheckAmount(BigDecimal checkAmount) {
		this.checkAmount = checkAmount;
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


	public String getFlag() {
		return flag;
	}


	public void setFlag(String flag) {
		this.flag = flag;
	}


	public BigDecimal getPayCheck() {
		return payCheck;
	}


	public void setPayCheck(BigDecimal payCheck) {
		this.payCheck = payCheck;
	}


	public String getOwner() {
		return owner;
	}


	public void setOwner(String owner) {
		this.owner = owner;
	}


	public String getExtData() {
		return extData;
	}


	public void setExtData(String extData) {
		this.extData = extData;
	}


	public String getTglExt() {
		return tglExt;
	}


	public void setTglExt(String tglExt) {
		this.tglExt = tglExt;
	}


	public String getBankCair() {
		return bankCair;
	}


	public void setBankCair(String bankCair) {
		this.bankCair = bankCair;
	}


	public String getCheckClearDt() {
		return checkClearDt;
	}


	public void setCheckClearDt(String checkClearDt) {
		this.checkClearDt = checkClearDt;
	}


	public String getCancelDate() {
		return cancelDate;
	}


	public void setCancelDate(String cancelDate) {
		this.cancelDate = cancelDate;
	}


	public String getBankTransfer() {
		return bankTransfer;
	}


	public void setBankTransfer(String bankTransfer) {
		this.bankTransfer = bankTransfer;
	}


	public String getBankTransferName() {
		return bankTransferName;
	}


	public void setBankTransferName(String bankTransferName) {
		this.bankTransferName = bankTransferName;
	}

	public String getBankCairName() {
		return bankCairName;
	}


	public void setBankCairName(String bankCairName) {
		this.bankCairName = bankCairName;
	}

	
}
