package myor.matrix.master.entity;

public class DetailDate {
	
	private String cDate;
	private String tahun;
	private String prdNo;
	private String weekNo;
	private String workFlag;
	public DetailDate() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DetailDate(String cDate, String tahun, String prdNo, String weekNo, String workFlag) {
		super();
		this.cDate = cDate;
		this.tahun = tahun;
		this.prdNo = prdNo;
		this.weekNo = weekNo;
		this.workFlag = workFlag;
	}
	public String getcDate() {
		return cDate;
	}
	public void setcDate(String cDate) {
		this.cDate = cDate;
	}
	public String getTahun() {
		return tahun;
	}
	public void setTahun(String tahun) {
		this.tahun = tahun;
	}
	public String getPrdNo() {
		return prdNo;
	}
	public void setPrdNo(String prdNo) {
		this.prdNo = prdNo;
	}
	public String getWeekNo() {
		return weekNo;
	}
	public void setWeekNo(String weekNo) {
		this.weekNo = weekNo;
	}
	public String getWorkFlag() {
		return workFlag;
	}
	public void setWorkFlag(String workFlag) {
		this.workFlag = workFlag;
	}
}
