package myor.matrix.master.service;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import myor.matrix.master.entity.DaftarTOPProdukFilterDto;
import net.sf.jasperreports.engine.JRException;

public interface DaftarTOPProdukService {
	public String getReport(DaftarTOPProdukFilterDto fs, HttpServletResponse response)
			throws FileNotFoundException, JRException, IOException;
}
