package myor.matrix.master.entity;

public class MappingOtorisasiValueDTO {

	private Double nilaiFrom;
	private Double nilaiTo;
	private String team;
	private String pejabatId;
	private String pejabatName;

	public MappingOtorisasiValueDTO() {
		// TODO Auto-generated constructor stub
	}

	public MappingOtorisasiValueDTO(Double nilaiFrom, Double nilaiTo, String team, String pejabatId,
			String pejabatName) {
		super();
		this.nilaiFrom = nilaiFrom;
		this.nilaiTo = nilaiTo;
		this.team = team;
		this.pejabatId = pejabatId;
		this.pejabatName = pejabatName;
	}

	public Double getNilaiFrom() {
		return nilaiFrom;
	}

	public void setNilaiFrom(Double nilaiFrom) {
		this.nilaiFrom = nilaiFrom;
	}

	public Double getNilaiTo() {
		return nilaiTo;
	}

	public void setNilaiTo(Double nilaiTo) {
		this.nilaiTo = nilaiTo;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
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
