package myor.matrix.master.entity;

import java.util.List;

public class ProductFocusLoadDetailStockDto {
	
	private List<ProductFocusDetailStockDto> detail;
	private List<MessageDto> message;
	
	public ProductFocusLoadDetailStockDto() {
		// TODO Auto-generated constructor stub
	}

	public ProductFocusLoadDetailStockDto(List<ProductFocusDetailStockDto> detail, List<MessageDto> message) {
		super();
		this.detail = detail;
		this.message = message;
	}

	public List<ProductFocusDetailStockDto> getDetail() {
		return detail;
	}

	public void setDetail(List<ProductFocusDetailStockDto> detail) {
		this.detail = detail;
	}

	public List<MessageDto> getMessage() {
		return message;
	}

	public void setMessage(List<MessageDto> message) {
		this.message = message;
	}
}
