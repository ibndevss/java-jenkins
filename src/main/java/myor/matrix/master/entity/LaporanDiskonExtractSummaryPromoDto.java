package myor.matrix.master.entity;

import java.math.BigDecimal;

public class LaporanDiskonExtractSummaryPromoDto {
	private String KODECABANG;
	private String NAMACABANG;
	private String KODEAP;
	private String INVNO;
	private String INVDATE;
	private String SLSNO;
	private String SLSNAME;
	private String CUSTNO;
	private String CUSTNAME;
	private String PCTPR;
	private String PCTPRNAME;
	private BigDecimal BESAR;
	private BigDecimal TENGAH;
	private BigDecimal KECIL;
	private BigDecimal TPRAMOUNT;
	private BigDecimal AMOUNT;
	
	public LaporanDiskonExtractSummaryPromoDto() {
		// TODO Auto-generated constructor stub
	}

	public LaporanDiskonExtractSummaryPromoDto(String kODECABANG, String nAMACABANG, String kODEAP, String iNVNO,
			String iNVDATE, String sLSNO, String sLSNAME, String cUSTNO, String cUSTNAME, String pCTPR,
			String pCTPRNAME, BigDecimal bESAR, BigDecimal tENGAH, BigDecimal kECIL, BigDecimal tPRAMOUNT,
			BigDecimal aMOUNT) {
		super();
		KODECABANG = kODECABANG;
		NAMACABANG = nAMACABANG;
		KODEAP = kODEAP;
		INVNO = iNVNO;
		INVDATE = iNVDATE;
		SLSNO = sLSNO;
		SLSNAME = sLSNAME;
		CUSTNO = cUSTNO;
		CUSTNAME = cUSTNAME;
		PCTPR = pCTPR;
		PCTPRNAME = pCTPRNAME;
		BESAR = bESAR;
		TENGAH = tENGAH;
		KECIL = kECIL;
		TPRAMOUNT = tPRAMOUNT;
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

	public String getINVNO() {
		return INVNO;
	}

	public void setINVNO(String iNVNO) {
		INVNO = iNVNO;
	}

	public String getINVDATE() {
		return INVDATE;
	}

	public void setINVDATE(String iNVDATE) {
		INVDATE = iNVDATE;
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

	public String getCUSTNO() {
		return CUSTNO;
	}

	public void setCUSTNO(String cUSTNO) {
		CUSTNO = cUSTNO;
	}

	public String getCUSTNAME() {
		return CUSTNAME;
	}

	public void setCUSTNAME(String cUSTNAME) {
		CUSTNAME = cUSTNAME;
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
}
