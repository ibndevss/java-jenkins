package myor.matrix.master.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myor.matrix.master.entity.GetChannelChoosen;
import myor.matrix.master.entity.GetOutletChoosen;
import myor.matrix.master.entity.GetPassword;
import myor.matrix.master.entity.GetProduct;
import myor.matrix.master.entity.HargaDto;
import myor.matrix.master.entity.MasterOutletDataAttributeDto;
import myor.matrix.master.entity.ProductBrowseInPengajuanSODto;
import myor.matrix.master.entity.ProductBrowseDto;
import myor.matrix.master.entity.ProductBrowserHargaSpesifikDto;
import myor.matrix.master.entity.ProductChoosenDto;
import myor.matrix.master.entity.ProductDetailVanDto;
import myor.matrix.master.entity.ProductDto;
import myor.matrix.master.entity.ProductVanBrowseDto;
import myor.matrix.master.entity.SelectItem;
import myor.matrix.master.repository.CustomerRepository;
import myor.matrix.master.repository.ProductRepository;
import myor.matrix.master.repository.SalesmanRepository;
import myor.matrix.master.repository.UtilRepository;
import myor.matrix.master.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private SalesmanRepository salesmanRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private UtilRepository utilRepository;

	@Override
	public List<SelectItem<String>> getListSelectItemProduct() {
		// TODO Auto-generated method stub
		return productRepository.getListProductSelectItem();
	}

	@Override
	@Transactional
	public ProductDto getProductDto(String pcode) {
		// TODO Auto-generated method stub
		
		ProductChoosenDto dataProduct = productRepository.getProductChoosen(pcode);
		if (dataProduct.getBlhjual().equalsIgnoreCase("All Channel")) {
			productRepository.deleteData("FTABEL13", "where data1 = '"+pcode+"' ");
		}
		List<GetChannelChoosen> dataChannel = productRepository.getChannelChoosen(pcode);
		List<GetOutletChoosen> dataOutlet = productRepository.getOutletChoosen(pcode);
		
		
		ProductDto dataEntry = new ProductDto(dataProduct, dataChannel, dataOutlet);
		
		return dataEntry;
	}

	@Override
	public List<GetProduct> getAllProduct() {
		// TODO Auto-generated method stub
		return productRepository.getAllProduct();
	}

	@Transactional
	@Override
	public List<GetProduct> updateJatah(List<GetProduct> dataProducts) {
		// TODO Auto-generated method stub
		for(GetProduct pdct : dataProducts) {
			productRepository.updateJatah(pdct.getPcode(), pdct.getCheck());
		}
		return productRepository.getAllProduct();
	}

	@Override
	public GetPassword getPassword(String id) {
		// TODO Auto-generated method stub
		return productRepository.getPassword(id);
	}

	@Override
	public ProductDetailVanDto getDetail(String slsno, String pcode) {
		// TODO Auto-generated method stub
		return productRepository.getDetail(slsno, pcode);
	}

	@Override
	public List<ProductVanBrowseDto> getBrowseVanPcode(String slsno) {
		// TODO Auto-generated method stub
		return productRepository.getBrowsePcodeVan(slsno);
	}

	@Override
	public List<ProductBrowseDto> getBrowsePcodeRO(String slsNo, String tipeOpr, String team) {
		// TODO Auto-generated method stub
		return productRepository.getBrowsePcodeRO(slsNo, tipeOpr, team);
	}

	@Override
	public List<ProductBrowseDto> getBrowsePcodeStockOutlet(String slsNo) {
		// TODO Auto-generated method stub
		return productRepository.getBrowsePcodeStockOutlet(slsNo);
	}

	@Override
	public List<ProductBrowseDto> getBrowseAllPcode() {
		// TODO Auto-generated method stub
		return productRepository.getBrowseAllPcode();
	}

	@Override
	public List<ProductBrowserHargaSpesifikDto> getViewBrowserHargaSpefisik() {
		// TODO Auto-generated method stub
		return productRepository.getViewBrowserHargaSpefisik();

	}
	
	@Override
	public List<ProductBrowseDto> getBrowsePcodeByGudang(String kdGudang) {
		// TODO Auto-generated method stub
		
		List<ProductBrowseDto> listPcode = null;
		
		if (kdGudang.equalsIgnoreCase("00") || kdGudang.equalsIgnoreCase("R1") || kdGudang.equalsIgnoreCase("R2")) {
			listPcode = productRepository.getBrowsePcodeGudangUtama();
		} else {
			listPcode = productRepository.getBrowsePcodeGudangTambahan(kdGudang);
		}
		return listPcode;
	}

	@Override
	public List<ProductBrowseDto> getBrowsePcodeOpname(String kdGudang) {
		// TODO Auto-generated method stub
		return productRepository.getBrowsePcodeOpname(kdGudang);
	}

	@Override
	public List<SelectItem<String>> getListPcodeOpnameSelectItem(String kdGudang) {
		// TODO Auto-generated method stub
		return productRepository.getListPcodeOpnameSelectItem(kdGudang);
	}

	@Override
	public Double getPricing(String kode, String pcode, String custNo, String cekDateUpload, String slsNo) {
		// TODO Auto-generated method stub
		Double result = 0.0;
		
		String XHrgBsr = "";
		String XHrgTgh = "";
		String XHrgKcl = "";
		int inc = 0;
		
		//Get Data Salesforce
		String salesForce = salesmanRepository.getSalesforce(slsNo);
		
		//Get Data Outlet
		MasterOutletDataAttributeDto outlet = customerRepository.getCustomerAttribute(custNo);
		String XGroupHrg = outlet.getGharga();
		String XChanel = outlet.getOuttype();
		String XGroupOut = outlet.getKdgroupout();
		
		HargaDto harga = productRepository.getHargaProductHirarki(pcode, cekDateUpload, custNo, XGroupOut, salesForce,
				XChanel, XGroupHrg);
		
		if(harga.getHargaBesar() != null) {
			XHrgBsr = ""+harga.getHargaBesar();
			XHrgTgh = ""+harga.getHargaTengah();
			XHrgKcl = ""+harga.getHargaKecil();
		}else {
			HargaDto sellPrice = productRepository.getSellPriceProduct(pcode);
			
			XHrgBsr = ""+sellPrice.getHargaBesar();
			XHrgTgh = ""+sellPrice.getHargaTengah();
			XHrgKcl = ""+sellPrice.getHargaKecil();
		}
		
		String flagNik = utilRepository.getMemostringFtable13("FLAGNIK");
		if(flagNik.equalsIgnoreCase("1")) {
			
			boolean cekNpwp = utilRepository.cekNpwpOutlet(custNo);
			if(cekNpwp == false) {
				
				boolean cekKtp = utilRepository.cekKtpOutlet(custNo);
				if(cekKtp) {
					inc = getPricing2(pcode, custNo, cekDateUpload, slsNo);
				}else {
					inc = 0;
				}
			}else {
				inc = 0;
			}
		}else {
			inc = 0;
		}
		
		int pengali = 100 + inc;
		
		String decPointRes = "0";
		
		String decimalPoint = utilRepository.getMemonamaFtable13("XDESIMALPOINT_D");
		if(decimalPoint.equalsIgnoreCase("")) {
			decPointRes = "0";
		}else {
			decPointRes = decimalPoint;
		}
		
		//Set Harga Besar
		if(kode.equals("1")) {
			Double XHrgBsrFin = (Double.parseDouble(XHrgBsr) * pengali / 100);
			result = utilRepository.roundSql(XHrgBsrFin, decPointRes);
		}
		
		//Set Harga Tengah
		if(kode.equals("2")) {
			Double XHrgTghFin = (Double.parseDouble(XHrgTgh) * pengali / 100);
			result = utilRepository.roundSql(XHrgTghFin, decPointRes);
		}
		
		//Set Harga Kecil
		if(kode.equals("3")) {
			Double XHrgKclFin = (Double.parseDouble(XHrgKcl) * pengali / 100);
			result =  utilRepository.roundSql(XHrgKclFin, decPointRes);
		}
		
		//Set Harga Besar Original
		if(kode.equalsIgnoreCase("1_org")) {
			result = Double.parseDouble(XHrgBsr);
		}
		
		//Set Harga Tengah Original
		if(kode.equalsIgnoreCase("2_org")) {
			result = Double.parseDouble(XHrgTgh);
		}
		
		//Set Harga Kecil Original
		if(kode.equalsIgnoreCase("3_org")) {
			result = Double.parseDouble(XHrgKcl);
		}
		
		return result;
	}

	@Override
	public Integer getPricing2(String pcode, String custNo, String cekDateUpload, String slsNo) {
		// TODO Auto-generated method stub
		Integer xInc = 0;
		
		//Get Data Salesforce
		String salesForce = salesmanRepository.getSalesforce(slsNo);
		
		//Get Data Outlet
		MasterOutletDataAttributeDto outlet = customerRepository.getCustomerAttribute(custNo);
		String XGroupHrg = outlet.getGharga();
		String XChanel = outlet.getOuttype();
		String XGroupOut = outlet.getKdgroupout();
		
		//Get Divisi
		String xDivisi = productRepository.getDivisiProduct(pcode);
		
		String getHargaPerc = productRepository.getHargaProductHirarkiPerc(pcode, xDivisi, cekDateUpload, custNo,
				XGroupOut, salesForce, XChanel, XGroupHrg);
		
		if(getHargaPerc.equals("") == false) {
			xInc = Integer.parseInt(getHargaPerc);
		}else {
			xInc = productRepository.getHargaData6Spesifik2(pcode, xDivisi);
		}
		
		return xInc;

	}
	
	public List<ProductBrowseDto> getBrowsePcodeDN(String nomorDN) {
		// TODO Auto-generated method stub
		return productRepository.getBrowsePcodeDN(nomorDN);
	}

	@Override
	public List<ProductBrowseDto> getBrowseInPengajuanRetur(String slsNo, String slsType, String slsTeam) {
		// TODO Auto-generated method stub
		return productRepository.getBrowseInPengajuanRetur(slsNo, slsType, slsTeam);
	}

	@Override
	public List<ProductBrowseInPengajuanSODto> getBrowseInPengajuanSO(String slsNo, String slsType) {
		// TODO Auto-generated method stub
		return productRepository.getBrowseInPengajuanSO(slsNo, slsType);
	}

}
