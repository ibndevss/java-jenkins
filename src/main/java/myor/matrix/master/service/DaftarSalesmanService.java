package myor.matrix.master.service;

import java.util.List;

import myor.matrix.master.entity.FilterStandard;
import myor.matrix.master.entity.FormatExtractDaftarSalesmanDto;
import myor.matrix.master.entity.ListAttributeDaftarSalesmanDto;

public interface DaftarSalesmanService {

	List<ListAttributeDaftarSalesmanDto> getListAttribute();

	int cekDataAttribute();

	void insertAttribute();

	List<FormatExtractDaftarSalesmanDto> getDefaultFormat(List<String> tmp);

	void insertTempReport(FilterStandard fs);

	String getPreview(FilterStandard fs, List<String> list);

	List<FormatExtractDaftarSalesmanDto> getExtractFormat(FilterStandard fs, List<String> list);

}
