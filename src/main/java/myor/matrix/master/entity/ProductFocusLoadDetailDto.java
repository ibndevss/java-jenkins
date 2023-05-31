package myor.matrix.master.entity;

import java.util.List;

public class ProductFocusLoadDetailDto {
	private List<ProductFocusDetailDto> detail;
	private List<MessageDto> message;
	
	public ProductFocusLoadDetailDto() {
		// TODO Auto-generated constructor stub
	}

	public ProductFocusLoadDetailDto(List<ProductFocusDetailDto> detail, List<MessageDto> message) {
		super();
		this.detail = detail;
		this.message = message;
	}

	public List<ProductFocusDetailDto> getDetail() {
		return detail;
	}

	public void setDetail(List<ProductFocusDetailDto> detail) {
		this.detail = detail;
	}

	public List<MessageDto> getMessage() {
		return message;
	}

	public void setMessage(List<MessageDto> message) {
		this.message = message;
	}
	
	
}
