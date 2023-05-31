package myor.matrix.master.entity;

public class CustomerBrowseDto {
	private String custNo;
	private String custName;
	private String custAdd;
	private String channel;
	private String flagOut;
	private String flagPay;
	private boolean custIsShipTo;
	private boolean custIsAp;
	private String custNoSO;
	private String custNoAP;
	private String city;
	private String contactPerson;
	private String phone;
	private String faxNo;
	private String address2;

	public CustomerBrowseDto() {
		// TODO Auto-generated constructor stub
	}
	
	public CustomerBrowseDto(String custNo, String custName, String custAdd, String channel) {
		super();
		this.custNo = custNo;
		this.custName = custName;
		this.custAdd = custAdd;
		this.channel = channel;
	}

	public CustomerBrowseDto(String custNo, String custName, String custAdd, String channel, String flagOut) {
		super();
		this.custNo = custNo;
		this.custName = custName;
		this.custAdd = custAdd;
		this.channel = channel;
		this.flagOut = flagOut;
	}

	public CustomerBrowseDto(String custNo, String custName, String custAdd, String channel, String flagOut,
			String flagPay, boolean custIsShipTo, boolean custIsAp) {
		super();
		this.custNo = custNo;
		this.custName = custName;
		this.custAdd = custAdd;
		this.channel = channel;
		this.flagOut = flagOut;
		this.flagPay = flagPay;
		this.custIsShipTo = custIsShipTo;
		this.custIsAp = custIsAp;
	}

	public CustomerBrowseDto(String custNo, String custName, String custAdd, String city, String contactPerson,
			String phone, String faxNo) {
		super();
		this.custNo = custNo;
		this.custName = custName;
		this.custAdd = custAdd;
		this.city = city;
		this.contactPerson = contactPerson;
		this.phone = phone;
		this.faxNo = faxNo;
	}

	public String getCustNo() {
		return custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCustAdd() {
		return custAdd;
	}

	public void setCustAdd(String custAdd) {
		this.custAdd = custAdd;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getFlagOut() {
		return flagOut;
	}

	public void setFlagOut(String flagOut) {
		this.flagOut = flagOut;
	}

	public String getFlagPay() {
		return flagPay;
	}

	public void setFlagPay(String flagPay) {
		this.flagPay = flagPay;
	}

	public boolean isCustIsShipTo() {
		return custIsShipTo;
	}

	public void setCustIsShipTo(boolean custIsShipTo) {
		this.custIsShipTo = custIsShipTo;
	}

	public boolean isCustIsAp() {
		return custIsAp;
	}

	public void setCustIsAp(boolean custIsAp) {
		this.custIsAp = custIsAp;
	}

	public String getCustNoSO() {
		return custNoSO;
	}

	public void setCustNoSO(String custNoSO) {
		this.custNoSO = custNoSO;
	}

	public String getCustNoAP() {
		return custNoAP;
	}

	public void setCustNoAP(String custNoAP) {
		this.custNoAP = custNoAP;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFaxNo() {
		return faxNo;
	}

	public void setFaxNo(String faxNo) {
		this.faxNo = faxNo;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

}
