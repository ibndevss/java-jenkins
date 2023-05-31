package myor.matrix.master.entity;

import java.util.List;

import myor.matrix.master.entity.MessageDto;

public class LaporanDiskonExtractDto {
	List<LaporanDiskonExtractSummaryPromoDto> summaryPromo;
	List<LaporanDiskonExtractSummaryPromoByAPDto> summaryPromoByAP;
	List<LaporanDiskonExtractSummaryDto> summary;
	List<LaporanDiskonExtractSummaryByAPDto> summaryByAP;
	List<MessageDto> message;
	
	public LaporanDiskonExtractDto() {
		// TODO Auto-generated constructor stub
	}

	public LaporanDiskonExtractDto(List<LaporanDiskonExtractSummaryPromoDto> summaryPromo,
			List<LaporanDiskonExtractSummaryPromoByAPDto> summaryPromoByAP,
			List<LaporanDiskonExtractSummaryDto> summary, List<LaporanDiskonExtractSummaryByAPDto> summaryByAP,
			List<MessageDto> message) {
		super();
		this.summaryPromo = summaryPromo;
		this.summaryPromoByAP = summaryPromoByAP;
		this.summary = summary;
		this.summaryByAP = summaryByAP;
		this.message = message;
	}

	public List<LaporanDiskonExtractSummaryPromoDto> getSummaryPromo() {
		return summaryPromo;
	}

	public void setSummaryPromo(List<LaporanDiskonExtractSummaryPromoDto> summaryPromo) {
		this.summaryPromo = summaryPromo;
	}

	public List<LaporanDiskonExtractSummaryPromoByAPDto> getSummaryPromoByAP() {
		return summaryPromoByAP;
	}

	public void setSummaryPromoByAP(List<LaporanDiskonExtractSummaryPromoByAPDto> summaryPromoByAP) {
		this.summaryPromoByAP = summaryPromoByAP;
	}

	public List<LaporanDiskonExtractSummaryDto> getSummary() {
		return summary;
	}

	public void setSummary(List<LaporanDiskonExtractSummaryDto> summary) {
		this.summary = summary;
	}

	public List<LaporanDiskonExtractSummaryByAPDto> getSummaryByAP() {
		return summaryByAP;
	}

	public void setSummaryByAP(List<LaporanDiskonExtractSummaryByAPDto> summaryByAP) {
		this.summaryByAP = summaryByAP;
	}

	public List<MessageDto> getMessage() {
		return message;
	}

	public void setMessage(List<MessageDto> message) {
		this.message = message;
	}
}
