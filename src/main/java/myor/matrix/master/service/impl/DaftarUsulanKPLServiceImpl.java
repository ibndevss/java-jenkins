package myor.matrix.master.service.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import myor.matrix.master.entity.DaftarUsulanKPLDto;
import myor.matrix.master.entity.FilterStandardDto;
import myor.matrix.master.repository.DaftarUsulanKPLRepository;
import myor.matrix.master.service.DaftarUsulanKPLService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class DaftarUsulanKPLServiceImpl implements DaftarUsulanKPLService {

	@Autowired
	DaftarUsulanKPLRepository repository;
	
	@Override
	public String getReport(FilterStandardDto fs, HttpServletResponse response)
			throws FileNotFoundException, JRException, IOException {
		// TODO Auto-generated method stub
		String base64result = "";
		
		List<DaftarUsulanKPLDto> result = repository.getReport(fs.getSlsnoFrom(), fs.getSlsnoTo(), fs.getTglFrom(), fs.getTglTo(), fs.getSortingData());
		
		if (result.size() == 0) {
			base64result = "X";
		} else {
			Resource resource = null;
			
			if (fs.getSortingData().equalsIgnoreCase("'0'")) {
				resource = new ClassPathResource("DaftarUsulanKPL.jrxml");
			} else {
				resource = new ClassPathResource("DaftarUsulanKPL2.jrxml");
			}
			
			System.out.println(fs.getSortingData());
			System.out.println(resource);
			
			InputStream inStream = resource.getInputStream();
			JasperReport jasper = JasperCompileManager.compileReport(inStream);
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(result);
			Map<String, Object> parameters = new HashMap<>();
			parameters.put(null, null);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasper, parameters, dataSource);
			
			try {
				byte[] pdf = JasperExportManager.exportReportToPdf(jasperPrint);
				base64result = Base64.getEncoder().encodeToString(pdf);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		return base64result;
	}

}
