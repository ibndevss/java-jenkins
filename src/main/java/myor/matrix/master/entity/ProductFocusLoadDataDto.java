package myor.matrix.master.entity;

import java.util.List;

public class ProductFocusLoadDataDto {
	private ProductFocusHeaderDto headerSFA;
	private List<ProductFocusDetailDto> detailSFA;
	private ProductFocusHeaderDto headerStock;
	private List<ProductFocusDetailStockDto> detailStock;
	private List<ProductFocusMinOrderDto> minOrder;
	
	public ProductFocusLoadDataDto() {
		// TODO Auto-generated constructor stub
	}

	public ProductFocusLoadDataDto(ProductFocusHeaderDto headerSFA, List<ProductFocusDetailDto> detailSFA,
			ProductFocusHeaderDto headerStock, List<ProductFocusDetailStockDto> detailStock,
			List<ProductFocusMinOrderDto> minOrder) {
		super();
		this.headerSFA = headerSFA;
		this.detailSFA = detailSFA;
		this.headerStock = headerStock;
		this.detailStock = detailStock;
		this.minOrder = minOrder;
	}

	public ProductFocusHeaderDto getHeaderSFA() {
		return headerSFA;
	}

	public void setHeaderSFA(ProductFocusHeaderDto headerSFA) {
		this.headerSFA = headerSFA;
	}

	public List<ProductFocusDetailDto> getDetailSFA() {
		return detailSFA;
	}

	public void setDetailSFA(List<ProductFocusDetailDto> detailSFA) {
		this.detailSFA = detailSFA;
	}

	public ProductFocusHeaderDto getHeaderStock() {
		return headerStock;
	}

	public void setHeaderStock(ProductFocusHeaderDto headerStock) {
		this.headerStock = headerStock;
	}

	public List<ProductFocusDetailStockDto> getDetailStock() {
		return detailStock;
	}

	public void setDetailStock(List<ProductFocusDetailStockDto> detailStock) {
		this.detailStock = detailStock;
	}

	public List<ProductFocusMinOrderDto> getMinOrder() {
		return minOrder;
	}

	public void setMinOrder(List<ProductFocusMinOrderDto> minOrder) {
		this.minOrder = minOrder;
	}
	
	
}
