package myor.matrix.master.service;

import java.util.List;

import myor.matrix.master.entity.FilterStandard;
import myor.matrix.master.entity.FilterStandardDto;
import myor.matrix.master.entity.InvoiceBrowseDto;
import myor.matrix.master.entity.OrderJualBrowseDto;
import myor.matrix.master.entity.RealOrderBrowseDto;
import myor.matrix.master.entity.RecapBrowseDto;

public interface OrderService {
	
	public List<InvoiceBrowseDto> getBrowseInvoice(String transType);
	
	public List<RealOrderBrowseDto> getBrowseRealOrder(String slsNo, String pilihan);
	
	public List<OrderJualBrowseDto> getBrowseOrderJual(String transType);
	
	public List<RecapBrowseDto> getBrowseRecap();
	
	public List<RealOrderBrowseDto> getBrowseOrderRO();
	
	public List<InvoiceBrowseDto> getBrowseCancelInvoice();
	
	public List<RecapBrowseDto> getBrowseSummaryOrder(FilterStandard fs);
	
	public List<InvoiceBrowseDto> getBrowseInvoiceByTglBayar(String tglBayar);
	
	public List<InvoiceBrowseDto> getBrowseInvoice(FilterStandardDto fs);
	
	public List<RecapBrowseDto> getBrowseSummaryOrder2();
}
