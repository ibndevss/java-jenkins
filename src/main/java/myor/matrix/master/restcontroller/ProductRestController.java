package myor.matrix.master.restcontroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
import myor.matrix.master.service.ProductService;

@RestController
@RequestMapping(path = "/master-product")
public class ProductRestController {

	@Autowired
	private ProductService productService;
	
	@GetMapping(path = "/upd-module")
	public List<String> getUpdModule() {
		List<String> result = new ArrayList<String>();
		result.add("06 MARCH 2023");
		return result;
	}

	@GetMapping(path = "/select-item")
	public List<SelectItem<String>> getListProduct() {
		return productService.getListSelectItemProduct();
	}

	@GetMapping(value = "/{pcode}")
	public ProductDto getProductDto(@PathVariable(name = "pcode") String pcode) {
		return productService.getProductDto(pcode);
	}

	@GetMapping(path = "/product")
	public List<GetProduct> getAllProduct() {
		return productService.getAllProduct();
	}

	@PutMapping(path = "/update")
	public List<GetProduct> updateJatah(@RequestBody List<GetProduct> dataProducts) {
		return productService.updateJatah(dataProducts);
	}

	@GetMapping(value = "/pass/{id}")
	public GetPassword getPassword(@PathVariable(name = "id") String id) {
		return productService.getPassword(id);
	}

	@GetMapping(path = "/{slsno}/{pcode}")
	public ProductDetailVanDto getDetail(@PathVariable String slsno, @PathVariable String pcode) {
		return productService.getDetail(slsno, pcode);
	}

	@GetMapping(path = "/van/{slsno}")
	public List<ProductVanBrowseDto> getBrowsePcodeVan(@PathVariable String slsno) {
		return productService.getBrowseVanPcode(slsno);
	}
	
	@GetMapping(path = "/ro", params = {"slsNo", "tipeOpr", "team"})
	public List<ProductBrowseDto> getBrowsePcodeRO(@RequestParam String slsNo, @RequestParam String tipeOpr,
			@RequestParam String team) {
		return productService.getBrowsePcodeRO(slsNo, tipeOpr, team);
	}
	
	@GetMapping(path = "/stock-outlet", params = {"slsNo"})
	public List<ProductBrowseDto> getBrowsePcodeStockOutlet(@RequestParam String slsNo) {
		return productService.getBrowsePcodeStockOutlet(slsNo);
	}
	
	@GetMapping(path = "/browse")
	public List<ProductBrowseDto> getBrowseAllPcode() {
		return productService.getBrowseAllPcode();
	}
	
	// Tommy M22-
	@GetMapping(path = "/browser-view-harga-spesifik")
	public List<ProductBrowserHargaSpesifikDto> getViewBrowserHargaSpefisik() {
		return productService.getViewBrowserHargaSpefisik();
	}
	
	@GetMapping(path = "/browse", params = {"kdGudang"})
	public List<ProductBrowseDto> getBrowsePcodeByGudang(@RequestParam String kdGudang) {
		return productService.getBrowsePcodeByGudang(kdGudang);
	}
	
	@GetMapping(path = "/browse-opname", params = {"kdGudang"})
	public List<ProductBrowseDto> getBrowsePcodeOpname(@RequestParam String kdGudang) {
		return productService.getBrowsePcodeOpname(kdGudang);
	}
	
	@GetMapping(path = "/select-item-opname",params = {"kdGudang"})
	public List<SelectItem<String>> getListPcodeOpnameSelectItem(@RequestParam String kdGudang) {
		return productService.getListPcodeOpnameSelectItem(kdGudang);
	}
	
	@GetMapping(path = "/pricing", params = { "pcode", "custNo", "cekDateUpload", "slsNo" })
	public HargaDto getPricing(@RequestParam String pcode, @RequestParam String custNo,
			@RequestParam String cekDateUpload, @RequestParam String slsNo) {
		
		HargaDto result = new HargaDto();
		
		result.setHargaBesar(productService.getPricing("1", pcode, custNo, cekDateUpload, slsNo));
		result.setHargaTengah(productService.getPricing("2", pcode, custNo, cekDateUpload, slsNo));
		result.setHargaKecil(productService.getPricing("3", pcode, custNo, cekDateUpload, slsNo));
		
		return result;
	}
	
	@GetMapping(path="/browse-dn",params = {"nomorDN"})
	public List<ProductBrowseDto> getBrowsePcodeDN(@RequestParam String nomorDN) {
		return productService.getBrowsePcodeDN(nomorDN);
	}
	
	@GetMapping(path = "/browse/pengajuan-retur", params = {"slsNo", "slsType", "slsTeam"})
	public List<ProductBrowseDto> getBrowseInPengajuanRetur(@RequestParam String slsNo, @RequestParam String slsType, @RequestParam String slsTeam) {
		return productService.getBrowseInPengajuanRetur(slsNo, slsType, slsTeam);
	}
	
	@GetMapping(path = "/browse/pengajuan-so", params = {"slsno", "slstype"})
	public List<ProductBrowseInPengajuanSODto> getBrowseInPengajuanRetur(@RequestParam String slsno, @RequestParam String slstype) {
		return productService.getBrowseInPengajuanSO(slsno, slstype);
	}

}
