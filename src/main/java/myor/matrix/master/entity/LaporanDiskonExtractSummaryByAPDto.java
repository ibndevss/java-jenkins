package myor.matrix.master.entity;

import java.math.BigDecimal;

public class LaporanDiskonExtractSummaryByAPDto {
	private String KODECABANG;
	private String NAMACABANG;
	private String KODEAP;
	private String KETERANGAN;
	private String AWAL;
	private String AKHIR;
	private String SLSNO;
	private String SLSNAME;
	private BigDecimal DISC;
	private BigDecimal PCODE;	
	private BigDecimal QTY;
	private BigDecimal AMOUNT;
	
	public LaporanDiskonExtractSummaryByAPDto() {
		// TODO Auto-generated constructor stub
	}

	public LaporanDiskonExtractSummaryByAPDto(String kODECABANG, String nAMACABANG, String kODEAP, String kETERANGAN,
			String aWAL, String aKHIR, String sLSNO, String sLSNAME, BigDecimal dISC, BigDecimal pCODE, BigDecimal qTY,
			BigDecimal aMOUNT) {
		super();
		KODECABANG = kODECABANG;
		NAMACABANG = nAMACABANG;
		KODEAP = kODEAP;
		KETERANGAN = kETERANGAN;
		AWAL = aWAL;
		AKHIR = aKHIR;
		SLSNO = sLSNO;
		SLSNAME = sLSNAME;
		DISC = dISC;
		PCODE = pCODE;
		QTY = qTY;
		AMOUNT = aMOUNT;
	}

	public String getKODECABANG() {
		return KODECABANG;
	}

	public void setKODECABANG(String kODECABANG) {
		KODECABANG = kODECABANG;
	}

	public String getNAMACABANG() {
		return NAMACABANG;
	}

	public void setNAMACABANG(String nAMACABANG) {
		NAMACABANG = nAMACABANG;
	}

	public String getKODEAP() {
		return KODEAP;
	}

	public void setKODEAP(String kODEAP) {
		KODEAP = kODEAP;
	}

	public String getKETERANGAN() {
		return KETERANGAN;
	}

	public void setKETERANGAN(String kETERANGAN) {
		KETERANGAN = kETERANGAN;
	}

	public String getAWAL() {
		return AWAL;
	}

	public void setAWAL(String aWAL) {
		AWAL = aWAL;
	}

	public String getAKHIR() {
		return AKHIR;
	}

	public void setAKHIR(String aKHIR) {
		AKHIR = aKHIR;
	}

	public String getSLSNO() {
		return SLSNO;
	}

	public void setSLSNO(String sLSNO) {
		SLSNO = sLSNO;
	}

	public String getSLSNAME() {
		return SLSNAME;
	}

	public void setSLSNAME(String sLSNAME) {
		SLSNAME = sLSNAME;
	}

	public BigDecimal getDISC() {
		return DISC;
	}

	public void setDISC(BigDecimal dISC) {
		DISC = dISC;
	}

	public BigDecimal getPCODE() {
		return PCODE;
	}

	public void setPCODE(BigDecimal pCODE) {
		PCODE = pCODE;
	}

	public BigDecimal getQTY() {
		return QTY;
	}

	public void setQTY(BigDecimal qTY) {
		QTY = qTY;
	}

	public BigDecimal getAMOUNT() {
		return AMOUNT;
	}

	public void setAMOUNT(BigDecimal aMOUNT) {
		AMOUNT = aMOUNT;
	}
}
