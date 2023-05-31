package myor.matrix.master.entity;

public class ViewValidasiOutletDto {
	String outlet;
	String status;
	String longitude;
	String latitude;
	String provinsi;
	String kabupaten;
	String kecamatan;
	String kelurahan;
	String tglSubmit;
	String userAdj;
	String subdist;
	public ViewValidasiOutletDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ViewValidasiOutletDto(String outlet, String status, String longitude, String latitude, String provinsi,
			String kabupaten, String kecamatan, String kelurahan, String tglSubmit, String userAdj, String subdist) {
		super();
		this.outlet = outlet;
		this.status = status;
		this.longitude = longitude;
		this.latitude = latitude;
		this.provinsi = provinsi;
		this.kabupaten = kabupaten;
		this.kecamatan = kecamatan;
		this.kelurahan = kelurahan;
		this.tglSubmit = tglSubmit;
		this.userAdj = userAdj;
		this.subdist = subdist;
	}
	public String getOutlet() {
		return outlet;
	}
	public void setOutlet(String outlet) {
		this.outlet = outlet;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
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
	public String getTglSubmit() {
		return tglSubmit;
	}
	public void setTglSubmit(String tglSubmit) {
		this.tglSubmit = tglSubmit;
	}
	public String getUserAdj() {
		return userAdj;
	}
	public void setUserAdj(String userAdj) {
		this.userAdj = userAdj;
	}
	public String getSubdist() {
		return subdist;
	}
	public void setSubdist(String subdist) {
		this.subdist = subdist;
	}
	
}
