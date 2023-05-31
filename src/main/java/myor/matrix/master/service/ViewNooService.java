package myor.matrix.master.service;

import java.util.List;

import myor.matrix.master.entity.DocumentBrowseDto;
import myor.matrix.master.entity.ViewNooDto;

public interface ViewNooService {
	
	public List<DocumentBrowseDto> getListDocument(String status);
	public ViewNooDto getDataViewNoo(String docNo,String status);

}
