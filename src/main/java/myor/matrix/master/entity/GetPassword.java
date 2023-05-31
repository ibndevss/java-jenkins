package myor.matrix.master.entity;

public class GetPassword {
	
	private String id;
	private String nama;
	public GetPassword() {
		super();
		// TODO Auto-generated constructor stub
	}
	public GetPassword(String id, String nama) {
		super();
		this.id = id;
		this.nama = nama;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	
	
}
