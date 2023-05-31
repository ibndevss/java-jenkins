package myor.matrix.master.service;

import java.util.List;

import myor.matrix.master.entity.DNBrowseDto;

public interface DNService {

	public List<DNBrowseDto> getListDN(String tglDN);
}
