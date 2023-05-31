package myor.matrix.master.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myor.matrix.master.entity.SearchBrowseDto;
import myor.matrix.master.entity.SelectItem;
import myor.matrix.master.repository.TeamRepository;
import myor.matrix.master.service.TeamService;

@Service
public class TeamServiceImpl implements TeamService {
	
	@Autowired
	TeamRepository teamRepository;
	
	@Override
	public List<SelectItem<String>> getAllTeam() {
		// TODO Auto-generated method stub
		return teamRepository.getAllListTeam();
	}

	@Override
	public List<SelectItem<String>> getAllTeamActiveSalesman() {
		// TODO Auto-generated method stub
		return teamRepository.getAllListTeamActiveSalesman();
	}

	@Override
	public List<SearchBrowseDto> getBrowseTeam() {
		// TODO Auto-generated method stub
		return teamRepository.getBrowseTeam();
	}

	@Override
	public List<SelectItem<String>> getListTeam() {
		// TODO Auto-generated method stub
		return teamRepository.getListTeam();
	}

}
