package myor.matrix.master.entity;

public class SupplierDto {

	private String supplier;
	private String supplierName;
	
	public SupplierDto() {
		// TODO Auto-generated constructor stub
	}
	
	public SupplierDto(String supplier, String supplierName) {
		super();
		this.supplier = supplier;
		this.supplierName = supplierName;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	
	
}
