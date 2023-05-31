package myor.matrix.master.service;

import java.util.List;

import org.springframework.core.io.Resource;

import myor.matrix.master.entity.EdiDto;

public interface ExtractMstOutletPengcoveranService {

	Resource ekstrakPengcoveran(String username, String optSls, String optOutletStatus, List<String> listSalesman);

	Resource ekstrakOutlet(String username, String optSls, String optOutletStatus, List<String> listSalesman);

}
