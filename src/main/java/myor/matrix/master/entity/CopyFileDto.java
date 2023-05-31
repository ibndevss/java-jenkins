package myor.matrix.master.entity;

public class CopyFileDto {

	private String location;
	private String destination;
	public CopyFileDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CopyFileDto(String location, String destination) {
		super();
		this.location = location;
		this.destination = destination;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
}
