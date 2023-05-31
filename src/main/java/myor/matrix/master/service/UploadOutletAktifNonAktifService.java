package myor.matrix.master.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import myor.matrix.master.entity.MessageDto;
import myor.matrix.master.entity.UploadOutletAktifNonAktifDto;
import myor.matrix.master.entity.UploadOutletAktifNonAktifExcelDto;
import myor.matrix.master.entity.UploadOutletAktifNonAktifHeaderDto;
import net.sf.jasperreports.engine.JRException;

public interface UploadOutletAktifNonAktifService {

	public List<String> loadForm();
	
	public List<UploadOutletAktifNonAktifHeaderDto> getBrowseDocument();
	
	public UploadOutletAktifNonAktifDto getDatas(String docNo);
	
	public MessageDto deleteDocument(String docNo);
	
	public MessageDto processDocument(String docNo, String act, String pass, String ras, String xuser);
	
	public MessageDto validasiOutlet(String custNo, String docDate, String action);
	
	public MessageDto print(UploadOutletAktifNonAktifDto p, String action, HttpServletResponse response) throws FileNotFoundException, JRException, IOException ;

	public UploadOutletAktifNonAktifDto cekExcel(List<UploadOutletAktifNonAktifExcelDto> p, String action, String docdate);
}
