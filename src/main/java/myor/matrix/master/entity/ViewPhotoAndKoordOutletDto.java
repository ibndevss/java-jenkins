package myor.matrix.master.entity;

public class ViewPhotoAndKoordOutletDto {
	String custno;
	String updDate;
	String userApproved;
	String flagApproved;
	String custName;
	String custAdd1;
	String dateR;
	String registrationDate;
	String address;
	String latitude;
	String longitude;
	String slsno;
	String slsName;
	String pictureName;
	String path;
	
	public ViewPhotoAndKoordOutletDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ViewPhotoAndKoordOutletDto(String custno, String updDate, String userApproved, String flagApproved,
			String custName, String custAdd1, String dateR, String registrationDate, String address, String latitude,
			String longitude, String slsno, String slsName, String pictureName, String path) {
		super();
		this.custno = custno;
		this.updDate = updDate;
		this.userApproved = userApproved;
		this.flagApproved = flagApproved;
		this.custName = custName;
		this.custAdd1 = custAdd1;
		this.dateR = dateR;
		this.registrationDate = registrationDate;
		this.address = address;
		this.latitude = latitude;
		this.longitude = longitude;
		this.slsno = slsno;
		this.slsName = slsName;
		this.pictureName = pictureName;
		this.path = path;
	}

	public String getCustno() {
		return custno;
	}
	public void setCustno(String custno) {
		this.custno = custno;
	}
	public String getUpdDate() {
		return updDate;
	}
	public void setUpdDate(String updDate) {
		this.updDate = updDate;
	}
	public String getUserApproved() {
		return userApproved;
	}
	public void setUserApproved(String userApproved) {
		this.userApproved = userApproved;
	}
	public String getFlagApproved() {
		return flagApproved;
	}
	public void setFlagApproved(String flagApproved) {
		this.flagApproved = flagApproved;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCustAdd1() {
		return custAdd1;
	}
	public void setCustAdd1(String custAdd1) {
		this.custAdd1 = custAdd1;
	}
	public String getDateR() {
		return dateR;
	}
	public void setDateR(String dateR) {
		this.dateR = dateR;
	}
	public String getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getSlsno() {
		return slsno;
	}
	public void setSlsno(String slsno) {
		this.slsno = slsno;
	}
	public String getSlsName() {
		return slsName;
	}
	public void setSlsName(String slsName) {
		this.slsName = slsName;
	}
	public String getPictureName() {
		return pictureName;
	}
	public void setPictureName(String pictureName) {
		this.pictureName = pictureName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
}
