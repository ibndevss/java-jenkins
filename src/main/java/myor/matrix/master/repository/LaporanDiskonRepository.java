package myor.matrix.master.repository;

import java.util.List;

import myor.matrix.master.entity.LaporanDiskonBrowseAPDto;
import myor.matrix.master.entity.LaporanDiskonExtractSummaryByAPDto;
import myor.matrix.master.entity.LaporanDiskonExtractSummaryPromoByAPDto;
import myor.matrix.master.entity.LaporanDiskonExtractSummaryDto;
import myor.matrix.master.entity.LaporanDiskonExtractSummaryPromoDto;
import myor.matrix.master.entity.LaporanDiskonReportDetailDto;
import myor.matrix.master.entity.LaporanDiskonReportDetailPromoDto;
import myor.matrix.master.entity.LaporanDiskonReportSummaryByAPDto;
import myor.matrix.master.entity.LaporanDiskonReportSummaryDto;
import myor.matrix.master.entity.LaporanDiskonReportSummaryPromoByAPDto;
import myor.matrix.master.entity.LaporanDiskonReportSummaryPromoDto;

public interface LaporanDiskonRepository {
	public List<LaporanDiskonBrowseAPDto> getBrowseAP(String flagHierarki, String tipeDiskon);

	public void deleteTemp(String tableName, String userId);

	public List<LaporanDiskonReportSummaryPromoDto> getReportSummaryPromo(String pilihanReport, String tipeDiskon,
			String flagKlaim, String filterProduk, String pcodeFrom, String pcodeTo, String pcodeIn, String custnoFrom,
			String custnoTo, String kodeAPFrom, String kodeAPTo, String invDateFrom, String invDateTo);

	public List<LaporanDiskonReportSummaryPromoDto> getReportSummaryPromoHierarki(String pilihanReport,
			String tipeDiskon, String flagKlaim, String filterProduk, String pcodeFrom, String pcodeTo, String pcodeIn,
			String custnoFrom, String custnoTo, String kodeAPFrom, String kodeAPTo, String invDateFrom,
			String invDateTo);

	public List<LaporanDiskonReportSummaryPromoByAPDto> getReportSummaryPromoByAP(String pilihanReport,
			String tipeDiskon, String flagKlaim, String filterProduk, String pcodeFrom, String pcodeTo, String pcodeIn,
			String custnoFrom, String custnoTo, String kodeAPFrom, String kodeAPTo, String invDateFrom,
			String invDateTo);

	public List<LaporanDiskonReportSummaryPromoByAPDto> getReportSummaryPromoByAPHierarki(String pilihanReport,
			String tipeDiskon, String flagKlaim, String filterProduk, String pcodeFrom, String pcodeTo, String pcodeIn,
			String custnoFrom, String custnoTo, String kodeAPFrom, String kodeAPTo, String invDateFrom,
			String invDateTo);

	public List<Object[]> getListAPPromo(String flagHierarki, String tipeDiskon, String flagKlaim, String filterProduk, String pcodeFrom,
			String pcodeTo, String pcodeIn, String custnoFrom, String custnoTo, String kodeAPFrom, String kodeAPTo,
			String invDateFrom, String invDateTo);

	public List<Object[]> getListAPInvnoPromo(String flagHierarki, String tipeDiskon, String flagKlaim, String filterProduk,
			String pcodeFrom, String pcodeTo, String pcodeIn, String custnoFrom, String custnoTo, String kodeAP,
			String invDateFrom, String invDateTo);

	public List<Object[]> getListDetailPromoKiri(String flagHierarki, String tipeDiskon, String flagKlaim, String filterProduk,
			String pcodeFrom, String pcodeTo, String pcodeIn, String custnoFrom, String custnoTo, String invno,
			String kodeAP);

	public List<Object[]> getListDetailPromoKanan(String flagHierarki, String tipeDiskon, String flagKlaim, String filterProduk,
			String pcodeFrom, String pcodeTo, String pcodeIn, String custnoFrom, String custnoTo, String invno,
			String kodeAP);

	public List<Object[]> getListDetailPromo(String flagHierarki, String tipeDiskon, String flagKlaim, String filterProduk, String pcodeFrom,
			String pcodeTo, String pcodeIn, String custnoFrom, String custnoTo, String invno, String kodeAP,
			String join);

