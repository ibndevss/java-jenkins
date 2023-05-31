package myor.matrix.master.entity;

public class DiscApBrowseDto {
	private String kodeAp;
	private String keterangan;
	private String tipe;
	private String tglMulai;
	private String tglAkhir;
	private String hirarki;
	private double id;
	
	public DiscApBrowseDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DiscApBrowseDto(String kodeAp, String keterangan, String tipe, String tglMulai, String tglAkhir,
			String hirarki, double id) {
		super();
		this.kodeAp = kodeAp;
		this.keterangan = keterangan;
		this.tipe = tipe;
		this.tglMulai = tglMulai;
		this.tglAkhir = tglAkhir;
		this.hirarki = hirarki;
		this.id = id;
	}

	public String getKeterangan() {
		return keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

	public String getTipe() {
		return tipe;
	}

	public void setTipe(String tipe) {
		this.tipe = tipe;
	}

	public String getKodeAp() {
		return kodeAp;
	}

	public void setKodeAp(String kodeAp) {
		this.kodeAp = kodeAp;
	}

	public String getTglMulai() {
		return tglMulai;
	}

	public void setTglMulai(String tglMulai) {
		this.tglMulai = tglMulai;
	}

	public String getTglAkhir() {
		return tglAkhir;
	}

	public void setTglAkhir(String tglAkhir) {
		this.tglAkhir = tglAkhir;
	}

	public String getHirarki() {
		return hirarki;
	}

	public void setHirarki(String hirarki) {
		this.hirarki = hirarki;
	}

	public double getId() {
		return id;
	}

	public void setId(double id) {
		this.id = id;
	}
	
}
