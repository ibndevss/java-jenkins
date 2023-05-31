package myor.matrix.master.entity;

public class GetOutletChoosen {
	
	private String subdist_id;
	private String custno;
	private String custname;
	public GetOutletChoosen() {
		super();
		// TODO Auto-generated constructor stub
	}
	public GetOutletChoosen(String subdist_id, String custno, String custname) {
		super();
		this.subdist_id = subdist_id;
		this.custno = custno;
		this.custname = custname;
	}
	public String getSubdist_id() {
		return subdist_id;
	}
	public void setSubdist_id(String subdist_id) {
		this.subdist_id = subdist_id;
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
	
	
}
