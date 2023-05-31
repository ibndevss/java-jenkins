package myor.matrix.master.entity;

public class LaporanDiskonBrowseAPDto {
	private String kodeAP;
	private String keterangan;
	private String tglAwal;
	private String tglAkhir;
	
	public LaporanDiskonBrowseAPDto() {
		// TODO Auto-generated constructor stub
	}

	public LaporanDiskonBrowseAPDto(String kodeAP, String keterangan, String tglAwal, String tglAkhir) {
		super();
		this.kodeAP = kodeAP;
		this.keterangan = keterangan;
		this.tglAwal = tglAwal;
		this.tglAkhir = tglAkhir;
	}

	public String getKodeAP() {
		return kodeAP;
	}

	public void setKodeAP(String kodeAP) {
		this.kodeAP = kodeAP;
	}

	public String getKeterangan() {
		return keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

	public String getTglAwal() {
		return tglAwal;
	}

	public void setTglAwal(String tglAwal) {
		this.tglAwal = tglAwal;
	}

	public String getTglAkhir() {
		return tglAkhir;
	}

	public void setTglAkhir(String tglAkhir) {
		this.tglAkhir = tglAkhir;
	}
}
