package myor.matrix.master.entity;

public class OutletRuteDto {
	String SLSNO;
	String SLSNAME;
	String CUSTNO;
	String CUSTNAME;
	String CUSTADD1;
	String CLIMIT;
	String SBEAT;
	String HARI;
	String POLA;
	String RUTE;
	public OutletRuteDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OutletRuteDto(String sLSNO, String sLSNAME, String cUSTNO, String cUSTNAME, String cUSTADD1, String cLIMIT,
			String sBEAT, String hARI, String pOLA, String rUTE) {
		super();
		SLSNO = sLSNO;
		SLSNAME = sLSNAME;
		CUSTNO = cUSTNO;
		CUSTNAME = cUSTNAME;
		CUSTADD1 = cUSTADD1;
		CLIMIT = cLIMIT;
		SBEAT = sBEAT;
		HARI = hARI;
		POLA = pOLA;
		RUTE = rUTE;
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
	public String getCUSTADD1() {
		return CUSTADD1;
	}
	public void setCUSTADD1(String cUSTADD1) {
		CUSTADD1 = cUSTADD1;
	}
	public String getCLIMIT() {
		return CLIMIT;
	}
	public void setCLIMIT(String cLIMIT) {
		CLIMIT = cLIMIT;
	}
	public String getSBEAT() {
		return SBEAT;
	}
	public void setSBEAT(String sBEAT) {
		SBEAT = sBEAT;
	}
	public String getHARI() {
		return HARI;
	}
	public void setHARI(String hARI) {
		HARI = hARI;
	}
	public String getPOLA() {
		return POLA;
	}
	public void setPOLA(String pOLA) {
		POLA = pOLA;
	}
	public String getRUTE() {
		return RUTE;
	}
	public void setRUTE(String rUTE) {
		RUTE = rUTE;
	}
	
}
