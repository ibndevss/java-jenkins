package myor.matrix.master.entity;

public class CreateKplListOutletDto {

	private String custNo;
	private String custName;
	private String address;
	private String slsNo;
	private String hSenin;
	private String hSelasa;
	private String hRabu;
	private String hKamis;
	private String hJumat;
	private String hSabtu;
	private String hMinggu;
	private String pola_k;
	private String tglGudangNew;
	public CreateKplListOutletDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CreateKplListOutletDto(String custNo, String custName, String address, String slsNo, String hSenin,
			String hSelasa, String hRabu, String hKamis, String hJumat, String hSabtu, String hMinggu, String pola_k,
			String tglGudangNew) {
		super();
		this.custNo = custNo;
		this.custName = custName;
		this.address = address;
		this.slsNo = slsNo;
		this.hSenin = hSenin;
		this.hSelasa = hSelasa;
		this.hRabu = hRabu;
		this.hKamis = hKamis;
		this.hJumat = hJumat;
		this.hSabtu = hSabtu;
		this.hMinggu = hMinggu;
		this.pola_k = pola_k;
		this.tglGudangNew = tglGudangNew;
	}
	public String getCustNo() {
		return custNo;
	}
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getSlsNo() {
		return slsNo;
	}
	public void setSlsNo(String slsNo) {
		this.slsNo = slsNo;
	}
	public String gethSenin() {
		return hSenin;
	}
	public void sethSenin(String hSenin) {
		this.hSenin = hSenin;
	}
	public String gethSelasa() {
		return hSelasa;
	}
	public void sethSelasa(String hSelasa) {
		this.hSelasa = hSelasa;
	}
	public String gethRabu() {
		return hRabu;
	}
	public void sethRabu(String hRabu) {
		this.hRabu = hRabu;
	}
	public String gethKamis() {
		return hKamis;
	}
	public void sethKamis(String hKamis) {
		this.hKamis = hKamis;
	}
	public String gethJumat() {
		return hJumat;
	}
	public void sethJumat(String hJumat) {
		this.hJumat = hJumat;
	}
	public String gethSabtu() {
		return hSabtu;
	}
	public void sethSabtu(String hSabtu) {
		this.hSabtu = hSabtu;
	}
	public String gethMinggu() {
		return hMinggu;
	}
	public void sethMinggu(String hMinggu) {
		this.hMinggu = hMinggu;
	}
	public String getPola_k() {
		return pola_k;
	}
	public void setPola_k(String pola_k) {
		this.pola_k = pola_k;
	}
	public String getTglGudangNew() {
		return tglGudangNew;
	}
	public void setTglGudangNew(String tglGudangNew) {
		this.tglGudangNew = tglGudangNew;
	}
}
