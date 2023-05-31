package myor.matrix.master.entity;

import java.math.BigDecimal;

public class LaporanDiskonExtractSummaryDto {
	private String KODECABANG;
	private String NAMACABANG;
	private String KODEAP;
	private String INVNO;
	private String INVDATE;
	private String SLSNO;
	private String SLSNAME;
	private String CUSTNO;
	private String CUSTNAME;
	private String NPWP;
	private String ALAMAT;
	private BigDecimal DISC;
	private BigDecimal PCODE;
	private BigDecimal QTY;
	private BigDecimal AMOUNT;
	
	public LaporanDiskonExtractSummaryDto() {
		// TODO Auto-generated constructor stub
	}

	public LaporanDiskonExtractSummaryDto(String kODECABANG, String nAMACABANG, String kODEAP, String iNVNO,
			String iNVDATE, String sLSNO, String sLSNAME, String cUSTNO, String cUSTNAME, String nPWP, String aLAMAT,
			BigDecimal dISC, BigDecimal pCODE, BigDecimal qTY, BigDecimal aMOUNT) {
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
		NPWP = nPWP;
		ALAMAT = aLAMAT;
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

	public String getNPWP() {
		return NPWP;
	}

	public void setNPWP(String nPWP) {
		NPWP = nPWP;
	}

	public String getALAMAT() {
		return ALAMAT;
	}

	public void setALAMAT(String aLAMAT) {
		ALAMAT = aLAMAT;
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
