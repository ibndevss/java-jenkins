package myor.matrix.master.service;

import java.util.List;

import myor.matrix.master.entity.SelectItem;

public interface GudangService {
	public List<SelectItem<String>> getListGudang();
	public List<SelectItem<String>> getListAllGudang();
	public List<SelectItem<String>> getListMutasiGudang(String kdGudang, String flagWhTransisi);
}
