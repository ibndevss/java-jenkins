package myor.matrix.master.service.impl;

import java.io.FileInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import myor.matrix.master.entity.MessageDto;
import myor.matrix.master.entity.ProductFocusBrowseTahunDto;
import myor.matrix.master.entity.ProductFocusDetailDto;
import myor.matrix.master.entity.ProductFocusDetailStockDto;
import myor.matrix.master.entity.ProductFocusFilterDto;
import myor.matrix.master.entity.ProductFocusHeaderDto;
import myor.matrix.master.entity.ProductFocusLoadDataDto;
import myor.matrix.master.entity.ProductFocusLoadDetailDto;
import myor.matrix.master.entity.ProductFocusLoadDetailStockDto;
import myor.matrix.master.entity.ProductFocusLoadMinOrder;
import myor.matrix.master.entity.ProductFocusMinOrderDto;
import myor.matrix.master.entity.ProductFocusUploadSFADto;
import myor.matrix.master.file.ProductFocusFile;
import myor.matrix.master.properties.RootDirectoryProperties;
import myor.matrix.master.repository.ProductFocusRepository;
import myor.matrix.master.service.ProductFocusService;

@Service
public class ProductFocusServiceImpl implements ProductFocusService {

	@Autowired
	private ProductFocusRepository repository;
	
	@Autowired
	private ProductFocusFile productFocusFile;
	
	private final Path rootLocationExcel;
	
	@Autowired
	public ProductFocusServiceImpl(RootDirectoryProperties properties) {
		// TODO Auto-generated constructor stub
		this.rootLocationExcel = Paths.get(properties.getFilePathExcel());
	}

	@Override
	public List<ProductFocusBrowseTahunDto> getBrowseFocusSFA() {
		// TODO Auto-generated method stub
		return repository.getBrowseFocusSFA();
	}

	@Override
	public List<ProductFocusBrowseTahunDto> getBrowseFocusStock() {
		// TODO Auto-generated method stub
		return repository.getBrowseFocusStock();
	}

	@Override
	public ProductFocusLoadDataDto loadFirstData() {
		// TODO Auto-generated method stub
		ProductFocusHeaderDto headerSFA = repository.getMaxFocusSFA();
		List<ProductFocusDetailDto> detailSFA = repository.getDetailFocusSFA(headerSFA.getTahun(), headerSFA.getPeriode());
		ProductFocusHeaderDto headerStock = repository.getMaxFocusStock();
		List<ProductFocusDetailStockDto> detailStock = repository.getDetailFocusStock(headerStock.getTahun(), headerStock.getPeriode());
		List<ProductFocusMinOrderDto> minOrder = repository.getDataMinOrder();
		
		ProductFocusLoadDataDto dataEntry = new ProductFocusLoadDataDto(headerSFA, detailSFA, headerStock, detailStock, minOrder);
		
		return dataEntry;
	}

	@Override
	public List<ProductFocusDetailDto> getDetailSFA(String tahun, String periode) {
		// TODO Auto-generated method stub
		return repository.getDetailFocusSFA(tahun, periode);
	}

	@Override
	public List<ProductFocusDetailStockDto> getDetailStock(String tahun, String periode) {
		// TODO Auto-generated method stub
		return repository.getDetailFocusStock(tahun, periode);
	}

	@Transactional
	@Override
	public ProductFocusLoadDetailDto insertFocusSFA(String tahun, String periode, String pcode) {
		// TODO Auto-generated method stub
		List<ProductFocusDetailDto> detail = new ArrayList<>(); 
		List<MessageDto> message = new ArrayList<>();
		
		ProductFocusLoadDetailDto dataEntry = new ProductFocusLoadDetailDto(detail, message);
		
		boolean cek = repository.isFocusSFAExist(tahun, periode, pcode);
		
		if (cek == true) {
			message.add(new MessageDto("warn", "Warning", "Produk sudah ada"));
		} else {
			repository.insertFocusSFA(tahun, periode, "Semua", "DBEDATA", pcode);
			detail = repository.getDetailFocusSFA(tahun, periode);
			message.add(new MessageDto("success", "Success", "Data Berhasil Diinput"));
		}
		
		dataEntry = new ProductFocusLoadDetailDto(detail, message);
		
		return dataEntry;
	}

