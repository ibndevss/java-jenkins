package myor.matrix.master.repository;

import java.util.List;

import myor.matrix.master.entity.ViewTopDto;

public interface ViewTopRepository {

	List<ViewTopDto> getListTop();

	List<ViewTopDto> getListTopOrderByTgl();

	List<ViewTopDto> getListTopOrderByTipe();

}
