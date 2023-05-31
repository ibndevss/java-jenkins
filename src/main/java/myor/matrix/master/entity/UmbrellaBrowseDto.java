package myor.matrix.master.entity;

public class UmbrellaBrowseDto {
	private String umbrella;
	private String umbrellaName;
	
	public UmbrellaBrowseDto() {
		// TODO Auto-generated constructor stub
	}

	public UmbrellaBrowseDto(String umbrella, String umbrellaName) {
		super();
		this.umbrella = umbrella;
		this.umbrellaName = umbrellaName;
	}

	public String getUmbrella() {
		return umbrella;
	}

	public void setUmbrella(String umbrella) {
		this.umbrella = umbrella;
	}

	public String getUmbrellaName() {
		return umbrellaName;
	}

	public void setUmbrellaName(String umbrellaName) {
		this.umbrellaName = umbrellaName;
	}
	
	
}
