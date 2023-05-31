package myor.matrix.master.entity;

public class RealOrderBrowseDto {

	String orderNo;
	String slsNo;
	String custNo;
	String custName;
	String orderDate;
	String orderNo2;

	public RealOrderBrowseDto() {
		// TODO Auto-generated constructor stub
	}

	public RealOrderBrowseDto(String orderNo, String slsNo, String custNo, String custName, String orderDate,
			String orderNo2) {
		super();
		this.orderNo = orderNo;
		this.slsNo = slsNo;
		this.custNo = custNo;
		this.custName = custName;
		this.orderDate = orderDate;
		this.orderNo2 = orderNo2;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getSlsNo() {
		return slsNo;
	}

	public void setSlsNo(String slsNo) {
		this.slsNo = slsNo;
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

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderNo2() {
		return orderNo2;
	}

	public void setOrderNo2(String orderNo2) {
		this.orderNo2 = orderNo2;
	}

	

}
