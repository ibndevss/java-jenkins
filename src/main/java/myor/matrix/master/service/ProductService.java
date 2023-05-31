package myor.matrix.master.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import myor.matrix.master.entity.GetPassword;
import myor.matrix.master.entity.GetProduct;
import myor.matrix.master.entity.HargaDto;
import myor.matrix.master.entity.ProductBrowseInPengajuanSODto;
import myor.matrix.master.entity.ProductBrowseDto;
import myor.matrix.master.entity.ProductBrowserHargaSpesifikDto;
import myor.matrix.master.entity.ProductDetailVanDto;
import myor.matrix.master.entity.ProductDto;
import myor.matrix.master.entity.ProductVanBrowseDto;
import myor.matrix.master.entity.SelectItem;

public interface ProductService {
	public List<SelectItem<String>> getListSelectItemProduct();
	
	public ProductDto getProductDto(String pcode);
	
	public List<GetProduct> getAllProduct();
	
	public List<GetProduct> updateJatah(List<GetProduct> dataProducts);
	
	public GetPassword getPassword(String id);
	
	public ProductDetailVanDto getDetail(String slsno, String pcode);
	
	public List<ProductVanBrowseDto> getBrowseVanPcode(String slsno);
	
	public List<ProductBrowseDto> getBrowsePcodeRO(String slsNo, String tipeOpr, String team);
	
	public List<ProductBrowseDto> getBrowsePcodeStockOutlet(String slsNo);
	
	public List<ProductBrowseDto> getBrowseAllPcode();

	public List<ProductBrowserHargaSpesifikDto> getViewBrowserHargaSpefisik();
	
	public List<ProductBrowseDto> getBrowsePcodeByGudang (String kdGudang);
	
	public List<ProductBrowseDto> getBrowsePcodeOpname(String kdGudang);
	
	public List<SelectItem<String>> getListPcodeOpnameSelectItem(String kdGudang);
	
	public Double getPricing(String kode, String pcode, String custNo, String cekDateUpload, String slsNo);
	
	public Integer getPricing2(String pcode, String custNo, String cekDateUpload, String slsNo);
	
	public List<ProductBrowseDto> getBrowsePcodeDN(String nomorDN);

	public List<ProductBrowseDto> getBrowseInPengajuanRetur(String slsNo, String slsType, String slsTeam);
	
	public List<ProductBrowseInPengajuanSODto> getBrowseInPengajuanSO(String slsNo, String slsType);
}
