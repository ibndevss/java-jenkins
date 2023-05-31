package myor.matrix.master.entity;

public class RecapBrowseDto {
	
	private String recapNo;
	private String recapDate;
	private String driver;
	private String helper;
	public RecapBrowseDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RecapBrowseDto(String recapNo, String recapDate, String driver, String helper) {
		super();
		this.recapNo = recapNo;
		this.recapDate = recapDate;
		this.driver = driver;
		this.helper = helper;
	}
	public String getRecapNo() {
		return recapNo;
	}
	public void setRecapNo(String recapNo) {
		this.recapNo = recapNo;
	}
	public String getRecapDate() {
		return recapDate;
	}
	public void setRecapDate(String recapDate) {
		this.recapDate = recapDate;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getHelper() {
		return helper;
	}
	public void setHelper(String helper) {
		this.helper = helper;
	}
}
