package myor.matrix.master.entity;

import java.util.List;

public class ProductFocusUploadSFADto {
	private ProductFocusHeaderDto header;
	private List<ProductFocusDetailDto> detail;
	private List<MessageDto> message;
	
	public ProductFocusUploadSFADto() {
		// TODO Auto-generated constructor stub
	}

	public ProductFocusUploadSFADto(ProductFocusHeaderDto header, List<ProductFocusDetailDto> detail,
			List<MessageDto> message) {
		super();
		this.header = header;
		this.detail = detail;
		this.message = message;
	}

	public ProductFocusHeaderDto getHeader() {
		return header;
	}

	public void setHeader(ProductFocusHeaderDto header) {
		this.header = header;
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
