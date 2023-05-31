package myor.matrix.master.restcontroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import myor.matrix.master.entity.ApExcludeDto;
import myor.matrix.master.entity.DiscApBrowseDto;
import myor.matrix.master.entity.DiscPromoDto;
import myor.matrix.master.entity.DiscViewDto;
import myor.matrix.master.entity.DiskonBertingkatDto;
import myor.matrix.master.service.PromotionDiscNewService;

@RestController
@RequestMapping(path = "/promotion-disc-new")
public class PromotionDiscNewRestController {
	
	@Autowired
	PromotionDiscNewService promotionDiscNewService;
	
	@GetMapping(path = "/upd-module")
	public List<String> getUpdModule() {
		List<String> result = new ArrayList<String>();
		result.add("21 SEP 2022");
		return result;
	}
	
	@GetMapping(path = "/browse")
	public List<DiscApBrowseDto> getListProduct() {
		return promotionDiscNewService.getPromoDiscApBrowse();
	}

	@GetMapping(value = "/view", params={"ap"})
	public DiscViewDto viewAp(@RequestParam(name = "ap") String ap) {
		return promotionDiscNewService.getPromoDiscNew(ap);
	}
	
	@GetMapping(value = "/exclude", params={"ap"})
	public List<ApExcludeDto> viewApExclude(@RequestParam(name = "ap") String ap) {
		return promotionDiscNewService.getApExclude(ap);
	}
	
	@GetMapping(value = "/bertingkat", params={"ap"})
	public List<DiskonBertingkatDto> viewApBertingkat(@RequestParam(name = "ap") String ap) {
		return promotionDiscNewService.getDiskonBertingkat(ap);
	}
	
	@GetMapping(value = "/dataspromo", params={"ap"})
	public List<DiscPromoDto> getDataPromo(@RequestParam(name = "ap") String ap) {
		return promotionDiscNewService.getDataPromo(ap);
	}
	
	@PutMapping(path = "/update-data-promo")
	public List<DiscPromoDto> updateDataPromo(@RequestBody List<DiscPromoDto> dataPromo){
		return promotionDiscNewService.updateDataPromo(dataPromo);
	}

}
