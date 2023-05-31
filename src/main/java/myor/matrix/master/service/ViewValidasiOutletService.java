package myor.matrix.master.service;

import java.util.List;

import myor.matrix.master.entity.ViewValidasiOutletDto;

public interface ViewValidasiOutletService {
	 List<ViewValidasiOutletDto> viewValidasiOutlet(String dateSubmitFrom, String dateSubmitTo);
}
