package myor.matrix.master.entity;

public class DaftarTeamDiskonRegulerDto {
	
	
	private String TGLEFEKTIF;
	private String TEAM;
	private String TEAMNAME;
	private String DIVISI;
	private String DIVNAME;
	private String KODEDIS;
	private String HEADER;
	private String TGLCETAK;
	private String TGLGUDANG;
	private String RELEASE;
	private String INPUTTEAM;
	private String INPUTDATEEFEKTIF;
	public DaftarTeamDiskonRegulerDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DaftarTeamDiskonRegulerDto(String tGLEFEKTIF, String tEAM, String tEAMNAME, String dIVISI, String dIVNAME,
			String kODEDIS, String hEADER, String tGLCETAK, String tGLGUDANG, String rELEASE, String iNPUTTEAM,
			String iNPUTDATEEFEKTIF) {
		super();
		TGLEFEKTIF = tGLEFEKTIF;
		TEAM = tEAM;
		TEAMNAME = tEAMNAME;
		DIVISI = dIVISI;
		DIVNAME = dIVNAME;
		KODEDIS = kODEDIS;
		HEADER = hEADER;
		TGLCETAK = tGLCETAK;
		TGLGUDANG = tGLGUDANG;
		RELEASE = rELEASE;
		INPUTTEAM = iNPUTTEAM;
		INPUTDATEEFEKTIF = iNPUTDATEEFEKTIF;
	}
	public String getTGLEFEKTIF() {
		return TGLEFEKTIF;
	}
	public void setTGLEFEKTIF(String tGLEFEKTIF) {
		TGLEFEKTIF = tGLEFEKTIF;
	}
	public String getTEAM() {
		return TEAM;
	}
	public void setTEAM(String tEAM) {
		TEAM = tEAM;
	}
	public String getTEAMNAME() {
		return TEAMNAME;
	}
	public void setTEAMNAME(String tEAMNAME) {
		TEAMNAME = tEAMNAME;
	}
	public String getDIVISI() {
		return DIVISI;
	}
	public void setDIVISI(String dIVISI) {
		DIVISI = dIVISI;
	}
	public String getDIVNAME() {
		return DIVNAME;
	}
	public void setDIVNAME(String dIVNAME) {
		DIVNAME = dIVNAME;
	}
	public String getKODEDIS() {
		return KODEDIS;
	}
	public void setKODEDIS(String kODEDIS) {
		KODEDIS = kODEDIS;
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
	public String getINPUTTEAM() {
		return INPUTTEAM;
	}
	public void setINPUTTEAM(String iNPUTTEAM) {
		INPUTTEAM = iNPUTTEAM;
	}
	public String getINPUTDATEEFEKTIF() {
		return INPUTDATEEFEKTIF;
	}
	public void setINPUTDATEEFEKTIF(String iNPUTDATEEFEKTIF) {
		INPUTDATEEFEKTIF = iNPUTDATEEFEKTIF;
	}
	
}
