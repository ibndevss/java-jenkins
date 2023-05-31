package myor.matrix.master.service;

import java.util.List;

import myor.matrix.master.entity.ProsesKonfirmasiNooDto;

public interface ProsesKonfirmasiNooService {

	public List<String> loadForm();
	
	public List<ProsesKonfirmasiNooDto> getListData(String tglRegister);
}
