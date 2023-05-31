package myor.matrix.master.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myor.matrix.master.entity.ProsesKonfirmasiNooDto;
import myor.matrix.master.repository.ProsesKonfirmasiNooRepository;
import myor.matrix.master.service.ProsesKonfirmasiNooService;

@Service
public class ProsesKonfirmasiNooServiceImpl implements ProsesKonfirmasiNooService {

	@Autowired
	private ProsesKonfirmasiNooRepository repoProsesKonfirmasiNoo;
	
	@Override
	public List<String> loadForm() {
		// TODO Auto-generated method stub
		List<String> datas = new ArrayList<>();
		String tglGudang = repoProsesKonfirmasiNoo.getXkeyString("FMEMO", "TO_CHAR(MEMODATE,'DD MON YYYY')", "WHERE MEMONAMA='CADATE'");
		datas.add(tglGudang);
		String sysDate =  repoProsesKonfirmasiNoo.getFromDualString("DUAL","TO_CHAR (SYSDATE, 'DD MON YYYY') AS tanggal", " ");
		datas.add(sysDate);
		String limitNoo = "99999999";
		String cekLimitNo = repoProsesKonfirmasiNoo.getXkeyString("ftable13", "TO_CHAR(memoint)", "where xkey ='XLIMITTXTNOO'");
		if (cekLimitNo.length() != 0) {
			limitNoo = cekLimitNo;
		}
		datas.add(limitNoo);
		
		return datas;
	}

	@Override
	public List<ProsesKonfirmasiNooDto> getListData(String tglRegister) {
		// TODO Auto-generated method stub
		return repoProsesKonfirmasiNoo.getListData(tglRegister);
	}
}
