package myor.matrix.master.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myor.matrix.master.entity.SalesmanBrowseDto;
import myor.matrix.master.entity.SalesmanChoosenDto;
import myor.matrix.master.entity.SelectItem;
import myor.matrix.master.repository.SalesmanRepository;
import myor.matrix.master.service.SalesmanService;

@Service
public class SalesmanServiceImpl implements SalesmanService {
	
	@Autowired
	private SalesmanRepository salesmanRepository; 
	
	@Override
	public List<SelectItem<String>> getListSelectItemSalesman(String oprType) {
		// TODO Auto-generated method stub
		return salesmanRepository.getListSalesmanSelectItem(oprType);
	}

	@Override
	public SalesmanChoosenDto getSalesmanChoosen(String slsId) {
		// TODO Auto-generated method stub
		return salesmanRepository.getSalesmanChoosen(slsId);
	}

	@Override
	public List<SelectItem<String>> getDataSalesCanvas() {
		// TODO Auto-generated method stub
		return salesmanRepository.getDataSalesCanvas();
	}

	@Override
	public List<SelectItem<String>> getListSalesforce() {
		// TODO Auto-generated method stub
		List<SelectItem<String>> result = new ArrayList();
		SelectItem<String> choose = new SelectItem<String>("Choose", null);
		result.add(choose);
		result.addAll(salesmanRepository.getListSalesforce());
		return result;
	}
	
	@Override
	public List<SalesmanBrowseDto> getBrowseSalesman(String oprType) {
		// TODO Auto-generated method stub
		return salesmanRepository.getBrowseSalesman(oprType);
	}

	@Override
	public List<SalesmanChoosenDto> getListSalesmanChoosen() {
		// TODO Auto-generated method stub
		return salesmanRepository.getListSalesmanChoosenBy("ALL");
	}

	@Override
	public List<SalesmanChoosenDto> getListSalesmanChoosenBy(String salesBy) {
		// TODO Auto-generated method stub
		return salesmanRepository.getListSalesmanChoosenBy(salesBy);
	}

	@Override
	public List<SalesmanChoosenDto> getListSalesmanViewKpl() {
		// TODO Auto-generated method stub
		return salesmanRepository.getListSalesmanViewKpl();
	}

	@Override
	public List<SalesmanBrowseDto> getBrowseInPengajuanRetur(String WHTRANSISI) {
		// TODO Auto-generated method stub
		return salesmanRepository.getBrowseInPengajuanRetur(WHTRANSISI);
	}

	public List<SalesmanBrowseDto> getSalesmanCover(String custno) {
		// TODO Auto-generated method stub
		return salesmanRepository.getSalesmanCover(custno);
	}

	@Override
	public List<SelectItem<String>> getListSalesmanSalesByTeam(String team) {
		// TODO Auto-generated method stub
		return salesmanRepository.getListSalesmanSalesByTeam(team);
	}
	
	@Override
	public List<SalesmanBrowseDto> getSalesmanLaporanPiutangDagang() {
		// TODO Auto-generated method stub
		return salesmanRepository.getSalesmanLaporanPiutangDagang();
	}

	@Override
	public List<SelectItem<String>> getListSalesman() {
		// TODO Auto-generated method stub
		return salesmanRepository.getListSalesman();
	}

	@Override
	public List<SalesmanBrowseDto> getSalesmanValidasiKTP() {
		// TODO Auto-generated method stub
		return salesmanRepository.getSalesmanValidasiKTP();
	}

}
