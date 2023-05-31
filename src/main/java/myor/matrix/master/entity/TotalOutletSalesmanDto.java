package myor.matrix.master.entity;

import java.math.BigDecimal;

public class TotalOutletSalesmanDto {
	String kode;
	String nama;
	BigDecimal jml;
	public TotalOutletSalesmanDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public TotalOutletSalesmanDto(String kode, String nama, BigDecimal jml) {
		super();
		this.kode = kode;
		this.nama = nama;
		this.jml = jml;
	}

	public String getKode() {
		return kode;
	}
	public void setKode(String kode) {
		this.kode = kode;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	public BigDecimal getJml() {
		return jml;
	}
	public void setJml(BigDecimal jml) {
		this.jml = jml;
	}
	
}
