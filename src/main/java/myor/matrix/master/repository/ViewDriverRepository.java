package myor.matrix.master.repository;

import java.util.List;

import myor.matrix.master.entity.SelectItem;
import myor.matrix.master.entity.ViewDriverDto;

public interface ViewDriverRepository {

	List<ViewDriverDto> getListDriver();

}
