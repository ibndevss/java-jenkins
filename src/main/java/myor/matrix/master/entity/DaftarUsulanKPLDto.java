package myor.matrix.master.entity;

import java.math.BigDecimal;

public class DaftarUsulanKPLDto {

	private String TGL;
	private String SLSNO;
	private String SLSNAME;	
	private String KETERANGAN;
	private String USERUSULAN;
	private String USEROTOMATIS;
	private String CUSTNO;
	private String TIPE;
	private String CUSTNAME;
	private BigDecimal ROUTE;
	private String HEADER;
	private String TGLGUDANG;
	private String RELEASE;
	private String TGLSYSTEM;
	private String SLSFROM;
	private String SLSTO;
	private String TGLFROM;
	private String TGLTO;
	
	public DaftarUsulanKPLDto(String tGL, String sLSNO, String sLSNAME, String kETERANGAN, String uSERUSULAN,
			String uSEROTOMATIS, String cUSTNO, String tIPE, String cUSTNAME, BigDecimal rOUTE, String hEADER,
			String tGLGUDANG, String rELEASE, String tGLSYSTEM, String sLSFROM, String sLSTO, String tGLFROM,
			String tGLTO) {
		super();
		TGL = tGL;
		SLSNO = sLSNO;
		SLSNAME = sLSNAME;
		KETERANGAN = kETERANGAN;
		USERUSULAN = uSERUSULAN;
		USEROTOMATIS = uSEROTOMATIS;
		CUSTNO = cUSTNO;
		TIPE = tIPE;
		CUSTNAME = cUSTNAME;
		ROUTE = rOUTE;
		HEADER = hEADER;
		TGLGUDANG = tGLGUDANG;
		RELEASE = rELEASE;
		TGLSYSTEM = tGLSYSTEM;
		SLSFROM = sLSFROM;
		SLSTO = sLSTO;
		TGLFROM = tGLFROM;
		TGLTO = tGLTO;
	}
	
	public String getTGL() {
		return TGL;
	}
	public void setTGL(String tGL) {
		TGL = tGL;
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
	public String getKETERANGAN() {
		return KETERANGAN;
	}
	public void setKETERANGAN(String kETERANGAN) {
		KETERANGAN = kETERANGAN;
	}
	public String getUSERUSULAN() {
		return USERUSULAN;
	}
	public void setUSERUSULAN(String uSERUSULAN) {
		USERUSULAN = uSERUSULAN;
	}
	public String getUSEROTOMATIS() {
		return USEROTOMATIS;
	}
	public void setUSEROTOMATIS(String uSEROTOMATIS) {
		USEROTOMATIS = uSEROTOMATIS;
	}
	public String getCUSTNO() {
		return CUSTNO;
	}
	public void setCUSTNO(String cUSTNO) {
		CUSTNO = cUSTNO;
	}
	public String getTIPE() {
		return TIPE;
	}
	public void setTIPE(String tIPE) {
		TIPE = tIPE;
	}
	public String getCUSTNAME() {
		return CUSTNAME;
	}
	public void setCUSTNAME(String cUSTNAME) {
		CUSTNAME = cUSTNAME;
	}
	public BigDecimal getROUTE() {
		return ROUTE;
	}
	public void setROUTE(BigDecimal rOUTE) {
		ROUTE = rOUTE;
	}
	public String getHEADER() {
		return HEADER;
	}
	public void setHEADER(String hEADER) {
		HEADER = hEADER;
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
	public String getTGLSYSTEM() {
		return TGLSYSTEM;
	}
	public void setTGLSYSTEM(String tGLSYSTEM) {
		TGLSYSTEM = tGLSYSTEM;
	}
	public String getSLSFROM() {
		return SLSFROM;
	}
	public void setSLSFROM(String sLSFROM) {
		SLSFROM = sLSFROM;
	}
	public String getSLSTO() {
		return SLSTO;
	}
	public void setSLSTO(String sLSTO) {
		SLSTO = sLSTO;
	}
	public String getTGLFROM() {
		return TGLFROM;
	}
	public void setTGLFROM(String tGLFROM) {
		TGLFROM = tGLFROM;
	}
	public String getTGLTO() {
		return TGLTO;
	}
	public void setTGLTO(String tGLTO) {
		TGLTO = tGLTO;
	}
	
	
}
