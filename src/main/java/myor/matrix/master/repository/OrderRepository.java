package myor.matrix.master.repository;

import java.util.List;

import myor.matrix.master.entity.InvoiceBrowseDto;
import myor.matrix.master.entity.OrderJualBrowseDto;
import myor.matrix.master.entity.RealOrderBrowseDto;
import myor.matrix.master.entity.RecapBrowseDto;

public interface OrderRepository {
	
	public List<InvoiceBrowseDto> getBrowseInvoice(String transType);
	
	public List<RealOrderBrowseDto> getBrowseRealOrder(String slsNo, String pilihan);
	
	public List<OrderJualBrowseDto> getBrowseOrderJual(String transType);
	
	public List<RecapBrowseDto> getBrowseRecap();
	
	public List<RealOrderBrowseDto> getBrowseOrderRO();
	
	public List<InvoiceBrowseDto> getBrowseCancelInvoice();
	
	public List<RecapBrowseDto> getBrowseSummaryOrder(String tglGudang, String xHariLibur, int xMinPengiriman, 
			String slsnoFrom, String slsnoTo, String invDateFrom, String invDateTo, String dueDateFrom, String dueDateTo, 
			String invnoFrom, String invnoTo);
	
	public List<InvoiceBrowseDto> getBrowseInvoiceByTglBayar(String tglBayar);
	
	public List<InvoiceBrowseDto> getBrowseInvoice2(String transType, String invnoFrom, String invnoTo,
			String slsnoFrom, String slsnoTo, String custnoFrom, String custnoTo);
	
	public List<RecapBrowseDto> getBrowseSummaryOrder2(String tglGudang);
}
