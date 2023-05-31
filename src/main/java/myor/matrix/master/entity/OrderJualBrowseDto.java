package myor.matrix.master.entity;

public class OrderJualBrowseDto {
	private String orderNo;
	private String orderDate;
	private String slsNo;
	private String custNo;
	private String transType;
	
	public OrderJualBrowseDto() {
		// TODO Auto-generated constructor stub
	}

	public OrderJualBrowseDto(String orderNo, String orderDate, String slsNo, String custNo, String transType) {
		super();
		this.orderNo = orderNo;
		this.orderDate = orderDate;
		this.slsNo = slsNo;
		this.custNo = custNo;
		this.transType = transType;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
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

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}
	
	
}
