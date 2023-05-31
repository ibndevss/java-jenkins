package myor.matrix.master.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import myor.matrix.master.entity.FilterStandard;
import myor.matrix.master.entity.FilterStandardDto;
import myor.matrix.master.entity.InvoiceBrowseDto;
import myor.matrix.master.entity.OrderJualBrowseDto;
import myor.matrix.master.entity.RealOrderBrowseDto;
import myor.matrix.master.entity.RecapBrowseDto;
import myor.matrix.master.service.OrderService;

@RestController
@RequestMapping(path = "/order")
public class OrderRestController {
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping(path = "/browse-inv",params = {"transType"})
	public List<InvoiceBrowseDto> getBrowseInvoice(@RequestParam String transType){
		return orderService.getBrowseInvoice(transType);
	}
	
	@GetMapping(path = "/browse-ro",params = {"slsNo", "pilihan"})
	public List<RealOrderBrowseDto> getBrowseRealOrder(@RequestParam String slsNo, @RequestParam String pilihan){
		return orderService.getBrowseRealOrder(slsNo, pilihan);
	}
	
	@GetMapping(path = "/browse-ordjual",params = {"transType"})
	public List<OrderJualBrowseDto> getBrowseOrderJual(@RequestParam String transType){
		return orderService.getBrowseOrderJual(transType);
	}
	
	@GetMapping(path = "/browse-recap")
	public List<RecapBrowseDto> getBrowseRecap(){
		return orderService.getBrowseRecap();
	}
	
	@GetMapping(path = "/browse-ord")
	public List<RealOrderBrowseDto> getBrowseOrderRO(){
		return orderService.getBrowseOrderRO();
	}
	
	@GetMapping(path = "/browse-cancel-inv")
	public List<InvoiceBrowseDto> getBrowseCancelInvoice(){
		return orderService.getBrowseCancelInvoice();
	}
	
	@PutMapping(path="/browse-summary-order")
	public List<RecapBrowseDto> getBrowseSummaryOrder(@RequestBody FilterStandard fs) {
		return orderService.getBrowseSummaryOrder(fs);
	}
	
	@GetMapping(path="/browse-invoice", params = {"tglBayar"})
	public List<InvoiceBrowseDto> getBrowseInvoiceByTglBayar(@RequestParam String tglBayar) {
		return orderService.getBrowseInvoiceByTglBayar(tglBayar);
	}
	
	@PostMapping(path="/browse-invno")
	public List<InvoiceBrowseDto> getBrowseInvno2(@RequestBody FilterStandardDto fs){		
		try {
			return orderService.getBrowseInvoice(fs);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			if (e.getCause() != null) {
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause().getCause().getLocalizedMessage());
			} else {
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
			}
		}	
	}
	
	@GetMapping(path="/browse-summary-order2")
	public List<RecapBrowseDto> getBrowseSummaryOrder2() {
		try {
			return orderService.getBrowseSummaryOrder2();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			if (e.getCause() != null) {
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause().getCause().getLocalizedMessage());
			} else {
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
			}
		}	
	}
}
