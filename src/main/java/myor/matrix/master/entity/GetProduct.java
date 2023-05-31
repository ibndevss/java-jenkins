package myor.matrix.master.entity;

public class GetProduct {
	
	private String jatah;
	private String pcode;
	private String pcodename;
	private String divisi;
	private String umbrella;
	private String brand;
	private String subbrand;
	private Boolean check;
	private String flagUpdate;
	
	public GetProduct() {
		super();
		// TODO Auto-generated constructor stub
	}
	public GetProduct(String jatah, String pcode, String pcodename, String divisi, String umbrella, String brand,
			String subbrand, Boolean check, String flagUpdate) {
		super();
		this.jatah = jatah;
		this.pcode = pcode;
		this.pcodename = pcodename;
		this.divisi = divisi;
		this.umbrella = umbrella;
		this.brand = brand;
		this.subbrand = subbrand;
		this.check = check;
		this.flagUpdate = flagUpdate;
	}
	public String getJatah() {
		return jatah;
	}
	public void setJatah(String jatah) {
		this.jatah = jatah;
	}
	public String getPcode() {
		return pcode;
	}
	public void setPcode(String pcode) {
		this.pcode = pcode;
	}
	public String getPcodename() {
		return pcodename;
	}
	public void setPcodename(String pcodename) {
		this.pcodename = pcodename;
	}
	public String getDivisi() {
		return divisi;
	}
	public void setDivisi(String divisi) {
		this.divisi = divisi;
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
	public String getSubbrand() {
		return subbrand;
	}
	public void setSubbrand(String subbrand) {
		this.subbrand = subbrand;
	}
	public Boolean getCheck() {
		return check;
	}
	public void setCheck(Boolean check) {
		this.check = check;
	}
	public String getflagUpdate() {
		return flagUpdate;
	}
	public void setflagUpdate(String flagUpdate) {
		this.flagUpdate = flagUpdate;
	}
	
	
		
}
