package myor.matrix.master.entity;

public class UploadOutletAktifNonAktifDetailDto {

	private String docno = "";
	private String custno = "";
	private String custname = "";
	private String custaddr = "";
	private String channel = "";
	private String alasan = "";
	private String namatipe = "";
	public UploadOutletAktifNonAktifDetailDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UploadOutletAktifNonAktifDetailDto(String docno, String custno, String custname, String custaddr,
			String channel, String alasan, String namatipe) {
		super();
		this.docno = docno;
		this.custno = custno;
		this.custname = custname;
		this.custaddr = custaddr;
		this.channel = channel;
		this.alasan = alasan;
		this.namatipe = namatipe;
	}
	public String getDocno() {
		return docno;
	}
	public void setDocno(String docno) {
		this.docno = docno;
	}
	public String getCustno() {
		return custno;
	}
	public void setCustno(String custno) {
		this.custno = custno;
	}
	public String getCustname() {
		return custname;
	}
	public void setCustname(String custname) {
		this.custname = custname;
	}
	public String getCustaddr() {
		return custaddr;
	}
	public void setCustaddr(String custaddr) {
		this.custaddr = custaddr;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getAlasan() {
		return alasan;
	}
	public void setAlasan(String alasan) {
		this.alasan = alasan;
	}
	public String getNamatipe() {
		return namatipe;
	}
	public void setNamatipe(String namatipe) {
		this.namatipe = namatipe;
	}
}
