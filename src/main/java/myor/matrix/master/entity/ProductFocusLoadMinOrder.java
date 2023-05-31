package myor.matrix.master.entity;

import java.util.List;

public class ProductFocusLoadMinOrder {
	private List<ProductFocusMinOrderDto> detail;
	private List<MessageDto> message;
	
	public ProductFocusLoadMinOrder() {
		// TODO Auto-generated constructor stub
	}

	public ProductFocusLoadMinOrder(List<ProductFocusMinOrderDto> detail, List<MessageDto> message) {
		super();
		this.detail = detail;
		this.message = message;
	}

	public List<ProductFocusMinOrderDto> getDetail() {
		return detail;
	}

	public void setDetail(List<ProductFocusMinOrderDto> detail) {
		this.detail = detail;
	}

	public List<MessageDto> getMessage() {
		return message;
	}

	public void setMessage(List<MessageDto> message) {
		this.message = message;
	}
	
	
}
