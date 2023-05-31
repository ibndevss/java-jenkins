package myor.matrix.master.entity;

public class MappingOutletPrincipalDto {
	
	private String custNoAP;
	private String custNameAP;
	private String addressAP;
	private String custNo;
	private String custName;
	private String address;
	private String tglUpd;
	
	public MappingOutletPrincipalDto() {
		// TODO Auto-generated constructor stub
	}

	public MappingOutletPrincipalDto(String custNoAP, String custNameAP, String addressAP, String custNo,
			String custName, String address, String tglUpd) {
		super();
		this.custNoAP = custNoAP;
		this.custNameAP = custNameAP;
		this.addressAP = addressAP;
		this.custNo = custNo;
		this.custName = custName;
		this.address = address;
		this.tglUpd = tglUpd;
	}

	public String getCustNoAP() {
		return custNoAP;
	}

	public void setCustNoAP(String custNoAP) {
		this.custNoAP = custNoAP;
	}

	public String getCustNameAP() {
		return custNameAP;
	}

	public void setCustNameAP(String custNameAP) {
		this.custNameAP = custNameAP;
	}

	public String getAddressAP() {
		return addressAP;
	}

	public void setAddressAP(String addressAP) {
		this.addressAP = addressAP;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTglUpd() {
		return tglUpd;
	}

	public void setTglUpd(String tglUpd) {
		this.tglUpd = tglUpd;
	}
	
	

}
