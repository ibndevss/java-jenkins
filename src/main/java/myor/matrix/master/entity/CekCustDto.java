package myor.matrix.master.entity;

public class CekCustDto {

	private String custNo;
	private String custName;
	private String address1;
	private String address2;
	private String contact;
	private String phone;
	private String fax;
	
	
	public CekCustDto() {	
		super();
		// TODO Auto-generated constructor stub
	}


	public CekCustDto(String custNo, String custName, String address1, String address2, String contact, String phone,
			String fax) {
		super();
		this.custNo = custNo;
		this.custName = custName;
		this.address1 = address1;
		this.address2 = address2;
		this.contact = contact;
		this.phone = phone;
		this.fax = fax;
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


	public String getAddress1() {
		return address1;
	}


	public void setAddress1(String address1) {
		this.address1 = address1;
	}


	public String getAddress2() {
		return address2;
	}


	public void setAddress2(String address2) {
		this.address2 = address2;
	}


	public String getContact() {
		return contact;
	}


	public void setContact(String contact) {
		this.contact = contact;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getFax() {
		return fax;
	}


	public void setFax(String fax) {
		this.fax = fax;
	}
	
	
}
