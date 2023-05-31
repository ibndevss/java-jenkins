package myor.matrix.master.entity;

public class DaftarTOPProdukFilterDto {
	private String pilihanData;
	private String pilihanData2;
	private String filterFrom;
	private String filterTo;
	
	public DaftarTOPProdukFilterDto() {
		// TODO Auto-generated constructor stub
	}

	public DaftarTOPProdukFilterDto(String pilihanData, String pilihanData2, String filterFrom, String filterTo) {
		super();
		this.pilihanData = pilihanData;
		this.pilihanData2 = pilihanData2;
		this.filterFrom = filterFrom;
		this.filterTo = filterTo;
	}

	public String getPilihanData() {
		return pilihanData;
	}

	public void setPilihanData(String pilihanData) {
		this.pilihanData = pilihanData;
	}

	public String getPilihanData2() {
		return pilihanData2;
	}

	public void setPilihanData2(String pilihanData2) {
		this.pilihanData2 = pilihanData2;
	}

	public String getFilterFrom() {
		return filterFrom;
	}

	public void setFilterFrom(String filterFrom) {
		this.filterFrom = filterFrom;
	}

	public String getFilterTo() {
		return filterTo;
	}

	public void setFilterTo(String filterTo) {
		this.filterTo = filterTo;
	}
	
	
}
