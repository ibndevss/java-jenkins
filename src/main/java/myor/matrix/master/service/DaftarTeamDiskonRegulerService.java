package myor.matrix.master.service;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import myor.matrix.master.entity.FilterStandardDto;
import net.sf.jasperreports.engine.JRException;

public interface DaftarTeamDiskonRegulerService {

	public String getReport (FilterStandardDto p , HttpServletResponse response) throws FileNotFoundException, JRException, IOException;
}
