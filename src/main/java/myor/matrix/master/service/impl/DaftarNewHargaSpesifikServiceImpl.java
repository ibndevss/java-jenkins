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

import myor.matrix.master.entity.DaftarNewHargaSpesifikDto;
import myor.matrix.master.entity.FilterStandardDto;
import myor.matrix.master.repository.DaftarNewHargaSpesifikRepository;
import myor.matrix.master.service.DaftarNewHargaSpesifikService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class DaftarNewHargaSpesifikServiceImpl implements DaftarNewHargaSpesifikService {

	@Autowired
	private DaftarNewHargaSpesifikRepository repository;

	@Override
	public String getReport(FilterStandardDto fs, HttpServletResponse response)
			throws FileNotFoundException, JRException, IOException {
		// TODO Auto-generated method stub
		String hasil = "";

		List<DaftarNewHargaSpesifikDto> result = repository.getReport(fs.getUmbrellaFrom(), fs.getUmbrellaTo(),
				fs.getBrandFrom(), fs.getBrandTo(), fs.getSubBrandFrom(), fs.getSubBrandTo(), fs.getPcodeFrom(),
				fs.getPcodeTo(), fs.getTglBerlakuFrom(), fs.getTglBerlakuTo(), fs.getPilihanData(),
				fs.getSortingData());

		if (result.size() > 0) {
			Resource resource = new ClassPathResource("DaftarNewHargaSpesifik.jrxml");

			InputStream inStream = resource.getInputStream();

			JasperReport jasper = JasperCompileManager.compileReport(inStream);

			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(result);
			Map<String, Object> parameters = new HashMap<>();
			parameters.put(null, null);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasper, parameters, dataSource);

			try {
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
