package myor.matrix.master.entity;

import java.math.BigDecimal;

public class ArmadaChoosenDto {
	
	private String noKend;
	private String jnsKend;
	private BigDecimal capacity;
	private String jenisBbm;
	private BigDecimal kubikase;
	private BigDecimal kmAkhir;
	private String driverCode;
	private String helperCode;
	private String statusKend;
	private BigDecimal stdIsi;
	private BigDecimal stdIsi2;
	private String statusAktif;	
	
	public ArmadaChoosenDto() {
		
	}

	public ArmadaChoosenDto(String noKend, String jnsKend, BigDecimal capacity, String jenisBbm, BigDecimal kubikase,
			BigDecimal kmAkhir, String driverCode, String helperCode, String statusKend, BigDecimal stdIsi, BigDecimal stdIsi2,
			String statusAktif) {
		super();
		this.noKend = noKend;
		this.jnsKend = jnsKend;
		this.capacity = capacity;
		this.jenisBbm = jenisBbm;
		this.kubikase = kubikase;
		this.kmAkhir = kmAkhir;
		this.driverCode = driverCode;
		this.helperCode = helperCode;
		this.statusKend = statusKend;
		this.stdIsi = stdIsi;
		this.stdIsi2 = stdIsi2;
		this.statusAktif = statusAktif;
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

	public BigDecimal getCapacity() {
		return capacity;
	}

	public void setCapacity(BigDecimal capacity) {
		this.capacity = capacity;
	}

	public String getJenisBbm() {
		return jenisBbm;
	}

	public void setJenisBbm(String jenisBbm) {
		this.jenisBbm = jenisBbm;
	}

	public BigDecimal getkubikase() {
		return kubikase;
	}

	public void setkubikase(BigDecimal kubikase) {
		this.kubikase = kubikase;
	}

	public BigDecimal getKmAkhir() {
		return kmAkhir;
	}

	public void setKmAkhir(BigDecimal kmAkhir) {
		this.kmAkhir = kmAkhir;
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

	public String getStatusAktif() {
		return statusAktif;
	}

	public void setStatusAktif(String statusAktif) {
		this.statusAktif = statusAktif;
	}

	
	

}
