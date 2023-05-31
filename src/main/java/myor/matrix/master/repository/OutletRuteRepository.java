package myor.matrix.master.repository;

import java.util.List;

import myor.matrix.master.entity.OutletRuteDto;
import myor.matrix.master.entity.ReportOutletRuteDto;
import myor.matrix.master.entity.TotalOutletSalesmanDto;

public interface OutletRuteRepository {
	List<OutletRuteDto> getOutletRute(String custFrom, String custTo, String teamFrom, String teamTo, String slsFrom, String slsTo, String pil, String hari, String urut);
	List<TotalOutletSalesmanDto> getTotalOutlet(String salesman, String hari);
	void insertOutletRute(String kdsls, String namaSls, String data, String noUrut, String outlet, String namaOutlet, String alamat, String klimit, String sbeat, String hari, String pola, String rute, String userId);
	public List<ReportOutletRuteDto> getReport(String custFrom, String custTo, String teamFrom, String teamTo,
			String slsFrom, String slsTo, String pil, String hari, String urut, String userId);
	public void deleteTempReport(String userId);
}
