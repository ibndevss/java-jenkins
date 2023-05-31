package myor.matrix.master.entity;

public class BrandBrowseDto {
	private String umbrella;
	private String brand;
	private String brandName;
	
	public BrandBrowseDto() {
		// TODO Auto-generated constructor stub
	}

	public BrandBrowseDto(String umbrella, String brand, String brandName) {
		super();
		this.umbrella = umbrella;
		this.brand = brand;
		this.brandName = brandName;
	}

	public String getUmbrella() {
		return umbrella;
	}

	public void setUmbrella(String umbrella) {
		this.umbrella = umbrella;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	
	
}
