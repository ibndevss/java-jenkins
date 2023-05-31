package myor.matrix.master.service;

import java.util.List;

import myor.matrix.master.entity.SearchBrowseDto;
import myor.matrix.master.entity.SelectItem;

public interface TeamService {
	
	public List<SelectItem<String>> getAllTeam();
	
	public List<SelectItem<String>> getAllTeamActiveSalesman();
	
	public List<SearchBrowseDto> getBrowseTeam();
	
	public List<SelectItem<String>> getListTeam();
	
}
