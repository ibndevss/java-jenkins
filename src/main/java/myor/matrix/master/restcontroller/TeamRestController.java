package myor.matrix.master.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import myor.matrix.master.entity.SearchBrowseDto;
import myor.matrix.master.entity.SelectItem;
import myor.matrix.master.service.TeamService;

@RestController
@RequestMapping(path = "/team")
public class TeamRestController {
	
	@Autowired
	private TeamService teamService;
	
	@GetMapping(path = "/select-items")
	public List<SelectItem<String>> getListTeam(){
		return teamService.getAllTeam();
	}
	
	@GetMapping(path = "/select-items/active-sls")
	public List<SelectItem<String>> getListTeamActiveSalesman(){
		return teamService.getAllTeamActiveSalesman();
	}
	
	@GetMapping(path = "/browse")
    public List<SearchBrowseDto> getBrowseTeam() {
        return teamService.getBrowseTeam();
    }
	
	@GetMapping(path="/select-items/all")
	public List<SelectItem<String>> getListTeam2() {
		return teamService.getListTeam();
	}
	
}
