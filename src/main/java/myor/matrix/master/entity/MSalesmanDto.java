package myor.matrix.master.entity;

import java.util.List;

public class MSalesmanDto {
	
	MSalesmanChoosenDto salesmanChoosen;
	List<MSalesmanBrandChoosen> brandSalesmanChoosen;
	List<MSalesmanProductChoosen> productSalesmanChoosen;
	
	public MSalesmanDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MSalesmanDto(MSalesmanChoosenDto salesmanChoosen, List<MSalesmanBrandChoosen> brandSalesmanChoosen,
			List<MSalesmanProductChoosen> productSalesmanChoosen) {
		super();
		this.salesmanChoosen = salesmanChoosen;
		this.brandSalesmanChoosen = brandSalesmanChoosen;
		this.productSalesmanChoosen = productSalesmanChoosen;
	}

	public MSalesmanChoosenDto getSalesmanChoosen() {
		return salesmanChoosen;
	}

	public void setSalesmanChoosen(MSalesmanChoosenDto salesmanChoosen) {
		this.salesmanChoosen = salesmanChoosen;
	}

	public List<MSalesmanBrandChoosen> getBrandSalesmanChoosen() {
		return brandSalesmanChoosen;
	}

	public void setBrandSalesmanChoosen(List<MSalesmanBrandChoosen> brandSalesmanChoosen) {
		this.brandSalesmanChoosen = brandSalesmanChoosen;
	}

	public List<MSalesmanProductChoosen> getProductSalesmanChoosen() {
		return productSalesmanChoosen;
	}

	public void setProductSalesmanChoosen(List<MSalesmanProductChoosen> productSalesmanChoosen) {
		this.productSalesmanChoosen = productSalesmanChoosen;
	}
	
	
}
