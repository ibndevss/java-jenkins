package myor.matrix.master.service.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
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

import myor.matrix.master.entity.DaftarHargaGeneralDto;
import myor.matrix.master.entity.FilterStandardDto;
import myor.matrix.master.repository.DaftarHargaGeneralRepository;
import myor.matrix.master.service.DaftarHargaGeneralService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
@Service
public class DaftarHargaGeneralServiceImpl implements DaftarHargaGeneralService {
	
	@Autowired
	private DaftarHargaGeneralRepository hargaGeneralRepo;

	@Override
	public String getReport(FilterStandardDto p, HttpServletResponse response)
			throws FileNotFoundException, JRException, IOException {
		// TODO Auto-generated method stub
		String pcodeFrom = p.getNoPcodeFrom();
		String pcodeTo = p.getNoPcodeTo();
		String dateBerlakuFrom = p.getDateBerlakuFrom();
		String dateBerlakuTo = p.getDateBerlakuTo();
		String inputDateBerlaku = "";
		String inputPcode = "";
		
		if (p.getDateBerlakuFrom().length() == 0 && p.getDateBerlakuTo().length() == 0) {
			inputDateBerlaku = "Semua";
		} else if (p.getDateBerlakuFrom().length() != 0 && p.getDateBerlakuTo().length() != 0) {
			inputDateBerlaku = p.getDateBerlakuFrom() + " s/d " + p.getDateBerlakuTo();
		} else if (p.getDateBerlakuFrom().length() != 0) {
			inputDateBerlaku = p.getDateBerlakuFrom() + " s/d ";
		} else if (p.getDateBerlakuTo().length() != 0) {
			inputDateBerlaku = " s/d " + p.getDateBerlakuTo();
		}
		
		if (p.getNoPcodeFrom().length() == 0 && p.getNoPcodeTo().length() == 0) {
			inputPcode = "Semua";
		} else if (p.getNoPcodeFrom().length() != 0 && p.getNoPcodeTo().length() != 0) {
			inputPcode = p.getNoPcodeFrom() + " s/d " + p.getNoPcodeTo();
		} else if (p.getNoPcodeFrom().length() != 0) {
			inputPcode = p.getNoPcodeFrom() + " s/d ";
		} else if (p.getNoPcodeTo().length() != 0) {
			inputPcode = " s/d " + p.getNoPcodeTo();
		}
		
		List<Object[]> getData = hargaGeneralRepo.getData(pcodeFrom, pcodeTo, dateBerlakuFrom, dateBerlakuTo);
		if (getData.size() == 0) {
			return "X";	
		} else {
			List<DaftarHargaGeneralDto> result = new ArrayList<>();
			DaftarHargaGeneralDto data = new DaftarHargaGeneralDto();
			Object[] header = hargaGeneralRepo.getHeader(inputDateBerlaku, inputPcode);
			
			for (Object[] obj : getData) {
				data = new DaftarHargaGeneralDto((String) obj[0], (String) obj[1], (String) obj[3], 
						(BigDecimal) obj[4], (BigDecimal) obj[5], (BigDecimal) obj[6], 
						(String) obj[7], 
						(String) header[0], (String) header[1], (String) header[2], (String) header[3], (String) header[4], (String) header[5]);
				result.add(data);
			}
			String hasil = "";
			Resource resource = new ClassPathResource("daftarHargaGeneral/DaftarHargaGeneral.jrxml");
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
