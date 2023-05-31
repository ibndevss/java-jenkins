package myor.matrix.master.entity;

public class MSalesmanGudang {
	
	private String kg;
	private String kgname;
	private String upddate;
	public MSalesmanGudang() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MSalesmanGudang(String kg, String kgname, String upddate) {
		super();
		this.kg = kg;
		this.kgname = kgname;
		this.upddate = upddate;
	}
	public String getKg() {
		return kg;
	}
	public void setKg(String kg) {
		this.kg = kg;
	}
	public String getKgname() {
		return kgname;
	}
	public void setKgname(String kgname) {
		this.kgname = kgname;
	}
	public String getUpddate() {
		return upddate;
	}
	public void setUpddate(String upddate) {
		this.upddate = upddate;
	}
	
}
