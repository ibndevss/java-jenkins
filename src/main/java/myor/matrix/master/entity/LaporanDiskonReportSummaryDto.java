package myor.matrix.master.entity;

import java.math.BigDecimal;

public class LaporanDiskonReportSummaryDto {
	private String HEADER;
	private String TGLSISTEM;
	private String TGLGUDANG;
	private String RELEASE;
	private String TIPEDISKON;
	private String FILTERAP;
	private String FLAGKLAIM;
	private String FILTERTANGGAL;
	private String PILIHANDISKON;
	private String KODEAP;
	private String INVNO;
	private String INVDATE;
	private String SLSNO;
	private String SLSNAME;
	private String CUSTNO;
	private String CUSTNAME;
	private BigDecimal DISC;
	private BigDecimal PCODE;
	private BigDecimal QTY;
	private BigDecimal AMOUNT;
	private String TRANSTYPE;
	
	public LaporanDiskonReportSummaryDto() {
		// TODO Auto-generated constructor stub
	}

	public LaporanDiskonReportSummaryDto(String hEADER, String tGLSISTEM, String tGLGUDANG, String rELEASE,
			String tIPEDISKON, String fILTERAP, String fLAGKLAIM, String fILTERTANGGAL, String pILIHANDISKON,
			String kODEAP, String iNVNO, String iNVDATE, String sLSNO, String sLSNAME, String cUSTNO, String cUSTNAME,
			BigDecimal dISC, BigDecimal pCODE, BigDecimal qTY, BigDecimal aMOUNT, String tRANSTYPE) {
		super();
		HEADER = hEADER;
		TGLSISTEM = tGLSISTEM;
		TGLGUDANG = tGLGUDANG;
		RELEASE = rELEASE;
		TIPEDISKON = tIPEDISKON;
		FILTERAP = fILTERAP;
		FLAGKLAIM = fLAGKLAIM;
		FILTERTANGGAL = fILTERTANGGAL;
		PILIHANDISKON = pILIHANDISKON;
		KODEAP = kODEAP;
		INVNO = iNVNO;
		INVDATE = iNVDATE;
		SLSNO = sLSNO;
		SLSNAME = sLSNAME;
		CUSTNO = cUSTNO;
		CUSTNAME = cUSTNAME;
		DISC = dISC;
		PCODE = pCODE;
		QTY = qTY;
		AMOUNT = aMOUNT;
		TRANSTYPE = tRANSTYPE;
	}

	public String getHEADER() {
		return HEADER;
	}

	public void setHEADER(String hEADER) {
		HEADER = hEADER;
	}

	public String getTGLSISTEM() {
		return TGLSISTEM;
	}

	public void setTGLSISTEM(String tGLSISTEM) {
		TGLSISTEM = tGLSISTEM;
	}

	public String getTGLGUDANG() {
		return TGLGUDANG;
	}

	public void setTGLGUDANG(String tGLGUDANG) {
		TGLGUDANG = tGLGUDANG;
	}

	public String getRELEASE() {
		return RELEASE;
	}

	public void setRELEASE(String rELEASE) {
		RELEASE = rELEASE;
	}

	public String getTIPEDISKON() {
		return TIPEDISKON;
	}

	public void setTIPEDISKON(String tIPEDISKON) {
		TIPEDISKON = tIPEDISKON;
	}

	public String getFILTERAP() {
		return FILTERAP;
	}

	public void setFILTERAP(String fILTERAP) {
		FILTERAP = fILTERAP;
	}

	public String getFLAGKLAIM() {
		return FLAGKLAIM;
	}

	public void setFLAGKLAIM(String fLAGKLAIM) {
		FLAGKLAIM = fLAGKLAIM;
	}

	public String getFILTERTANGGAL() {
		return FILTERTANGGAL;
	}

	public void setFILTERTANGGAL(String fILTERTANGGAL) {
		FILTERTANGGAL = fILTERTANGGAL;
	}

	public String getPILIHANDISKON() {
		return PILIHANDISKON;
	}

	public void setPILIHANDISKON(String pILIHANDISKON) {
		PILIHANDISKON = pILIHANDISKON;
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

	public String getTRANSTYPE() {
		return TRANSTYPE;
	}

	public void setTRANSTYPE(String tRANSTYPE) {
		TRANSTYPE = tRANSTYPE;
	}

}
