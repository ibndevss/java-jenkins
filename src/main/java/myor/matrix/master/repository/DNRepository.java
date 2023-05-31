package myor.matrix.master.repository;

import java.util.List;

import myor.matrix.master.entity.DNBrowseDto;

public interface DNRepository {

	public List<DNBrowseDto> listDN (String tglDN);
}
