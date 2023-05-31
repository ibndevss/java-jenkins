package myor.matrix.master.entity;

public class DriverBrowseDto {
	private String driverCode;
	private String driverName;
	
	public DriverBrowseDto() {
		// TODO Auto-generated constructor stub
	}

	public DriverBrowseDto(String driverCode, String driverName) {
		super();
		this.driverCode = driverCode;
		this.driverName = driverName;
	}

	public String getDriverCode() {
		return driverCode;
	}

	public void setDriverCode(String driverCode) {
		this.driverCode = driverCode;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	
	
}
