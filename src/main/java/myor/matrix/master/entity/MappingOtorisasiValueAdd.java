package myor.matrix.master.entity;

import java.util.List;

public class MappingOtorisasiValueAdd {

	private Integer nilaiFrom;
	private Integer nilaiTo;
	private List<String> teams;
	private String pejabatId;
	private String pejabatName;

	public MappingOtorisasiValueAdd() {
		// TODO Auto-generated constructor stub
	}

	public MappingOtorisasiValueAdd(Integer nilaiFrom, Integer nilaiTo, List<String> teams, String pejabatId,
			String pejabatName) {
		super();
		this.nilaiFrom = nilaiFrom;
		this.nilaiTo = nilaiTo;
		this.teams = teams;
		this.pejabatId = pejabatId;
		this.pejabatName = pejabatName;
	}

	public Integer getNilaiFrom() {
		return nilaiFrom;
	}

	public void setNilaiFrom(Integer nilaiFrom) {
		this.nilaiFrom = nilaiFrom;
	}

	public Integer getNilaiTo() {
		return nilaiTo;
	}

	public void setNilaiTo(Integer nilaiTo) {
		this.nilaiTo = nilaiTo;
	}

	public List<String> getTeams() {
		return teams;
	}

	public void setTeams(List<String> teams) {
		this.teams = teams;
	}

	public String getPejabatId() {
		return pejabatId;
	}

	public void setPejabatId(String pejabatId) {
		this.pejabatId = pejabatId;
	}

	public String getPejabatName() {
		return pejabatName;
	}

	public void setPejabatName(String pejabatName) {
		this.pejabatName = pejabatName;
	}

}
