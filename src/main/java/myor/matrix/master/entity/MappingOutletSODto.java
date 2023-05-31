package myor.matrix.master.entity;

public class MappingOutletSODto {
	
	private String custNoSO;
	private String custNameSO;
	private String addressSO;
	private String custNo;
	private String custName;
	private String address;
	private String tglUpd;
	
	public MappingOutletSODto() {
		// TODO Auto-generated constructor stub
	}

	public MappingOutletSODto(String custNoSO, String custNameSO, String addressSO, String custNo, String custName,
			String address, String tglUpd) {
		super();
		this.custNoSO = custNoSO;
		this.custNameSO = custNameSO;
		this.addressSO = addressSO;
		this.custNo = custNo;
		this.custName = custName;
		this.address = address;
		this.tglUpd = tglUpd;
	}

	public String getCustNoSO() {
		return custNoSO;
	}

	public void setCustNoSO(String custNoSO) {
		this.custNoSO = custNoSO;
	}

	public String getCustNameSO() {
		return custNameSO;
	}

	public void setCustNameSO(String custNameSO) {
		this.custNameSO = custNameSO;
	}

	public String getAddressSO() {
		return addressSO;
	}

	public void setAddressSO(String addressSO) {
		this.addressSO = addressSO;
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
