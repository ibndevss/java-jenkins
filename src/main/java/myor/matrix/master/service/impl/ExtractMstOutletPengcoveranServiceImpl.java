package myor.matrix.master.service.impl;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import myor.matrix.master.entity.EdiDto;
import myor.matrix.master.entity.EdiParamDto;
import myor.matrix.master.exception.ConflictException;
import myor.matrix.master.exception.NotFoundException;
import myor.matrix.master.repository.ExtractMstOutletPengcoveranRepository;
import myor.matrix.master.service.ExtractMstOutletPengcoveranService;

@Service
public class ExtractMstOutletPengcoveranServiceImpl implements ExtractMstOutletPengcoveranService {
	
	@Autowired
	private ExtractMstOutletPengcoveranRepository extractMstOutletPengcoveranRepository;
	
	private String nameFolderEdi = "/EXTRACT";
	
	public Path load(String fileName) {
		String home = System.getProperty("user.home");
		String source = home + nameFolderEdi;
		return (Path) Paths.get(source).resolve(fileName);
	}
	
	@Override
	public Resource ekstrakPengcoveran(String username, String optSls, String optOutletStatus, List<String> listSalesman) {	
		String fullFileName =  "Pengcoveran_by_"+ username +".txt";
		int cekEkstrak =  extractMstOutletPengcoveranRepository.getEkstrakDataPengcoveran(username, fullFileName, optSls, optOutletStatus, listSalesman);
		
		if(cekEkstrak == 1) {
			try {
				Path file = load(fullFileName);
				Resource resource = new UrlResource(file.toUri());
				if (resource.exists()) {
					return resource;
				} else {
					throw new NotFoundException("Could not read file " );
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
				throw new NotFoundException("Could not read file " );
			}
		}else {
			throw new ConflictException("Error Extract File " );
		}
	}
	
	@Override
	public Resource ekstrakOutlet(String username, String optSls, String optOutletStatus, List<String> listSalesman) {
		String fullFileName =  "Outlet_by_"+ username +".txt";
		int cekEkstrak =  extractMstOutletPengcoveranRepository.getEkstrakDataOutlet(username, fullFileName, optSls, optOutletStatus, listSalesman);
		
		if(cekEkstrak == 1) {
			try {
				Path file = load(fullFileName);
				Resource resource = new UrlResource(file.toUri());
				if (resource.exists()) {
					return resource;
				} else {
					throw new NotFoundException("Could not read file " );
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
				throw new NotFoundException("Could not read file " );
			}
		}else {
			throw new ConflictException("Error Extract File " );
		}
	}

}
