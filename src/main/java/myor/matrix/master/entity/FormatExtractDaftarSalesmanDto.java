package myor.matrix.master.entity;

public class FormatExtractDaftarSalesmanDto {
	private String nomer;
	private String format;

	public FormatExtractDaftarSalesmanDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FormatExtractDaftarSalesmanDto(String nomer, String format) {
		super();
		this.nomer = nomer;
		this.format = format;
	}

	public String getNomer() {
		return nomer;
	}

	public void setNomer(String nomer) {
		this.nomer = nomer;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

}
