package myor.matrix.master.entity;

public class PlantDto {

	private String plantId;
	private String plantName;
	private String address;
	private String noTelpon;
	
	public PlantDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PlantDto(String plantId, String plantName, String address, String noTelpon) {
		super();
		this.plantId = plantId;
		this.plantName = plantName;
		this.address = address;
		this.noTelpon = noTelpon;
	}

	public String getPlantId() {
		return plantId;
	}

	public void setPlantId(String plantId) {
		this.plantId = plantId;
	}

	public String getPlantName() {
		return plantName;
	}

	public void setPlantName(String plantName) {
		this.plantName = plantName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNoTelpon() {
		return noTelpon;
	}

	public void setNoTelpon(String noTelpon) {
		this.noTelpon = noTelpon;
	}
	
	
}
