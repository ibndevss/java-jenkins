package myor.matrix.master.service;

import java.util.List;

import myor.matrix.master.entity.ViewTopDto;

public interface ViewTopService {

	List<ViewTopDto> getListTop();

	List<ViewTopDto> getListTopByTgl();

	List<ViewTopDto> getListTopByTipe();

}
