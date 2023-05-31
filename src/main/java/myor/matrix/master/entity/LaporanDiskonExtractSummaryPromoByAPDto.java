package myor.matrix.master.entity;

import java.math.BigDecimal;

public class LaporanDiskonExtractSummaryPromoByAPDto {
	private String KODECABANG;
	private String NAMACABANG;
	private String KODEAP;
	private String KETERANGAN;
	private String AWAL;
	private String AKHIR;
	private String SLSNO;
	private String SLSNAME;	
	private String PCTPR;
	private String PCTPRNAME;
	private BigDecimal QTY;
	private BigDecimal TPRAMOUNT;
	private BigDecimal AMOUNT;
	private BigDecimal CONVUNIT2;
	private BigDecimal CONVUNIT3;
	private BigDecimal BESAR;
	private BigDecimal TENGAH;
	private BigDecimal KECIL;
	
	public LaporanDiskonExtractSummaryPromoByAPDto() {
		// TODO Auto-generated constructor stub
	}

	public LaporanDiskonExtractSummaryPromoByAPDto(String kODECABANG, String nAMACABANG, String kODEAP, String kETERANGAN,
			String aWAL, String aKHIR, String sLSNO, String sLSNAME, String pCTPR, String pCTPRNAME, BigDecimal qTY,
			BigDecimal tPRAMOUNT, BigDecimal aMOUNT, BigDecimal cONVUNIT2, BigDecimal cONVUNIT3, BigDecimal bESAR,
			BigDecimal tENGAH, BigDecimal kECIL) {
		super();
		KODECABANG = kODECABANG;
		NAMACABANG = nAMACABANG;
		KODEAP = kODEAP;
		KETERANGAN = kETERANGAN;
		AWAL = aWAL;
		AKHIR = aKHIR;
		SLSNO = sLSNO;
		SLSNAME = sLSNAME;
		PCTPR = pCTPR;
		PCTPRNAME = pCTPRNAME;
		QTY = qTY;
		TPRAMOUNT = tPRAMOUNT;
		AMOUNT = aMOUNT;
		CONVUNIT2 = cONVUNIT2;
		CONVUNIT3 = cONVUNIT3;
		BESAR = bESAR;
		TENGAH = tENGAH;
		KECIL = kECIL;
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

	public String getPCTPR() {
		return PCTPR;
	}

	public void setPCTPR(String pCTPR) {
		PCTPR = pCTPR;
	}

	public String getPCTPRNAME() {
		return PCTPRNAME;
	}

	public void setPCTPRNAME(String pCTPRNAME) {
		PCTPRNAME = pCTPRNAME;
	}

	public BigDecimal getQTY() {
		return QTY;
	}

	public void setQTY(BigDecimal qTY) {
		QTY = qTY;
	}

	public BigDecimal getTPRAMOUNT() {
		return TPRAMOUNT;
	}

	public void setTPRAMOUNT(BigDecimal tPRAMOUNT) {
		TPRAMOUNT = tPRAMOUNT;
	}

	public BigDecimal getAMOUNT() {
		return AMOUNT;
	}

	public void setAMOUNT(BigDecimal aMOUNT) {
		AMOUNT = aMOUNT;
	}

	public BigDecimal getCONVUNIT2() {
		return CONVUNIT2;
	}

	public void setCONVUNIT2(BigDecimal cONVUNIT2) {
		CONVUNIT2 = cONVUNIT2;
	}

	public BigDecimal getCONVUNIT3() {
		return CONVUNIT3;
	}

	public void setCONVUNIT3(BigDecimal cONVUNIT3) {
		CONVUNIT3 = cONVUNIT3;
	}

	public BigDecimal getBESAR() {
		return BESAR;
	}

	public void setBESAR(BigDecimal bESAR) {
		BESAR = bESAR;
	}

	public BigDecimal getTENGAH() {
		return TENGAH;
	}

	public void setTENGAH(BigDecimal tENGAH) {
		TENGAH = tENGAH;
	}

	public BigDecimal getKECIL() {
		return KECIL;
	}

	public void setKECIL(BigDecimal kECIL) {
		KECIL = kECIL;
	}
	
}
