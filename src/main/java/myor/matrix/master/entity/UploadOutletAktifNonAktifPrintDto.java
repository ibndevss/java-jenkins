package myor.matrix.master.entity;

public class UploadOutletAktifNonAktifPrintDto {

	private String DOCNO;
	private String DOCDATE;
	private String XKEY;
	private String TIPE;
	private String STATUS;
	private String CUSTNO;
	private String CUSTNAME;
	private String CUSTADDR;
	private String CHANNEL;
	private String ALASAN;
	private String HEADER;
	private String TGLCETAK;
	private String TGLGUDANG;
	private String RELEASE;
	public UploadOutletAktifNonAktifPrintDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UploadOutletAktifNonAktifPrintDto(String dOCNO, String dOCDATE, String xKEY, String tIPE, String sTATUS,
			String cUSTNO, String cUSTNAME, String cUSTADDR, String cHANNEL, String aLASAN, String hEADER,
			String tGLCETAK, String tGLGUDANG, String rELEASE) {
		super();
		DOCNO = dOCNO;
		DOCDATE = dOCDATE;
		XKEY = xKEY;
		TIPE = tIPE;
		STATUS = sTATUS;
		CUSTNO = cUSTNO;
		CUSTNAME = cUSTNAME;
		CUSTADDR = cUSTADDR;
		CHANNEL = cHANNEL;
		ALASAN = aLASAN;
		HEADER = hEADER;
		TGLCETAK = tGLCETAK;
		TGLGUDANG = tGLGUDANG;
		RELEASE = rELEASE;
	}
	public String getDOCNO() {
		return DOCNO;
	}
	public void setDOCNO(String dOCNO) {
		DOCNO = dOCNO;
	}
	public String getDOCDATE() {
		return DOCDATE;
	}
	public void setDOCDATE(String dOCDATE) {
		DOCDATE = dOCDATE;
	}
	public String getXKEY() {
		return XKEY;
	}
	public void setXKEY(String xKEY) {
		XKEY = xKEY;
	}
	public String getTIPE() {
		return TIPE;
	}
	public void setTIPE(String tIPE) {
		TIPE = tIPE;
	}
	public String getSTATUS() {
		return STATUS;
	}
	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
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
	public String getCUSTADDR() {
		return CUSTADDR;
	}
	public void setCUSTADDR(String cUSTADDR) {
		CUSTADDR = cUSTADDR;
	}
	public String getCHANNEL() {
		return CHANNEL;
	}
	public void setCHANNEL(String cHANNEL) {
		CHANNEL = cHANNEL;
	}
	public String getALASAN() {
		return ALASAN;
	}
	public void setALASAN(String aLASAN) {
		ALASAN = aLASAN;
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
}
