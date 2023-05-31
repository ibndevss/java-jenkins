package myor.matrix.master.repository;

import java.util.List;

import myor.matrix.master.entity.SearchBrowseDto;
import myor.matrix.master.entity.SelectItem;

public interface TeamRepository {
	
	public List<SelectItem<String>> getAllListTeam();
	
	public List<SelectItem<String>> getAllListTeamActiveSalesman();
	
	public List<SearchBrowseDto> getBrowseTeam();
	
	public List<SelectItem<String>> getListTeam();
	
}
