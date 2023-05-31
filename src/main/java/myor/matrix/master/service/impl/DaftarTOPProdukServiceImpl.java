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

import myor.matrix.master.entity.DaftarTOPProdukDto;
import myor.matrix.master.entity.DaftarTOPProdukFilterDto;
import myor.matrix.master.repository.DaftarTOPProdukRepository;
import myor.matrix.master.service.DaftarTOPProdukService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class DaftarTOPProdukServiceImpl implements DaftarTOPProdukService {

	@Autowired
	private DaftarTOPProdukRepository repository;

	@Override
	public String getReport(DaftarTOPProdukFilterDto fs, HttpServletResponse response)
			throws FileNotFoundException, JRException, IOException {
		// TODO Auto-generated method stub
		String hasil = "";

		List<DaftarTOPProdukDto> result = repository.getReport(fs.getPilihanData(), fs.getPilihanData2(),
				fs.getFilterFrom(), fs.getFilterTo());

		if (result.size() > 0) {
			Resource resource = new ClassPathResource("DaftarTOPProduk.jrxml");

			InputStream inStream = resource.getInputStream();

			JasperReport jasper = JasperCompileManager.compileReport(inStream);

			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(result);
			Map<String, Object> parameters = new HashMap<>();
			parameters.put(null, null);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasper, parameters, dataSource);

			try {
				// JasperExportManager.exportReportToPdfStream(jasperPrint,
				// response.getOutputStream());
				byte[] pdf = JasperExportManager.exportReportToPdf(jasperPrint);
				hasil = Base64.getEncoder().encodeToString(pdf);
			} catch (JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			hasil = "X";
		}

		return hasil;
	}

}
