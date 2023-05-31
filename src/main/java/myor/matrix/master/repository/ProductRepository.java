package myor.matrix.master.repository;

import java.util.List;

import myor.matrix.master.entity.GetChannelChoosen;
import myor.matrix.master.entity.GetOutletChoosen;
import myor.matrix.master.entity.GetPassword;
import myor.matrix.master.entity.GetProduct;
import myor.matrix.master.entity.HargaDto;
import myor.matrix.master.entity.ProductBrowseInPengajuanSODto;
import myor.matrix.master.entity.ProductBrowseDto;
import myor.matrix.master.entity.ProductBrowserHargaSpesifikDto;
import myor.matrix.master.entity.ProductChoosenDto;
import myor.matrix.master.entity.ProductDetailVanDto;
import myor.matrix.master.entity.ProductVanBrowseDto;
import myor.matrix.master.entity.SelectItem;

public interface ProductRepository {
	
	public void deleteData(String tableName, String where);
	
	public List<SelectItem<String>> getListProductSelectItem();

	public ProductChoosenDto getProductChoosen(String pcode);
	
	public List<GetChannelChoosen> getChannelChoosen(String pcode);
	
	public List<GetOutletChoosen> getOutletChoosen(String pcode);
	
	public List<GetProduct> getAllProduct();
	
	public GetPassword getPassword(String id);
	
	public void updateJatah(String pcode, Boolean check);
	
	public ProductDetailVanDto getDetail(String slsno, String pcode);
	
	public List<ProductVanBrowseDto> getBrowsePcodeVan(String slsno);
	
	public List<ProductBrowseDto> getBrowsePcodeRO(String slsNo, String tipeOpr, String team);
	
	public List<ProductBrowseDto> getBrowsePcodeStockOutlet(String slsNo);
	
	public List<ProductBrowseDto> getBrowseAllPcode();

	public List<ProductBrowserHargaSpesifikDto> getViewBrowserHargaSpefisik();
	
	public List<ProductBrowseDto> getBrowsePcodeGudangUtama();
	
	public List<ProductBrowseDto> getBrowsePcodeGudangTambahan(String kdGudang);	
	
	public List<ProductBrowseDto> getBrowsePcodeOpname(String kdGudang);
		
	public List<SelectItem<String>> getListPcodeOpnameSelectItem(String kdGudang);
	
	public HargaDto getHargaProductHirarki(String pcode, String tgl, String custNo, String groupOut, String salesForce,
			String channel, String groupHarga);
	
	public HargaDto getSellPriceProduct(String pcode);
	
	public String getHargaProductHirarkiPerc(String pcode, String divisi, String tgl, String custNo, String groupOut,
			String salesForce, String channel, String groupHarga);
	
	public String getDivisiProduct(String pcode);
	
	public Integer getHargaData6Spesifik2(String pcode, String xDivisi);

	public List<ProductBrowseDto> getBrowsePcodeDN(String nomorDN);
	
	public List<ProductBrowseDto> getBrowseInPengajuanRetur(String slsNo, String slsType, String slsTeam);
	
	public List<ProductBrowseInPengajuanSODto> getBrowseInPengajuanSO(String slsNo, String slsType);
}
