package myor.matrix.master.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import myor.matrix.master.entity.MessageDto;
import myor.matrix.master.entity.ProsesNooBrowseDto;
import myor.matrix.master.entity.ProsesNooDto;
import myor.matrix.master.entity.ProsesNooInputDto;
import myor.matrix.master.entity.ProsesNooMessageDto;
import net.sf.jasperreports.engine.JRException;

public interface ProsesNooService {

	public List<ProsesNooBrowseDto> getBrowseDocument();
	
	public List<ProsesNooBrowseDto> getBrowseDataDocument();
	
	public ProsesNooDto getLoadData(String docNo);
	
	public List<String> loadForm();
	
	public MessageDto addDocument(ProsesNooInputDto p);
	
	public MessageDto addData(ProsesNooInputDto p);

	public MessageDto cancelDocument(ProsesNooInputDto p);

	public MessageDto deleteDocument(ProsesNooInputDto p);

	public MessageDto saveDocument(ProsesNooInputDto p);

	public MessageDto bersihDocument(ProsesNooInputDto p);

	public MessageDto loadDataDocument(ProsesNooInputDto p);

	public MessageDto deleteDataDocument(ProsesNooInputDto p);
	
	public MessageDto keyRequest(ProsesNooInputDto p);

	public MessageDto onPrintFPP(ProsesNooInputDto p, HttpServletResponse response) throws FileNotFoundException, JRException, IOException ;

	public MessageDto onPrintRekap(ProsesNooInputDto p, HttpServletResponse response) throws FileNotFoundException, JRException, IOException ;

	public ProsesNooMessageDto onProcessDocument(ProsesNooInputDto p);
	
	public ProsesNooMessageDto onCekEdit(ProsesNooInputDto p);
	
	public MessageDto onActionEdit(ProsesNooInputDto p);
}
