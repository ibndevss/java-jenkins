package myor.matrix.master.entity;

public class ViewHargaSpesifikDto {
	private String pcode;
	private String pcodeName;
	private String berlaku;
	private String kode;
	private String kodeName;
	private String tglAwal;
	private String tglAkhir;
	private int toleransi;
	private int hargaJualBesar;
	private int hargaJualTengah;
	private int hargaJualKecil;
	
	public ViewHargaSpesifikDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ViewHargaSpesifikDto(String pcode, String pcodeName, String berlaku, String kode, String kodeName,
			String tglAwal, String tglAkhir, int toleransi, int hargaJualBesar, int hargaJualTengah,
			int hargaJualKecil) {
		super();
		this.pcode = pcode;
		this.pcodeName = pcodeName;
		this.berlaku = berlaku;
		this.kode = kode;
		this.kodeName = kodeName;
		this.tglAwal = tglAwal;
		this.tglAkhir = tglAkhir;
		this.toleransi = toleransi;
		this.hargaJualBesar = hargaJualBesar;
		this.hargaJualTengah = hargaJualTengah;
		this.hargaJualKecil = hargaJualKecil;
	}

	public String getPcode() {
		return pcode;
	}

	public void setPcode(String pcode) {
		this.pcode = pcode;
	}

	public String getPcodeName() {
		return pcodeName;
	}

	public void setPcodeName(String pcodeName) {
		this.pcodeName = pcodeName;
	}

	public String getBerlaku() {
		return berlaku;
	}

	public void setBerlaku(String berlaku) {
		this.berlaku = berlaku;
	}

	public String getKode() {
		return kode;
	}

	public void setKode(String kode) {
		this.kode = kode;
	}

	public String getKodeName() {
		return kodeName;
	}

	public void setKodeName(String kodeName) {
		this.kodeName = kodeName;
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

	public int getToleransi() {
		return toleransi;
	}

	public void setToleransi(int toleransi) {
		this.toleransi = toleransi;
	}

	public int getHargaJualBesar() {
		return hargaJualBesar;
	}

	public void setHargaJualBesar(int hargaJualBesar) {
		this.hargaJualBesar = hargaJualBesar;
	}

	public int getHargaJualTengah() {
		return hargaJualTengah;
	}

	public void setHargaJualTengah(int hargaJualTengah) {
		this.hargaJualTengah = hargaJualTengah;
	}

	public int getHargaJualKecil() {
		return hargaJualKecil;
	}

	public void setHargaJualKecil(int hargaJualKecil) {
		this.hargaJualKecil = hargaJualKecil;
	}

}
