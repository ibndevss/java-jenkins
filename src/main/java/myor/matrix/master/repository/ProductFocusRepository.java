package myor.matrix.master.repository;

import java.util.List;

import myor.matrix.master.entity.ProductFocusBrowseTahunDto;
import myor.matrix.master.entity.ProductFocusDetailDto;
import myor.matrix.master.entity.ProductFocusDetailStockDto;
import myor.matrix.master.entity.ProductFocusHeaderDto;
import myor.matrix.master.entity.ProductFocusMinOrderDto;

public interface ProductFocusRepository {
	public List<ProductFocusBrowseTahunDto> getBrowseFocusSFA();

	public List<ProductFocusBrowseTahunDto> getBrowseFocusStock();
	
	public ProductFocusHeaderDto getMaxFocusSFA();		
	
	public ProductFocusHeaderDto getMaxFocusStock();
	
	public List<ProductFocusDetailDto> getDetailFocusSFA(String tahun, String periode);
	
	public List<ProductFocusDetailStockDto> getDetailFocusStock(String tahun, String periode);
	
	public List<ProductFocusMinOrderDto> getDataMinOrder();
	
	public boolean isFocusSFAExist(String tahun, String periode, String pcode);
	
	public void insertFocusSFA(String tahun, String periode, String tipe, String data, String pcode);
	
	public boolean isFocusStockExist(String tahun, String periode, String pcode, String salesforce, String team,
			String channel, String tglAwal, String tglAkhir);
	
	public void insertFocusStock(String tahun, String periode, String tipe, String data, String pcode,
			String salesforce, String team, String channel, String tglAwal, String tglAkhir, String flag);
	
	public void deleteFocusSFA(String tahun, String periode, String pcode);
	
	public void deleteFocusStock(String tahun, String periode, String pcode, String salesforce, String team,
			String channel, String tglAwal, String tglAkhir);
	
	public boolean isMinOrderExist(String pcode);
	
	public void insertMinOrder(String pcode, String tglAwal, String tglAkhir, String minOrder, String userId);
	
	public void updateMinOrder(String pcode, String tglAwal, String tglAkhir, String minOrder, String userId);
	
	public void deleteMinOrder(String pcode);
	
	public void deleteTempFocusSFA(String userId);
	
	public void insertTempFocusSFA(String userId, Integer noBaris, String tahun, String periode, String pcode);
	
	public List<String> validasiUploadFocusSFA(String userId);
	
	public void insertFocusSFAUpload(String userId);
	
	public String getStartWeek();
	
	public String getEndWeek();
	
	public void insertFocusStockAuto(String tahun, String periode, String tipe, String data, String salesforce,
			String team, String channel, String tglAwal, String tglAkhir, String flag, String startWeek,
			String endWeek);
	
	public boolean cekInsertStockAuto(String tahun, String periode, String tipe, String data, String salesforce,
			String team, String channel, String tglAwal, String tglAkhir, String flag, String startWeek,
			String endWeek);
	
}
