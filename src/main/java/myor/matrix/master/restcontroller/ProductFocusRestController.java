package myor.matrix.master.restcontroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
import myor.matrix.master.service.ProductFocusService;

@RestController
@RequestMapping(path = "/product-focus")
public class ProductFocusRestController {

	@Autowired
	private ProductFocusService service;

	@GetMapping(path = "/browse-focus-sfa")
	public List<ProductFocusBrowseTahunDto> getBrowseFocusSFA() {
		return service.getBrowseFocusSFA();
	}

	@GetMapping(path = "/browse-focus-stock")
	public List<ProductFocusBrowseTahunDto> getBrowseFocusStock() {
		return service.getBrowseFocusStock();
	}

	@GetMapping(path = "/load")
	public ProductFocusLoadDataDto loadFirstData() {
		return service.loadFirstData();
	}

	@GetMapping(path = "/load-sfa/{tahun}/{periode}")
	public List<ProductFocusDetailDto> getDetailSFA(@PathVariable String tahun, @PathVariable String periode) {
		return service.getDetailSFA(tahun, periode);
	}
	
	@PostMapping("/upload-sfa/{userId}/")
	public ProductFocusUploadSFADto uploadFocusSFANew(@RequestParam("file") MultipartFile file, @PathVariable String userId) {
		return service.uploadFocusSFA(file, userId);
	}

	@GetMapping(path = "/load-stock/{tahun}/{periode}")
	public List<ProductFocusDetailStockDto> getDetailStock(@PathVariable String tahun, @PathVariable String periode) {
		return service.getDetailStock(tahun, periode);
	}

	@PostMapping(path = "/insert-sfa/{tahun}/{periode}/{pcode}")
	public ProductFocusLoadDetailDto insertFocusSFA(@PathVariable String tahun, @PathVariable String periode,
			@PathVariable String pcode) {
		return service.insertFocusSFA(tahun, periode, pcode);
	}

	@PostMapping(path = "/insert-stock")
	public ProductFocusLoadDetailStockDto insertFocusStock(@RequestBody ProductFocusFilterDto fs) {
		return service.insertFocusStock(fs);
	}	

	@PostMapping(path = "/del-sfa/{tahun}/{periode}/{pcode}")
	public ProductFocusLoadDetailDto deleteFocusSFA(@PathVariable String tahun, @PathVariable String periode,
			@PathVariable String pcode) {
		return service.deleteFocusSFA(tahun, periode, pcode);
	}

	@PostMapping(path = "/del-stock")
	public ProductFocusLoadDetailStockDto deleteFocusStock(@RequestBody ProductFocusFilterDto fs) {
		return service.deleteFocusStock(fs);
	}

	@PostMapping(path = "/insert-minorder/{pcode}/{tglAwal}/{tglAkhir}/{minOrder}/{userId}")
	public ProductFocusLoadMinOrder insertMinOrder(@PathVariable String pcode, @PathVariable String tglAwal,
			@PathVariable String tglAkhir, @PathVariable String minOrder, @PathVariable String userId) {
		return service.insertMinOrder(pcode, tglAwal, tglAkhir, minOrder, userId);
	}
	
	@PostMapping(path = "/upd-minorder/{pcode}/{tglAwal}/{tglAkhir}/{minOrder}/{userId}")
	public ProductFocusLoadMinOrder updateMinOrder(@PathVariable String pcode, @PathVariable String tglAwal,
			@PathVariable String tglAkhir, @PathVariable String minOrder, @PathVariable String userId) {
		return service.updateMinOrder(pcode, tglAwal, tglAkhir, minOrder, userId);
	}
	
	@PostMapping(path = "/del-minorder/{pcode}")
	public ProductFocusLoadMinOrder deleteMinOrder(@PathVariable String pcode) {
		return service.deleteMinOrder(pcode);
	}
	
	@GetMapping(path = "/upd-module")
	public List<String> getUpdModule(){
		List<String> result = new ArrayList<String>();
		result.add("09 Dec 2022");
		return result;
	}
}
