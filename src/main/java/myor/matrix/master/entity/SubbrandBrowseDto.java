package myor.matrix.master.entity;

public class SubbrandBrowseDto {
	private String umbrella;
	private String brand;
	private String subBrand;
	private String subBrandName;
	
	public SubbrandBrowseDto() {
		// TODO Auto-generated constructor stub
	}

	public SubbrandBrowseDto(String umbrella, String brand, String subBrand, String subBrandName) {
		super();
		this.umbrella = umbrella;
		this.brand = brand;
		this.subBrand = subBrand;
		this.subBrandName = subBrandName;
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

	public String getSubBrand() {
		return subBrand;
	}

	public void setSubBrand(String subBrand) {
		this.subBrand = subBrand;
	}

	public String getSubBrandName() {
		return subBrandName;
	}

	public void setSubBrandName(String subBrandName) {
		this.subBrandName = subBrandName;
	}
	
	
}
