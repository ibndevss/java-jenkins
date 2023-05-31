package myor.matrix.master.entity;

import java.math.BigDecimal;

public class ViewTopDto {
	private String data1;
	private String berlaku;
	private String product;
	private String nmberlaku;
	private String data3;
	private String tipe;
	private String kode;
	private String nama;
	private String tglBerlaku;
	private String tglBerakhir;
	private String tipeBerlaku;
	private int totalDay;
	private String fixDate;
	
	public ViewTopDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ViewTopDto(String data1, String berlaku, String product, String nmberlaku, String data3, String tipe,
			String kode, String nama, String tglBerlaku, String tglBerakhir, String tipeBerlaku, int totalDay,
			String fixDate) {
		super();
		this.data1 = data1;
		this.berlaku = berlaku;
		this.product = product;
		this.nmberlaku = nmberlaku;
		this.data3 = data3;
		this.tipe = tipe;
		this.kode = kode;
		this.nama = nama;
		this.tglBerlaku = tglBerlaku;
		this.tglBerakhir = tglBerakhir;
		this.tipeBerlaku = tipeBerlaku;
		this.totalDay = totalDay;
		this.fixDate = fixDate;
	}

	public String getData1() {
		return data1;
	}

	public void setData1(String data1) {
		this.data1 = data1;
	}

	public String getBerlaku() {
		return berlaku;
	}

	public void setBerlaku(String berlaku) {
		this.berlaku = berlaku;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getNmberlaku() {
		return nmberlaku;
	}

	public void setNmberlaku(String nmberlaku) {
		this.nmberlaku = nmberlaku;
	}

	public String getData3() {
		return data3;
	}

	public void setData3(String data3) {
		this.data3 = data3;
	}

	public String getTipe() {
		return tipe;
	}

	public void setTipe(String tipe) {
		this.tipe = tipe;
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

	public String getTglBerlaku() {
		return tglBerlaku;
	}

	public void setTglBerlaku(String tglBerlaku) {
		this.tglBerlaku = tglBerlaku;
	}

	public String getTglBerakhir() {
		return tglBerakhir;
	}

	public void setTglBerakhir(String tglBerakhir) {
		this.tglBerakhir = tglBerakhir;
	}

	public String getTipeBerlaku() {
		return tipeBerlaku;
	}

	public void setTipeBerlaku(String tipeBerlaku) {
		this.tipeBerlaku = tipeBerlaku;
	}

	public int getTotalDay() {
		return totalDay;
	}

	public void setTotalDay(int totalDay) {
		this.totalDay = totalDay;
	}

	public String getFixDate() {
		return fixDate;
	}

	public void setFixDate(String fixDate) {
		this.fixDate = fixDate;
	}
	
}
