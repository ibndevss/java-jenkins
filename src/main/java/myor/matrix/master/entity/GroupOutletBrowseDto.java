package myor.matrix.master.entity;

public class GroupOutletBrowseDto {
	private String groupCode;
	private String groupName;
	
	public GroupOutletBrowseDto() {
		// TODO Auto-generated constructor stub
	}

	public GroupOutletBrowseDto(String groupCode, String groupName) {
		super();
		this.groupCode = groupCode;
		this.groupName = groupName;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	
}
