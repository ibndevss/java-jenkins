package myor.matrix.master.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import myor.matrix.master.entity.ProductFocusBrowseTahunDto;
import myor.matrix.master.entity.ProductFocusDetailDto;
import myor.matrix.master.entity.ProductFocusDetailStockDto;
import myor.matrix.master.entity.ProductFocusFilterDto;
import myor.matrix.master.entity.ProductFocusLoadDataDto;
import myor.matrix.master.entity.ProductFocusLoadDetailDto;
import myor.matrix.master.entity.ProductFocusLoadDetailStockDto;
import myor.matrix.master.entity.ProductFocusLoadMinOrder;
import myor.matrix.master.entity.ProductFocusUploadSFADto;

public interface ProductFocusService {
	public List<ProductFocusBrowseTahunDto> getBrowseFocusSFA();

	public List<ProductFocusBrowseTahunDto> getBrowseFocusStock();
	
	public ProductFocusLoadDataDto loadFirstData();
	
	public List<ProductFocusDetailDto> getDetailSFA(String tahun, String periode);
	
	public ProductFocusUploadSFADto uploadFocusSFA(MultipartFile file, String userId);
	
	public void readUploadSFA(String userId, String originalFilename);
	
	public List<ProductFocusDetailStockDto> getDetailStock(String tahun, String periode);
	
	public ProductFocusLoadDetailDto insertFocusSFA(String tahun, String periode, String pcode);
	
	public ProductFocusLoadDetailStockDto insertFocusStock(ProductFocusFilterDto fs);
	
	public ProductFocusLoadDetailDto deleteFocusSFA(String tahun, String periode, String pcode);
	
	public ProductFocusLoadDetailStockDto deleteFocusStock(ProductFocusFilterDto fs);
	
	public ProductFocusLoadMinOrder insertMinOrder(String pcode, String tglAwal, String tglAkhir, String minOrder, String userId);
	
	public ProductFocusLoadMinOrder updateMinOrder(String pcode, String tglAwal, String tglAkhir, String minOrder, String userId);
	
	public ProductFocusLoadMinOrder deleteMinOrder(String pcode);
	
}
