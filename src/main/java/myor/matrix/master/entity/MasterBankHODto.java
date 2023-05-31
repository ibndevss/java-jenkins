package myor.matrix.master.entity;

public class MasterBankHODto {
	private String kodeBankHO;
	private String keteranganBankHO;
	
	public MasterBankHODto() {
		// TODO Auto-generated constructor stub
	}
	
	public MasterBankHODto(String kodeBankHO, String keteranganBankHO) {
		super();
		this.kodeBankHO = kodeBankHO;
		this.keteranganBankHO = keteranganBankHO;
	}

	public String getKodeBankHO() {
		return kodeBankHO;
	}

	public void setKodeBankHO(String kodeBankHO) {
		this.kodeBankHO = kodeBankHO;
	}

	public String getKeteranganBankHO() {
		return keteranganBankHO;
	}

	public void setKeteranganBankHO(String keteranganBankHO) {
		this.keteranganBankHO = keteranganBankHO;
	}
	
	

}
