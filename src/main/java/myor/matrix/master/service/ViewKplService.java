package myor.matrix.master.service;

import java.util.List;

import myor.matrix.master.entity.ViewKplDto;

public interface ViewKplService {

	List<ViewKplDto> getDataDetail(String slsNo, String tgl, String chooseData);

}
