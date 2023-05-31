package myor.matrix.master.restcontroller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import myor.matrix.master.entity.ReportOutletRuteDto;
import myor.matrix.master.service.OutletRuteService;
import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping(path = "/outlet-rute")
public class OutletRuteRestController {
	@Autowired
	OutletRuteService outletRuteService;
	
	@GetMapping(path = "/report", params={"custFrom", "custTo", "teamFrom", "teamTo",
			"slsFrom", "slsTo", "pil", "hari", "urut", "userId"})
	public List<ReportOutletRuteDto> getBrowse(String custFrom, String custTo, String teamFrom, String teamTo,
			String slsFrom, String slsTo, String pil, String hari, String urut, String userId) {
		return outletRuteService.getReport(custFrom, custTo, teamFrom, teamTo, slsFrom, slsTo, pil, hari, urut, userId);
	}
	
	
	@PutMapping(path = "/preview",  params= {"custFrom", "custTo", "teamFrom", "teamTo",
			"slsFrom", "slsTo", "pil", "hari", "urut", "userId"})
	public String getReport(String custFrom, String custTo, String teamFrom, String teamTo,
			String slsFrom, String slsTo, String pil, String hari, String urut, String userId, HttpServletResponse response)
			throws FileNotFoundException, JRException, IOException {		
		return outletRuteService.getReportOutletRute(custFrom, custTo, teamFrom, teamTo, slsFrom, slsTo, pil, hari, urut, userId, response);
	}
	
	@GetMapping(path = "/upd-module")
	public List<String> getUpdModule(){
		List<String> result = new ArrayList<String>();
		result.add("16 MAR 2023");
		return result;
	}

}
