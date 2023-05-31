package myor.matrix.master.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myor.matrix.master.entity.SearchBrowseDto;
import myor.matrix.master.repository.LokasiRepository;
import myor.matrix.master.service.LokasiService;
@Service
public class LokasiServiceImpl implements LokasiService {

    @Autowired
	private LokasiRepository lokasiRepository;
    
    @Override
    public List<SearchBrowseDto> getBrowseLokasi() {
        // TODO Auto-generated method stub
        return lokasiRepository.getBrowseLokasi();
    }

}