	@Transactional
	@Override
	public ProductFocusLoadDetailStockDto insertFocusStock(ProductFocusFilterDto fs) {
		// TODO Auto-generated method stub
		List<ProductFocusDetailStockDto> detail = new ArrayList<>(); 
		List<MessageDto> message = new ArrayList<>();
		
		ProductFocusLoadDetailStockDto dataEntry = new ProductFocusLoadDetailStockDto(detail, message);
		
		String tahun = fs.getTahun();
		String periode = fs.getPeriode();
		String pcode = fs.getPcode();
		String salesforce = fs.getSalesforce();
		String team = fs.getTeam();
		String channel = fs.getChannel();
		String tglAwal = fs.getTglAwal();
		String tglAkhir = fs.getTglAkhir();
		String flagAuto = fs.getFlagAuto(); 
		
		boolean cek = repository.isFocusStockExist(tahun, periode, pcode, salesforce, team, channel, tglAwal, tglAkhir);		
		 
		if (cek == true) {
			message.add(new MessageDto("warn", "Warning", "Produk sudah ada"));
		} else {
			if (flagAuto.equalsIgnoreCase("Y")) {
				String startWeek = repository.getStartWeek();
				String endWeek = repository.getEndWeek();
				
				if (repository.cekInsertStockAuto(tahun, periode, "Semua", "DBEDATA", salesforce, team, channel,
						tglAwal, tglAkhir, "Y", startWeek, endWeek) == true) {
					repository.insertFocusStockAuto(tahun, periode, "Semua", "DBEDATA", salesforce, team, channel, tglAwal,
							tglAkhir, "Y", startWeek, endWeek);
					message.add(new MessageDto("success", "Success", "Data Berhasil Diinput"));
				} else {
					message.add(new MessageDto("warn", "Warning", "Data Tidak Ada"));
				}
				
				
			} else {
				repository.insertFocusStock(tahun, periode, "Semua", "DBEDATA", pcode, salesforce, team, channel,
						tglAwal, tglAkhir,"");
				message.add(new MessageDto("success", "Success", "Data Berhasil Diinput"));
			}						
			
			detail = repository.getDetailFocusStock(tahun, periode);
			
		}
		
		dataEntry = new ProductFocusLoadDetailStockDto(detail, message);
		
		return dataEntry;		
	}

	@Transactional
	@Override
	public ProductFocusLoadDetailDto deleteFocusSFA(String tahun, String periode, String pcode) {
		// TODO Auto-generated method stub
		List<ProductFocusDetailDto> detail = new ArrayList<>(); 
		List<MessageDto> message = new ArrayList<>();
		
		ProductFocusLoadDetailDto dataEntry = new ProductFocusLoadDetailDto(detail, message);
		
		repository.deleteFocusSFA(tahun, periode, pcode);
		message.add(new MessageDto("success", "Success", "Data Berhasil Dihapus"));
		detail = repository.getDetailFocusSFA(tahun, periode);
		
		dataEntry = new ProductFocusLoadDetailDto(detail, message);
		
		return dataEntry;
	}

	@Transactional
	@Override
	public ProductFocusLoadDetailStockDto deleteFocusStock(ProductFocusFilterDto fs) {
		// TODO Auto-generated method stub
		List<ProductFocusDetailStockDto> detail = new ArrayList<>(); 
		List<MessageDto> message = new ArrayList<>();
		
		ProductFocusLoadDetailStockDto dataEntry = new ProductFocusLoadDetailStockDto(detail, message);
		
		String tahun = fs.getTahun();
		String periode = fs.getPeriode();
		String pcode = fs.getPcode();
		String salesforce = fs.getSalesforce();
		String team = fs.getTeam();
		String channel = fs.getChannel();
		String tglAwal = fs.getTglAwal();
		String tglAkhir = fs.getTglAkhir();
		
		repository.deleteFocusStock(tahun, periode, pcode, salesforce, team, channel, tglAwal, tglAkhir);
		message.add(new MessageDto("success", "Success", "Data Berhasil Dihapus"));
		detail = repository.getDetailFocusStock(tahun, periode);
		
		dataEntry = new ProductFocusLoadDetailStockDto(detail, message);
		
		return dataEntry;
	}

	@Transactional
	@Override
	public ProductFocusLoadMinOrder insertMinOrder(String pcode, String tglAwal, String tglAkhir, String minOrder, String userId) {
		// TODO Auto-generated method stub
		List<ProductFocusMinOrderDto> detail = new ArrayList<>();
		List<MessageDto> message = new ArrayList<>();
		
		ProductFocusLoadMinOrder dataEntry = new ProductFocusLoadMinOrder(detail, message);
		
		boolean cek = repository.isMinOrderExist(pcode);
		
		if (cek == true) {
			message.add(new MessageDto("warn", "Warning", "Produk sudah ada"));
		} else {
			repository.insertMinOrder(pcode, tglAwal, tglAkhir, minOrder, userId);
			detail = repository.getDataMinOrder();
			message.add(new MessageDto("success", "Success", "Data Berhasil Diinput"));
		}
		
		dataEntry = new ProductFocusLoadMinOrder(detail, message);
		
		return dataEntry;
	}

