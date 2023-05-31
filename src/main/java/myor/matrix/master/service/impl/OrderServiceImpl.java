package myor.matrix.master.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myor.matrix.master.entity.FilterStandard;
import myor.matrix.master.entity.FilterStandardDto;
import myor.matrix.master.entity.InvoiceBrowseDto;
import myor.matrix.master.entity.OrderJualBrowseDto;
import myor.matrix.master.entity.RealOrderBrowseDto;
import myor.matrix.master.entity.RecapBrowseDto;
import myor.matrix.master.repository.OrderRepository;
import myor.matrix.master.service.OrderService;
import myor.matrix.master.util.Util;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	Util util;
	
	@Override
	public List<InvoiceBrowseDto> getBrowseInvoice(String transType) {
		// TODO Auto-generated method stub		
		return orderRepository.getBrowseInvoice(transType);
	}

	@Override
	public List<RealOrderBrowseDto> getBrowseRealOrder(String slsNo, String pilihan) {
		// TODO Auto-generated method stub
		return orderRepository.getBrowseRealOrder(slsNo, pilihan);
	}
	
	@Override
	public List<OrderJualBrowseDto> getBrowseOrderJual(String transType) {
		// TODO Auto-generated method stub
		return orderRepository.getBrowseOrderJual(transType);
	}

	@Override
	public List<RecapBrowseDto> getBrowseRecap() {
		// TODO Auto-generated method stub
		return orderRepository.getBrowseRecap();
	}
	
	@Override
	public List<RealOrderBrowseDto> getBrowseOrderRO() {
		// TODO Auto-generated method stub
		return orderRepository.getBrowseOrderRO();
	}

	@Override
	public List<InvoiceBrowseDto> getBrowseCancelInvoice() {
		// TODO Auto-generated method stub
		return orderRepository.getBrowseCancelInvoice();
	}

	@Override
	public List<RecapBrowseDto> getBrowseSummaryOrder(FilterStandard fs) {
		// TODO Auto-generated method stub
		String tglGudang = util.getTglGudang();
		String xHariLibur = util.getParamLibur();
		int xMinPengiriman = util.getMinPengiriman();
		
		return orderRepository.getBrowseSummaryOrder(tglGudang, xHariLibur, xMinPengiriman, fs.getSlsnoFrom(), fs.getSlsnoTo(), 
				fs.getInvDateFrom(), fs.getInvDateTo(), fs.getDueDateFrom(), fs.getDueDateTo(), fs.getInvnoFrom(), fs.getInvnoTo());
	}

	@Override
	public List<InvoiceBrowseDto> getBrowseInvoiceByTglBayar(String tglBayar) {
		// TODO Auto-generated method stub
		return orderRepository.getBrowseInvoiceByTglBayar(tglBayar);
	}
	
	@Override
	public List<InvoiceBrowseDto> getBrowseInvoice(FilterStandardDto fs) {
		// TODO Auto-generated method stub
		String transtype = fs.getTranstype();
		String invnoFrom = fs.getInvnoFrom();
		String invnoTo = fs.getInvnoTo();
		String slsnoFrom = fs.getSlsnoFrom();
		String slsnoTo = fs.getSlsnoTo();
		String custnoFrom = fs.getCustnoFrom();
		String custnoTo = fs.getCustnoTo();
		
		return orderRepository.getBrowseInvoice2(transtype, invnoFrom, invnoTo, slsnoFrom, slsnoTo, custnoFrom,
				custnoTo);
	}

	@Override
	public List<RecapBrowseDto> getBrowseSummaryOrder2() {
		// TODO Auto-generated method stub
		String tglGudang = util.getTglGudang();
						
		return orderRepository.getBrowseSummaryOrder2(tglGudang);
	}
	
	

}
