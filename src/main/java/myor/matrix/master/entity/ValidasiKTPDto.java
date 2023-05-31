package myor.matrix.master.entity;

import java.util.List;

public class ValidasiKTPDto {
	String custno;
	String outlet;
	String groupPayer;
	String groupPayerId;
	String alamat;
	String provinsi;
	String kabupaten;
	String kecamatan;
	String kelurahan;
	String tglVal;
	String salesman;
	String status;
	String namaPemilik;
	String tlp;
	String nik;
	String namaKtp;
	String alamatKtp;
	String alamatKtp2;
	String npwp;
	String namaNpwp;
	String alamatNpwp;
	String alamatNpwp2;
	String photoKtp;
	String photoNpwp;
	List<ValidasiKtpOutletGroupPayerDto> detailOutlet;
	String path;
	public ValidasiKTPDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ValidasiKTPDto(String custno, String outlet, String groupPayer, String groupPayerId, String alamat,
			String provinsi, String kabupaten, String kecamatan, String kelurahan, String tglVal, String salesman,
			String status, String namaPemilik, String tlp, String nik, String namaKtp, String alamatKtp,
			String alamatKtp2, String npwp, String namaNpwp, String alamatNpwp, String alamatNpwp2, String photoKtp,
			String photoNpwp) {
		super();
		this.custno = custno;
		this.outlet = outlet;
		this.groupPayer = groupPayer;
		this.groupPayerId = groupPayerId;
		this.alamat = alamat;
		this.provinsi = provinsi;
		this.kabupaten = kabupaten;
		this.kecamatan = kecamatan;
		this.kelurahan = kelurahan;
		this.tglVal = tglVal;
		this.salesman = salesman;
		this.status = status;
		this.namaPemilik = namaPemilik;
		this.tlp = tlp;
		this.nik = nik;
		this.namaKtp = namaKtp;
		this.alamatKtp = alamatKtp;
		this.alamatKtp2 = alamatKtp2;
		this.npwp = npwp;
		this.namaNpwp = namaNpwp;
		this.alamatNpwp = alamatNpwp;
		this.alamatNpwp2 = alamatNpwp2;
		this.photoKtp = photoKtp;
		this.photoNpwp = photoNpwp;
	}
	public String getCustno() {
		return custno;
	}
	public void setCustno(String custno) {
		this.custno = custno;
	}
	public String getOutlet() {
		return outlet;
	}
	public void setOutlet(String outlet) {
		this.outlet = outlet;
	}
	public String getGroupPayer() {
		return groupPayer;
	}
	public void setGroupPayer(String groupPayer) {
		this.groupPayer = groupPayer;
	}
	public String getGroupPayerId() {
		return groupPayerId;
	}
	public void setGroupPayerId(String groupPayerId) {
		this.groupPayerId = groupPayerId;
	}
	public String getAlamat() {
		return alamat;
	}
	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}
	public String getProvinsi() {
		return provinsi;
	}
	public void setProvinsi(String provinsi) {
		this.provinsi = provinsi;
	}
	public String getKabupaten() {
		return kabupaten;
	}
	public void setKabupaten(String kabupaten) {
		this.kabupaten = kabupaten;
	}
	public String getKecamatan() {
		return kecamatan;
	}
	public void setKecamatan(String kecamatan) {
		this.kecamatan = kecamatan;
	}
	public String getKelurahan() {
		return kelurahan;
	}
	public void setKelurahan(String kelurahan) {
		this.kelurahan = kelurahan;
	}
	public String getTglVal() {
		return tglVal;
	}
	public void setTglVal(String tglVal) {
		this.tglVal = tglVal;
	}
	public String getSalesman() {
		return salesman;
	}
	public void setSalesman(String salesman) {
		this.salesman = salesman;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getNamaPemilik() {
		return namaPemilik;
	}
	public void setNamaPemilik(String namaPemilik) {
		this.namaPemilik = namaPemilik;
	}
	public String getTlp() {
		return tlp;
	}
	public void setTlp(String tlp) {
		this.tlp = tlp;
	}
	public String getNik() {
		return nik;
	}
	public void setNik(String nik) {
		this.nik = nik;
	}
	public String getNamaKtp() {
		return namaKtp;
	}
	public void setNamaKtp(String namaKtp) {
		this.namaKtp = namaKtp;
	}
	public String getAlamatKtp() {
		return alamatKtp;
	}
	public void setAlamatKtp(String alamatKtp) {
		this.alamatKtp = alamatKtp;
	}
	public String getAlamatKtp2() {
		return alamatKtp2;
	}
	public void setAlamatKtp2(String alamatKtp2) {
		this.alamatKtp2 = alamatKtp2;
	}
	public String getNpwp() {
		return npwp;
	}
	public void setNpwp(String npwp) {
		this.npwp = npwp;
	}
	public String getNamaNpwp() {
		return namaNpwp;
	}
	public void setNamaNpwp(String namaNpwp) {
		this.namaNpwp = namaNpwp;
	}
	public String getAlamatNpwp() {
		return alamatNpwp;
	}
	public void setAlamatNpwp(String alamatNpwp) {
		this.alamatNpwp = alamatNpwp;
	}
	public String getAlamatNpwp2() {
		return alamatNpwp2;
	}
	public void setAlamatNpwp2(String alamatNpwp2) {
		this.alamatNpwp2 = alamatNpwp2;
	}
	public String getPhotoKtp() {
		return photoKtp;
	}
	public void setPhotoKtp(String photoKtp) {
		this.photoKtp = photoKtp;
	}
	public String getPhotoNpwp() {
		return photoNpwp;
	}
	public void setPhotoNpwp(String photoNpwp) {
		this.photoNpwp = photoNpwp;
	}
	public List<ValidasiKtpOutletGroupPayerDto> getDetailOutlet() {
		return detailOutlet;
	}
	public void setDetailOutlet(List<ValidasiKtpOutletGroupPayerDto> detailOutlet) {
		this.detailOutlet = detailOutlet;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
}
