package myor.matrix.master.repository;

import java.util.List;

import myor.matrix.master.entity.ViewValidasiOutletDto;

public interface ViewValidasiOutletRepository {
	List<ViewValidasiOutletDto> viewValidasiOutlet(String dateSubmitFrom, String dateSubmitTo);

}