	public List<Object[]> getTotalPromoKiri(String flagHierarki, String tipeDiskon, String flagKlaim, String filterProduk, String pcodeFrom,
			String pcodeTo, String pcodeIn, String custnoFrom, String custnoTo, String kodeAP, String invDateFrom,
			String invDateTo);

	public List<Object[]> getTotalPromoKanan(String flagHierarki, String tipeDiskon, String flagKlaim, String filterProduk, String pcodeFrom,
			String pcodeTo, String pcodeIn, String custnoFrom, String custnoTo, String kodeAP, String invDateFrom,
			String invDateTo);

	public List<Object[]> getTotalPromo(String flagHierarki, String tipeDiskon, String flagKlaim, String filterProduk, String pcodeFrom,
			String pcodeTo, String pcodeIn, String custnoFrom, String custnoTo, String kodeAP, String invDateFrom,
			String invDateTo, String join);

	public void insertTempPromo(String flag, String userId, Integer urut, String kodeAP, String invno, String invDate,
			String slsno, String slsName, String custno, String custName, String pctpr, String pctprName, String besar,
			String tengah, String kecil, String tprAmount, String pcode, String pcodeName, String qty, String amount);

	public List<LaporanDiskonReportDetailPromoDto> getReportDetailPromo(String pilihanReport, String tipeDiskon,
			String flagKlaim, String kodeAPFrom, String kodeAPTo, String invDateFrom, String invDateTo, String userId);
	
	public List<LaporanDiskonReportDetailDto> getReportDetail(String flagHierarki, String pilihanReport, String tipeDiskon, String flagKlaim,
			String filterProduk, String pcodeFrom, String pcodeTo, String pcodeIn, String custnoFrom, String custnoTo,
			String kodeAPFrom, String kodeAPTo, String invDateFrom, String invDateTo);
	
	public List<LaporanDiskonReportSummaryDto> getReportSummary(String flagHierarki, String pilihanReport,
			String tipeDiskon, String flagKlaim, String filterProduk, String pcodeFrom, String pcodeTo, String pcodeIn,
			String custnoFrom, String custnoTo, String kodeAPFrom, String kodeAPTo, String invDateFrom,
			String invDateTo);
	
	public List<LaporanDiskonReportSummaryByAPDto> getReportSummaryByAP(String flagHierarki, String pilihanReport,
			String tipeDiskon, String flagKlaim, String filterProduk, String pcodeFrom, String pcodeTo, String pcodeIn,
			String custnoFrom, String custnoTo, String kodeAPFrom, String kodeAPTo, String invDateFrom,
			String invDateTo);
	
	public List<LaporanDiskonExtractSummaryPromoDto> extractSummaryPromo(String flagHierarki, String tipeDiskon,
			String flagKlaim, String filterProduk, String pcodeFrom, String pcodeTo, String pcodeIn, String custnoFrom,
			String custnoTo, String kodeAPFrom, String kodeAPTo, String invDateFrom, String invDateTo);
	
	public List<LaporanDiskonExtractSummaryPromoByAPDto> extractSummaryPromoByAP(String flagHierarki, String tipeDiskon,
			String flagKlaim, String filterProduk, String pcodeFrom, String pcodeTo, String pcodeIn, String custnoFrom,
			String custnoTo, String kodeAPFrom, String kodeAPTo, String invDateFrom, String invDateTo);
	
	public List<LaporanDiskonExtractSummaryDto> extractSummary(String flagHierarki, String tipeDiskon, String flagKlaim,
			String filterProduk, String pcodeFrom, String pcodeTo, String pcodeIn, String custnoFrom, String custnoTo,
			String kodeAPFrom, String kodeAPTo, String invDateFrom, String invDateTo);
	
	public List<LaporanDiskonExtractSummaryByAPDto> extractSummaryByAP(String flagHierarki, String tipeDiskon,
			String flagKlaim, String filterProduk, String pcodeFrom, String pcodeTo, String pcodeIn, String custnoFrom,
			String custnoTo, String kodeAPFrom, String kodeAPTo, String invDateFrom, String invDateTo);
}
