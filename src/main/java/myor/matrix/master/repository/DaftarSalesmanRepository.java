package myor.matrix.master.repository;

import java.util.List;

import myor.matrix.master.entity.FormatExtractDaftarSalesmanDto;
import myor.matrix.master.entity.ListAttributeDaftarSalesmanDto;

public interface DaftarSalesmanRepository {

	List<ListAttributeDaftarSalesmanDto> getListAttribute();

	int cekDataAttribute();

	void insertAttribute();

	List<FormatExtractDaftarSalesmanDto> getDefaultFormat(List<String> list);

	List<String> getColumnField(List<String> list);

	List<FormatExtractDaftarSalesmanDto> getSelectByColumn(List<String> datas, List<String> datasHeader, String salesFrom, String salesTo, String sforceFrom, String sforceTo, 
				String teamFrom, String teamTo, String rayonFrom, String rayonTo, String pilihData);


}
