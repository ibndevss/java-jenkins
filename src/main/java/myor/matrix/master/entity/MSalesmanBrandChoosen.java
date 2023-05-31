package myor.matrix.master.entity;

import java.math.BigDecimal;

public class MSalesmanBrandChoosen {

	private String slsno;
	private BigDecimal nobrs;
	private String data2;
	private String sbra2name;
	private String kode;
	private BigDecimal kategori;
	
	public MSalesmanBrandChoosen() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MSalesmanBrandChoosen(String slsno, BigDecimal nobrs, String data2, String sbra2name, String kode,
			BigDecimal kategori) {
		super();
		this.slsno = slsno;
		this.nobrs = nobrs;
		this.data2 = data2;
		this.sbra2name = sbra2name;
		this.kode = kode;
		this.kategori = kategori;
	}

	public String getSlsno() {
		return slsno;
	}

	public void setSlsno(String slsno) {
		this.slsno = slsno;
	}

	public BigDecimal getNobrs() {
		return nobrs;
	}

	public void setNobrs(BigDecimal nobrs) {
		this.nobrs = nobrs;
	}

	public String getData2() {
		return data2;
	}

	public void setData2(String data2) {
		this.data2 = data2;
	}

	public String getSbra2name() {
		return sbra2name;
	}

	public void setSbra2name(String sbra2name) {
		this.sbra2name = sbra2name;
	}

	public String getKode() {
		return kode;
	}

	public void setKode(String kode) {
		this.kode = kode;
	}

	public BigDecimal getKategori() {
		return kategori;
	}

	public void setKategori(BigDecimal kategori) {
		this.kategori = kategori;
	}
	
}
