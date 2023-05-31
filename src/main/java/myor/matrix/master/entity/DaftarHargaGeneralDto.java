package myor.matrix.master.entity;

import java.math.BigDecimal;

public class DaftarHargaGeneralDto {
	
	private String PCODE;
	private String NAMABARANG;
	private String TIPE;
	private BigDecimal HARGABSR;
	private BigDecimal HARGATGH;
	private BigDecimal HARGAKCL;
	private String TGLGANTI;
	private String HEADER;
	private String TGLCETAK;
	private String TGLGUDANG;
	private String RELEASE;
	private String INPUTPCODE;
	private String INPUTDATEBERLAKU;
	public DaftarHargaGeneralDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DaftarHargaGeneralDto(String pCODE, String nAMABARANG, String tIPE, BigDecimal hARGABSR, BigDecimal hARGATGH,
			BigDecimal hARGAKCL, String tGLGANTI, String hEADER, String tGLCETAK, String tGLGUDANG, String rELEASE,
			String iNPUTPCODE, String iNPUTDATEBERLAKU) {
		super();
		PCODE = pCODE;
		NAMABARANG = nAMABARANG;
		TIPE = tIPE;
		HARGABSR = hARGABSR;
		HARGATGH = hARGATGH;
		HARGAKCL = hARGAKCL;
		TGLGANTI = tGLGANTI;
		HEADER = hEADER;
		TGLCETAK = tGLCETAK;
		TGLGUDANG = tGLGUDANG;
		RELEASE = rELEASE;
		INPUTPCODE = iNPUTPCODE;
		INPUTDATEBERLAKU = iNPUTDATEBERLAKU;
	}
	public String getPCODE() {
		return PCODE;
	}
	public void setPCODE(String pCODE) {
		PCODE = pCODE;
	}
	public String getNAMABARANG() {
		return NAMABARANG;
	}
	public void setNAMABARANG(String nAMABARANG) {
		NAMABARANG = nAMABARANG;
	}
	public String getTIPE() {
		return TIPE;
	}
	public void setTIPE(String tIPE) {
		TIPE = tIPE;
	}
	public BigDecimal getHARGABSR() {
		return HARGABSR;
	}
	public void setHARGABSR(BigDecimal hARGABSR) {
		HARGABSR = hARGABSR;
	}
	public BigDecimal getHARGATGH() {
		return HARGATGH;
	}
	public void setHARGATGH(BigDecimal hARGATGH) {
		HARGATGH = hARGATGH;
	}
	public BigDecimal getHARGAKCL() {
		return HARGAKCL;
	}
	public void setHARGAKCL(BigDecimal hARGAKCL) {
		HARGAKCL = hARGAKCL;
	}
	public String getTGLGANTI() {
		return TGLGANTI;
	}
	public void setTGLGANTI(String tGLGANTI) {
		TGLGANTI = tGLGANTI;
	}
	public String getHEADER() {
		return HEADER;
	}
	public void setHEADER(String hEADER) {
		HEADER = hEADER;
	}
	public String getTGLCETAK() {
		return TGLCETAK;
	}
	public void setTGLCETAK(String tGLCETAK) {
		TGLCETAK = tGLCETAK;
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
	public String getINPUTPCODE() {
		return INPUTPCODE;
	}
	public void setINPUTPCODE(String iNPUTPCODE) {
		INPUTPCODE = iNPUTPCODE;
	}
	public String getINPUTDATEBERLAKU() {
		return INPUTDATEBERLAKU;
	}
	public void setINPUTDATEBERLAKU(String iNPUTDATEBERLAKU) {
		INPUTDATEBERLAKU = iNPUTDATEBERLAKU;
	}
}
