package myor.matrix.master.entity;

import java.math.BigDecimal;

public class ArmadaAllDto {

	private String noKend;
	private String jnsKend;
	private String jenisBbm;
	private BigDecimal capacity;
	private BigDecimal kubikase;
	private BigDecimal kmAkhir;
	private String statusAktif;
	private BigDecimal tonase;
	private String jenisMuat;
	private String driverCode;
	private String helperCode;
	private String statusKend;
	private BigDecimal stdIsi;
	private BigDecimal stdIsi2;
	private BigDecimal maxRit;
	private int flagEdit;
	private String dedicatedData;
	private String tglEfektif;
	private String akhirEfektif;
	private String xkey;
	private String keyAuth;
	
	
	public ArmadaAllDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ArmadaAllDto(String noKend, String jnsKend, String jenisBbm, BigDecimal capacity, BigDecimal kubikase,
			BigDecimal kmAkhir, String statusAktif, BigDecimal tonase, String jenisMuat, String driverCode,
			String helperCode, String statusKend, BigDecimal stdIsi, BigDecimal stdIsi2, BigDecimal maxRit,
			int flagEdit, String dedicatedData, String tglEfektif, String akhirEfektif) {
		super();
		this.noKend = noKend;
		this.jnsKend = jnsKend;
		this.jenisBbm = jenisBbm;
		this.capacity = capacity;
		this.kubikase = kubikase;
		this.kmAkhir = kmAkhir;
		this.statusAktif = statusAktif;
		this.tonase = tonase;
		this.jenisMuat = jenisMuat;
		this.driverCode = driverCode;
		this.helperCode = helperCode;
		this.statusKend = statusKend;
		this.stdIsi = stdIsi;
		this.stdIsi2 = stdIsi2;
		this.maxRit = maxRit;
		this.flagEdit = flagEdit;
		this.dedicatedData = dedicatedData;
		this.tglEfektif = tglEfektif;
		this.akhirEfektif = akhirEfektif;
	}

	public String getNoKend() {
		return noKend;
	}


	public void setNoKend(String noKend) {
		this.noKend = noKend;
	}


	public String getJnsKend() {
		return jnsKend;
	}


	public void setJnsKend(String jnsKend) {
		this.jnsKend = jnsKend;
	}


	public String getJenisBbm() {
		return jenisBbm;
	}


	public void setJenisBbm(String jenisBbm) {
		this.jenisBbm = jenisBbm;
	}


	public BigDecimal getCapacity() {
		return capacity;
	}


	public void setCapacity(BigDecimal capacity) {
		this.capacity = capacity;
	}


	public BigDecimal getKubikase() {
		return kubikase;
	}


	public void setKubikase(BigDecimal kubikase) {
		this.kubikase = kubikase;
	}


	public BigDecimal getKmAkhir() {
		return kmAkhir;
	}


	public void setKmAkhir(BigDecimal kmAkhir) {
		this.kmAkhir = kmAkhir;
	}


	public String getStatusAktif() {
		return statusAktif;
	}


	public void setStatusAktif(String statusAktif) {
		this.statusAktif = statusAktif;
	}


	public BigDecimal getTonase() {
		return tonase;
	}


	public void setTonase(BigDecimal tonase) {
		this.tonase = tonase;
	}


	public String getJenisMuat() {
		return jenisMuat;
	}


	public void setJenisMuat(String jenisMuat) {
		this.jenisMuat = jenisMuat;
	}


	public String getDriverCode() {
		return driverCode;
	}


	public void setDriverCode(String driverCode) {
		this.driverCode = driverCode;
	}


	public String getHelperCode() {
		return helperCode;
	}


	public void setHelperCode(String helperCode) {
		this.helperCode = helperCode;
	}


	public String getStatusKend() {
		return statusKend;
	}


	public void setStatusKend(String statusKend) {
		this.statusKend = statusKend;
	}


	public BigDecimal getStdIsi() {
		return stdIsi;
	}


	public void setStdIsi(BigDecimal stdIsi) {
		this.stdIsi = stdIsi;
	}


	public BigDecimal getStdIsi2() {
		return stdIsi2;
	}


	public void setStdIsi2(BigDecimal stdIsi2) {
		this.stdIsi2 = stdIsi2;
	}


	public BigDecimal getMaxRit() {
		return maxRit;
	}


	public void setMaxRit(BigDecimal maxRit) {
		this.maxRit = maxRit;
	}


	public int getFlagEdit() {
		return flagEdit;
	}


	public void setFlagEdit(int flagEdit) {
		this.flagEdit = flagEdit;
	}

	public String getDedicatedData() {
		return dedicatedData;
	}

	public void setDedicatedData(String dedicatedData) {
		this.dedicatedData = dedicatedData;
	}

	public String getTglEfektif() {
		return tglEfektif;
	}

	public void setTglEfektif(String tglEfektif) {
		this.tglEfektif = tglEfektif;
	}

	public String getAkhirEfektif() {
		return akhirEfektif;
	}

	public void setAkhirEfektif(String akhirEfektif) {
		this.akhirEfektif = akhirEfektif;
	}

	public String getXkey() {
		return xkey;
	}

	public void setXkey(String xkey) {
		this.xkey = xkey;
	}

	public String getKeyAuth() {
		return keyAuth;
	}

	public void setKeyAuth(String keyAuth) {
		this.keyAuth = keyAuth;
	}
	
	
}
