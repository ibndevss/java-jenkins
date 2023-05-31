package myor.matrix.master.service.impl;

import java.io.Console;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import myor.matrix.master.entity.DaftarTeamDiskonRegulerDto;
import myor.matrix.master.entity.FilterStandardDto;
import myor.matrix.master.repository.DaftarTeamDiskonRegulerRepository;
import myor.matrix.master.service.DaftarTeamDiskonRegulerService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
@Service
public class DaftarTeamDiskonRegulerServiceImpl implements DaftarTeamDiskonRegulerService {
	
	@Autowired
	private DaftarTeamDiskonRegulerRepository teamDiskonRepo;

	@Override
	public String getReport(FilterStandardDto p, HttpServletResponse response)
			throws FileNotFoundException, JRException, IOException {
		// TODO Auto-generated method stub
		
		String teamFrom = p.getNoTeamFrom();
		String teamTo = p.getNoTeamTo();
		String dateEfektifFrom = p.getDateEfektifFrom();
		String dateEfektifTo = p.getDateEfektifTo();
		String inputDateEfektif = "";
		String inputTeam = "";
		
		if (p.getDateEfektifFrom().length() == 0 && p.getDateEfektifTo().length() == 0) {
			inputDateEfektif = "Semua";
		} else if (p.getDateEfektifFrom().length() != 0 && p.getDateEfektifTo().length() != 0) {
			inputDateEfektif = p.getDateEfektifFrom() + " s/d " + p.getDateEfektifTo();
		} else if (p.getDateEfektifFrom().length() != 0) {
			inputDateEfektif = p.getDateEfektifFrom() + " s/d ";
		} else if (p.getDateEfektifTo().length() != 0) {
			inputDateEfektif = " s/d " + p.getDateEfektifTo();
		}
		
		if (p.getNoTeamFrom().length() == 0 && p.getNoTeamTo().length() == 0) {
			inputTeam = "Semua";
		} else if (p.getNoTeamFrom().length() != 0 && p.getNoTeamTo().length() != 0) {
			inputTeam = p.getNoTeamFrom() + " s/d " + p.getNoTeamTo();
		} else if (p.getNoTeamFrom().length() != 0) {
			inputTeam = p.getNoTeamFrom() + " s/d ";
		} else if (p.getNoTeamTo().length() != 0) {
			inputTeam = " s/d " + p.getNoTeamTo();
		}
		
		List<Object[]> getData = teamDiskonRepo.getData(teamFrom, teamTo, dateEfektifFrom, dateEfektifTo);
		if (getData.size() == 0) {
			return "X";	
		} else {
			List<DaftarTeamDiskonRegulerDto> result = new ArrayList<>();
			DaftarTeamDiskonRegulerDto data = new DaftarTeamDiskonRegulerDto();
			Object[] header = teamDiskonRepo.getHeader(inputDateEfektif, inputTeam);
			String tgltemp = "";
			String teamtemp = "";
			String teamnametemp = "";
			for (Object[] obj : getData) {
				String tgl = (String) obj[0];
				String team = (String) obj[1];
				String teamname = (String) obj[5];
				if (tgl.equalsIgnoreCase(tgltemp) && team.equalsIgnoreCase(teamtemp) && teamname.equalsIgnoreCase(teamnametemp)) {
					tgl = "99-DES-9999";
					team = "99-DES-9999";
					teamname = "99-DES-9999";
				} else {
					tgl = (String) obj[0];
					team = (String) obj[1];
					teamname = (String) obj[5];
				}
				
				tgltemp = (String) obj[0];
				teamtemp = (String) obj[1];
				teamnametemp = (String) obj[5];
				data = new DaftarTeamDiskonRegulerDto(tgl, team, teamname, (String) obj[2], (String) obj[4], (String) obj[3], 
						(String) header[0], (String) header[1], (String) header[2], (String) header[3], (String) header[4], (String) header[5]);
				result.add(data);
			}
			
//			Collections.sort(result, new Comparator<DaftarTeamDiskonRegulerDto>() {
//				@Override
//				public int compare(DaftarTeamDiskonRegulerDto o1, DaftarTeamDiskonRegulerDto o2) {
//					int c;
//					
//					c =  Integer.compare(o2.getTGLEFEKTIF().length(), o1.getTGLEFEKTIF().length());
//					if (c == 0) {
//						c =  Integer.compare(o1.getTEAM().length(), o2.getTEAM().length());
//					}
//					if (c == 0) {
//						c =  Integer.compare(o1.getDIVISI().length(), o1.getDIVISI().length());
//					}
//					return c;
//				}
//			});
			
			Collections.sort(result,Comparator.comparing(DaftarTeamDiskonRegulerDto::getTGLEFEKTIF)
		            .thenComparing(DaftarTeamDiskonRegulerDto::getTEAM)
		            .thenComparing(DaftarTeamDiskonRegulerDto::getDIVISI));
//			
//			Collections.sort(result,
//                    Comparator.nullsFirst(
//                        Comparator.comparing(
//                        		DaftarTeamDiskonRegulerDto::getTGLEFEKTIF)));

			String hasil = "";
			Resource resource = new ClassPathResource("daftarTeamDiskonRegular/DaftarTeamDiskonRegular.jrxml");
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
