package myor.matrix.master.entity;

import java.util.List;

public class ProductDto {
	
	ProductChoosenDto productChoosen;
	List<GetChannelChoosen> channelChoosen;
	List<GetOutletChoosen> outletChoosen;
	
	public ProductDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductDto(ProductChoosenDto productChoosen, List<GetChannelChoosen> channelChoosen,
			List<GetOutletChoosen> outletChoosen) {
		super();
		this.productChoosen = productChoosen;
		this.channelChoosen = channelChoosen;
		this.outletChoosen = outletChoosen;
	}

	public ProductChoosenDto getProductChoosen() {
		return productChoosen;
	}

	public void setProductChoosen(ProductChoosenDto productChoosen) {
		this.productChoosen = productChoosen;
	}

	public List<GetChannelChoosen> getChannelChoosen() {
		return channelChoosen;
	}

	public void setChannelChoosen(List<GetChannelChoosen> channelChoosen) {
		this.channelChoosen = channelChoosen;
	}

	public List<GetOutletChoosen> getOutletChoosen() {
		return outletChoosen;
	}

	public void setOutletChoosen(List<GetOutletChoosen> outletChoosen) {
		this.outletChoosen = outletChoosen;
	}

	
	
}