package myor.matrix.master.entity;

import java.util.List;

public class MasterOutletSaveDto {

	private MasterOutletDataProfileDto dataProfile;
	private MasterOutletDataAttributeDto dataAttribute;
	private MasterOutletDataPajakDto dataPajak;
	private MasterOutletDataPemerintahanDto dataPemerintahan;
	public MasterOutletSaveDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MasterOutletSaveDto(MasterOutletDataProfileDto dataProfile, MasterOutletDataAttributeDto dataAttribute,
			MasterOutletDataPajakDto dataPajak, MasterOutletDataPemerintahanDto dataPemerintahan) {
		super();
		this.dataProfile = dataProfile;
		this.dataAttribute = dataAttribute;
		this.dataPajak = dataPajak;
		this.dataPemerintahan = dataPemerintahan;
	}
	public MasterOutletDataProfileDto getDataProfile() {
		return dataProfile;
	}
	public void setDataProfile(MasterOutletDataProfileDto dataProfile) {
		this.dataProfile = dataProfile;
	}
	public MasterOutletDataAttributeDto getDataAttribute() {
		return dataAttribute;
	}
	public void setDataAttribute(MasterOutletDataAttributeDto dataAttribute) {
		this.dataAttribute = dataAttribute;
	}
	public MasterOutletDataPajakDto getDataPajak() {
		return dataPajak;
	}
	public void setDataPajak(MasterOutletDataPajakDto dataPajak) {
		this.dataPajak = dataPajak;
	}
	public MasterOutletDataPemerintahanDto getDataPemerintahan() {
		return dataPemerintahan;
	}
	public void setDataPemerintahan(MasterOutletDataPemerintahanDto dataPemerintahan) {
		this.dataPemerintahan = dataPemerintahan;
	}
}
