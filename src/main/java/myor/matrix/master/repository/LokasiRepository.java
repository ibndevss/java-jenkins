package myor.matrix.master.repository;

import java.util.List;

import myor.matrix.master.entity.SearchBrowseDto;

public interface LokasiRepository {

    public List<SearchBrowseDto> getBrowseLokasi();
}