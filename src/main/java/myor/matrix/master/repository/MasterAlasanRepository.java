package myor.matrix.master.repository;

import java.util.List;

import myor.matrix.master.entity.AlasanBrowseDto;
import myor.matrix.master.entity.SelectItem;

public interface MasterAlasanRepository {
	
	public List<SelectItem<String>> getAlasan(String tipe);
	
	public List<SelectItem<String>> getAlasanNotEc();
	
	public List<AlasanBrowseDto> browseAlasan(String tipeAlasan);
	
	public List<SelectItem<String>> getAlasanRetur (boolean reshuffle);
}
