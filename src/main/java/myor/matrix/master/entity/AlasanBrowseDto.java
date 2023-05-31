package myor.matrix.master.entity;

public class AlasanBrowseDto {

	private String kdAlasan;
	private String alasan;
	
	public AlasanBrowseDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AlasanBrowseDto(String kdAlasan, String alasan) {
		super();
		this.kdAlasan = kdAlasan;
		this.alasan = alasan;
	}

	public String getKdAlasan() {
		return kdAlasan;
	}

	public void setKdAlasan(String kdAlasan) {
		this.kdAlasan = kdAlasan;
	}

	public String getAlasan() {
		return alasan;
	}

	public void setAlasan(String alasan) {
		this.alasan = alasan;
	}
	
	
}
