package myor.matrix.master.entity;

public class TypeOutletBrowseDto {

	private String type;
	private String typeName;
	
	public TypeOutletBrowseDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TypeOutletBrowseDto(String type, String typeName) {
		super();
		this.type = type;
		this.typeName = typeName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	
}
