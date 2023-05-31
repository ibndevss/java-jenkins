package myor.matrix.master.entity;

public class UploadOutletAktifNonAktifHeaderDto {

	private String docno = "";
	private String docdate = "";	
	private String tipe = "";	
	private String usercreated = "";	
	private String xkey = "";	
	private String keyauthentication = "";	
	private String userauthentication = "";	
	private String dateproses = "";
	public UploadOutletAktifNonAktifHeaderDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UploadOutletAktifNonAktifHeaderDto(String docno, String docdate, String tipe, String usercreated,
			String xkey, String keyauthentication, String userauthentication, String dateproses) {
		super();
		this.docno = docno;
		this.docdate = docdate;
		this.tipe = tipe;
		this.usercreated = usercreated;
		this.xkey = xkey;
		this.keyauthentication = keyauthentication;
		this.userauthentication = userauthentication;
		this.dateproses = dateproses;
	}
	public String getDocno() {
		return docno;
	}
	public void setDocno(String docno) {
		this.docno = docno;
	}
	public String getDocdate() {
		return docdate;
	}
	public void setDocdate(String docdate) {
		this.docdate = docdate;
	}
	public String getTipe() {
		return tipe;
	}
	public void setTipe(String tipe) {
		this.tipe = tipe;
	}
	public String getUsercreated() {
		return usercreated;
	}
	public void setUsercreated(String usercreated) {
		this.usercreated = usercreated;
	}
	public String getXkey() {
		return xkey;
	}
	public void setXkey(String xkey) {
		this.xkey = xkey;
	}
	public String getKeyauthentication() {
		return keyauthentication;
	}
	public void setKeyauthentication(String keyauthentication) {
		this.keyauthentication = keyauthentication;
	}
	public String getUserauthentication() {
		return userauthentication;
	}
	public void setUserauthentication(String userauthentication) {
		this.userauthentication = userauthentication;
	}
	public String getDateproses() {
		return dateproses;
	}
	public void setDateproses(String dateproses) {
		this.dateproses = dateproses;
	}
}
