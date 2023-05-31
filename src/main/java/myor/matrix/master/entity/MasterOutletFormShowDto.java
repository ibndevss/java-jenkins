package myor.matrix.master.entity;

public class MasterOutletFormShowDto {

	private String XMASTERDATA;
	private String XNPWPBLANK;
	private String XEXTEOD;
	private String OVERDUEGP;
	private String LIMITKREDITGP;
	private String XEDITGROUPDISC;
	private String XEDITSPESIFIC;
	private String TGLGUDANG;
	private String PEKAN;
	public MasterOutletFormShowDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MasterOutletFormShowDto(String xMASTERDATA, String xNPWPBLANK, String xEXTEOD, String oVERDUEGP,
			String lIMITKREDITGP, String xEDITGROUPDISC, String xEDITSPESIFIC, String tGLGUDANG, String pEKAN) {
		super();
		XMASTERDATA = xMASTERDATA;
		XNPWPBLANK = xNPWPBLANK;
		XEXTEOD = xEXTEOD;
		OVERDUEGP = oVERDUEGP;
		LIMITKREDITGP = lIMITKREDITGP;
		XEDITGROUPDISC = xEDITGROUPDISC;
		XEDITSPESIFIC = xEDITSPESIFIC;
		TGLGUDANG = tGLGUDANG;
		PEKAN = pEKAN;
	}
	public String getXMASTERDATA() {
		return XMASTERDATA;
	}
	public void setXMASTERDATA(String xMASTERDATA) {
		XMASTERDATA = xMASTERDATA;
	}
	public String getXNPWPBLANK() {
		return XNPWPBLANK;
	}
	public void setXNPWPBLANK(String xNPWPBLANK) {
		XNPWPBLANK = xNPWPBLANK;
	}
	public String getXEXTEOD() {
		return XEXTEOD;
	}
	public void setXEXTEOD(String xEXTEOD) {
		XEXTEOD = xEXTEOD;
	}
	public String getOVERDUEGP() {
		return OVERDUEGP;
	}
	public void setOVERDUEGP(String oVERDUEGP) {
		OVERDUEGP = oVERDUEGP;
	}
	public String getLIMITKREDITGP() {
		return LIMITKREDITGP;
	}
	public void setLIMITKREDITGP(String lIMITKREDITGP) {
		LIMITKREDITGP = lIMITKREDITGP;
	}
	public String getXEDITGROUPDISC() {
		return XEDITGROUPDISC;
	}
	public void setXEDITGROUPDISC(String xEDITGROUPDISC) {
		XEDITGROUPDISC = xEDITGROUPDISC;
	}
	public String getXEDITSPESIFIC() {
		return XEDITSPESIFIC;
	}
	public void setXEDITSPESIFIC(String xEDITSPESIFIC) {
		XEDITSPESIFIC = xEDITSPESIFIC;
	}
	public String getTGLGUDANG() {
		return TGLGUDANG;
	}
	public void setTGLGUDANG(String tGLGUDANG) {
		TGLGUDANG = tGLGUDANG;
	}
	public String getPEKAN() {
		return PEKAN;
	}
	public void setPEKAN(String pEKAN) {
		PEKAN = pEKAN;
	}
}
