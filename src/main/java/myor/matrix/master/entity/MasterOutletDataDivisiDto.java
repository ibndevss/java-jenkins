package myor.matrix.master.entity;

public class MasterOutletDataDivisiDto {

	private String custno;
	private String sbra2;
	private String sbra2name;
	private String flagout;
	private String firstdate;
	public MasterOutletDataDivisiDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MasterOutletDataDivisiDto(String custno, String sbra2, String sbra2name, String flagout, String firstdate) {
		super();
		this.custno = custno;
		this.sbra2 = sbra2;
		this.sbra2name = sbra2name;
		this.flagout = flagout;
		this.firstdate = firstdate;
	}
	public String getCustno() {
		return custno;
	}
	public void setCustno(String custno) {
		this.custno = custno;
	}
	public String getSbra2() {
		return sbra2;
	}
	public void setSbra2(String sbra2) {
		this.sbra2 = sbra2;
	}
	public String getSbra2name() {
		return sbra2name;
	}
	public void setSbra2name(String sbra2name) {
		this.sbra2name = sbra2name;
	}
	public String getFlagout() {
		return flagout;
	}
	public void setFlagout(String flagout) {
		this.flagout = flagout;
	}
	public String getFirstdate() {
		return firstdate;
	}
	public void setFirstdate(String firstdate) {
		this.firstdate = firstdate;
	}
}