	@Transactional
	@Override
	public ProductFocusLoadMinOrder updateMinOrder(String pcode, String tglAwal, String tglAkhir, String minOrder,
			String userId) {
		// TODO Auto-generated method stub
		List<ProductFocusMinOrderDto> detail = new ArrayList<>();
		List<MessageDto> message = new ArrayList<>();
		
		ProductFocusLoadMinOrder dataEntry = new ProductFocusLoadMinOrder(detail, message);
		
		repository.updateMinOrder(pcode, tglAwal, tglAkhir, minOrder, userId);
		message.add(new MessageDto("success", "Success", "Data Berhasil Diupdate"));
		detail = repository.getDataMinOrder();
		
		dataEntry = new ProductFocusLoadMinOrder(detail, message);
		
		return dataEntry;
	}

	@Transactional
	@Override
	public ProductFocusLoadMinOrder deleteMinOrder(String pcode) {
		// TODO Auto-generated method stub
		List<ProductFocusMinOrderDto> detail = new ArrayList<>();
		List<MessageDto> message = new ArrayList<>();
		
		ProductFocusLoadMinOrder dataEntry = new ProductFocusLoadMinOrder(detail, message);
		
		repository.deleteMinOrder(pcode);
		message.add(new MessageDto("success", "Success", "Data Berhasil Dihappus"));
		detail = repository.getDataMinOrder();
		
		dataEntry = new ProductFocusLoadMinOrder(detail, message);
		
		return dataEntry;
	}

	@Transactional
	@Override
	public ProductFocusUploadSFADto uploadFocusSFA(MultipartFile file, String userId) {
		// TODO Auto-generated method stub
		ProductFocusHeaderDto header = new ProductFocusHeaderDto();
		List<ProductFocusDetailDto> detail = new ArrayList<>();
		List<MessageDto> message = new ArrayList<>();
		
		ProductFocusUploadSFADto dataEntry = new ProductFocusUploadSFADto(header, detail, message);
		
		productFocusFile.uploadFocusSFA(file, userId);
		
		List<String> valid = repository.validasiUploadFocusSFA(userId);
		if (!valid.isEmpty()) {
			for (String val : valid) {
				message.add(new MessageDto("warn", "Warning", val));
			}
		} else {
			repository.insertFocusSFAUpload(userId);
			header = repository.getMaxFocusSFA();
			detail = repository.getDetailFocusSFA(header.getTahun(), header.getPeriode());
			message.add(new MessageDto("success", "Success", "Data Berhasil Diupload"));
		}
		
		repository.deleteTempFocusSFA(userId);		
		dataEntry = new ProductFocusUploadSFADto(header, detail, message);
		
		return dataEntry;
	}

	@Override
	public void readUploadSFA(String userId, String originalFilename) {
		// TODO Auto-generated method stub
		String FILE_NAME = this.rootLocationExcel.toString() + '/' + originalFilename;
		FileInputStream fis = null;
		
		try {
//			fis = new FileInputStream(FILE_NAME);
//			Workbook workBook = new XSSFWorkbook(fis);
//			Sheet sheet = workBook.getSheetAt(0);
//			DataFormatter formatter = new DataFormatter();
//			Iterator<Row> rowIterator = sheet.rowIterator();
			
			fis = new FileInputStream(FILE_NAME);
			//format xls, jika xlsx pakai XSSFWorkBook
			Workbook workBook = new HSSFWorkbook(fis);
			//format xls, jika xlsx pakai XSSFWorkBook			
			Sheet sheet = workBook.getSheetAt(0);
			DataFormatter formatter = new DataFormatter();
			Iterator<Row> rowIterator = sheet.rowIterator();
			
			int i = 0;
			int nomor = 0;
			repository.deleteTempFocusSFA(userId);
			
	        while (rowIterator.hasNext()) {
	        	nomor++;
				try {
					Row row = rowIterator.next();
					if (row.getRowNum() != 0) { // Exclude header atau baris pertama
						Iterator<Cell> cellIterator = row.cellIterator();
						repository.insertTempFocusSFA(userId, nomor, (String) formatter.formatCellValue(sheet.getRow(i).getCell(2)), (String) formatter.formatCellValue(sheet.getRow(i).getCell(1)), (String) formatter.formatCellValue(sheet.getRow(i).getCell(0)));
//						while (cellIterator.hasNext()) {
////							Cell cell = cellIterator.next();						
//							
//								
//						}
					}
					 i++;
				} catch (Exception e) {
					// TODO: handle exception					
					i++;
				}
			} fis.close();	
			workBook.close();
		} catch (Exception e) {
			// TODO: handle exception			
		}
		
	}

	
}
