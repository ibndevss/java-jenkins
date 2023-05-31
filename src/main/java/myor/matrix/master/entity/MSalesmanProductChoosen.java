package myor.matrix.master.entity;

public class MSalesmanProductChoosen {

	private String team;
	private String divisi;
	private String divisiname;
	private String keterangan;
	private String flag;
	
	public MSalesmanProductChoosen() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MSalesmanProductChoosen(String team, String divisi, String divisiname, String keterangan, String flag) {
		super();
		this.team = team;
		this.divisi = divisi;
		this.divisiname = divisiname;
		this.keterangan = keterangan;
		this.flag = flag;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public String getDivisi() {
		return divisi;
	}

	public void setDivisi(String divisi) {
		this.divisi = divisi;
	}

	public String getDivisiname() {
		return divisiname;
	}

	public void setDivisiname(String divisiname) {
		this.divisiname = divisiname;
	}

	public String getKeterangan() {
		return keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	
}
