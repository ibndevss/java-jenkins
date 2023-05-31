package myor.matrix.master.service.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import myor.matrix.master.entity.OutletRuteDto;
import myor.matrix.master.entity.ReportOutletRuteDto;
import myor.matrix.master.entity.TotalOutletSalesmanDto;
import myor.matrix.master.repository.OutletRuteRepository;
import myor.matrix.master.service.OutletRuteService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class OutletRuteServiceImpl implements OutletRuteService {
	@Autowired
	OutletRuteRepository outletRuteRepository;
	
	@Override
	public void insertTemp(String custFrom, String custTo, String teamFrom, String teamTo, String slsFrom, String slsTo,
			String pil, String hari, String urut, String userId) {
		String slsno="", slsnotemp="", slsnametemp="";
	    int i, no_urut=0, a, totaloutlet=0, j=0;
		List<OutletRuteDto> header = outletRuteRepository.getOutletRute(custFrom, custTo, teamFrom, teamTo, slsFrom, slsTo, pil, hari, urut);
		for(OutletRuteDto h: header) {
			j = j+1;
			slsno = h.getSLSNO();
//			salesname = h.getSLSNAME();
			if(slsno.equalsIgnoreCase(slsnotemp)) {
				no_urut = no_urut +1;
			}
			if(!slsno.equalsIgnoreCase(slsnotemp)) {
				no_urut = 1;
				List<TotalOutletSalesmanDto> res= outletRuteRepository.getTotalOutlet(slsnotemp, hari);
				if(!slsnotemp.equalsIgnoreCase("")) {
					a =2;
					outletRuteRepository.insertOutletRute(slsnotemp, slsnametemp, Integer.toString(a), 
							"Total Outlet Salesman "+slsnotemp+" : "+ Integer.toString(totaloutlet), "", "", "", "", "", "", "", "", userId);
					a = a+1;
					for(TotalOutletSalesmanDto r:res) {
						outletRuteRepository.insertOutletRute(slsnotemp, slsnametemp, Integer.toString(a), r.getKode()+"-"+r.getNama()+":"+r.getJml(), "", "", "", "", "", "", "", "", userId);
						a = a+1;
					}
				}
			}
			slsnotemp = h.getSLSNO();
			slsnametemp = h.getSLSNAME();
			a=0;
			outletRuteRepository.insertOutletRute(h.getSLSNO(), h.getSLSNAME(), "1", Integer.toString(no_urut), h.getCUSTNO(), h.getCUSTNAME(), h.getCUSTADD1(), h.getCLIMIT(), h.getSBEAT(), h.getHARI(), h.getPOLA(), h.getRUTE(), userId);
			totaloutlet = no_urut;
			if(j == header.size()) {
				a = 2;
				outletRuteRepository.insertOutletRute(h.getSLSNO(), h.getSLSNAME(), Integer.toString(a), "Total Outlet Salesman "+h.getSLSNO()+" : "+ Integer.toString(totaloutlet), "", "", "", "", "", "", "", "", userId);
				List<TotalOutletSalesmanDto>tot =  outletRuteRepository.getTotalOutlet(slsno, hari);
				a = a+1;
				for(TotalOutletSalesmanDto t: tot) {
					outletRuteRepository.insertOutletRute(h.getSLSNO(), h.getSLSNAME(), Integer.toString(a), t.getKode()+"-"+t.getNama()+":"+t.getJml(), "", "", "", "", "", "", "", "", userId);
					a =a +1;
				}
			}
		}
	}

	@Override
	public List<ReportOutletRuteDto> getReport(String custFrom, String custTo, String teamFrom, String teamTo,
			String slsFrom, String slsTo, String pil, String hari, String urut, String userId) {
		outletRuteRepository.deleteTempReport(userId);
		insertTemp(custFrom, custTo, teamFrom, teamTo, slsFrom, slsTo, pil, hari, urut, userId);
		return outletRuteRepository.getReport(custFrom, custTo, teamFrom, teamTo, slsFrom, slsTo, pil, hari, urut, userId);
	}

	@Override
	public String getReportOutletRute(String custFrom, String custTo, String teamFrom, String teamTo, String slsFrom,
			String slsTo, String pil, String hari, String urut, String userId, HttpServletResponse response) throws FileNotFoundException, JRException, IOException {
		// TODO Auto-generated method stub
		String hasil ="";
		
		List<ReportOutletRuteDto> result = new ArrayList<>();
		result = getReport(custFrom, custTo, teamFrom, teamTo, slsFrom, slsTo, pil, hari, urut, userId);
		if (result.size() == 0) {
			return "X";
		} else {
			Resource resource = new ClassPathResource("");
			resource = new ClassPathResource("daftarOutletPerRute/daftar_outlet_per_rute.jrxml");
			InputStream inStream = resource.getInputStream();
			JasperReport jasper = JasperCompileManager.compileReport(inStream);

			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(result);

			Map<String, Object> parameters = new HashMap<>();
			parameters.put(null, null);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasper, parameters, dataSource);
			jasperPrint.setProperty("net.sf.jasperreports.export.pdf.permissions.denied",
					"ASSEMBLY|COPY|FILL_IN|MODIFY_ANNOTATIONS|MODIFY_CONTENTS|SCREENREADERS|DEGRADED_PRINTING|PRINTING");
			try {
				byte[] pdf = JasperExportManager.exportReportToPdf(jasperPrint);
				hasil = Base64.getEncoder().encodeToString(pdf);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return hasil;
		}
	}

}
