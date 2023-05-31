package myor.matrix.master.entity;

public class MasterBankDto {
	private String kodeBank;
	private String keteranganBank;

	public MasterBankDto() {
		// TODO Auto-generated constructor stub
	}

	public MasterBankDto(String kodeBank, String keteranganBank) {
		super();
		this.kodeBank = kodeBank;
		this.keteranganBank = keteranganBank;
	}

	public String getKodeBank() {
		return kodeBank;
	}

	public void setKodeBank(String kodeBank) {
		this.kodeBank = kodeBank;
	}

	public String getKeteranganBank() {
		return keteranganBank;
	}

	public void setKeteranganBank(String keteranganBank) {
		this.keteranganBank = keteranganBank;
	}

}
