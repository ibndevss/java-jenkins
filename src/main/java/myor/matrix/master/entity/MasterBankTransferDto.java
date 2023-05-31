package myor.matrix.master.entity;

public class MasterBankTransferDto {
	private String kodeBankTransfer;
	private String keteranganBankTransfer;

	public MasterBankTransferDto() {
		// TODO Auto-generated constructor stub
	}

	public MasterBankTransferDto(String kodeBankTransfer, String keteranganBankTransfer) {
		super();
		this.kodeBankTransfer = kodeBankTransfer;
		this.keteranganBankTransfer = keteranganBankTransfer;
	}

	public String getKodeBankTransfer() {
		return kodeBankTransfer;
	}

	public void setKodeBankTransfer(String kodeBankTransfer) {
		this.kodeBankTransfer = kodeBankTransfer;
	}

	public String getKeteranganBankTransfer() {
		return keteranganBankTransfer;
	}

	public void setKeteranganBankTransfer(String keteranganBankTransfer) {
		this.keteranganBankTransfer = keteranganBankTransfer;
	}

}
