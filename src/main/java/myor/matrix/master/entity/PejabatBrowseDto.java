package myor.matrix.master.entity;

public class PejabatBrowseDto {
	private String kdPejabat;
	private String nmPejabat;
	
	public PejabatBrowseDto() {
		// TODO Auto-generated constructor stub
	}

	public PejabatBrowseDto(String kdPejabat, String nmPejabat) {
		super();
		this.kdPejabat = kdPejabat;
		this.nmPejabat = nmPejabat;
	}

	public String getKdPejabat() {
		return kdPejabat;
	}

	public void setKdPejabat(String kdPejabat) {
		this.kdPejabat = kdPejabat;
	}

	public String getNmPejabat() {
		return nmPejabat;
	}

	public void setNmPejabat(String nmPejabat) {
		this.nmPejabat = nmPejabat;
	}
	
	
}
