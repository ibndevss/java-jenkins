package myor.matrix.master.repository;

import java.util.List;

import myor.matrix.master.entity.ViewKplDto;

public interface ViewKplRepository {

	List<ViewKplDto> getDataDetailAll(String slsNo, String tgl);

	List<ViewKplDto> getDataDetailPcodeKpl(String slsNo, String tgl);


}
