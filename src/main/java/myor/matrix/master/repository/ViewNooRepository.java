package myor.matrix.master.repository;

import java.util.List;

import myor.matrix.master.entity.DocumentBrowseDto;
import myor.matrix.master.entity.ViewNooDto;

public interface ViewNooRepository {
	public String getMaxDocNo(String status);
	public List<DocumentBrowseDto> getListDocument(String status);
	public ViewNooDto getDataViewNoo(String docNo);

}
