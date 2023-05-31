package myor.matrix.master.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import myor.matrix.master.entity.CopyFileDto;
import myor.matrix.master.entity.MessageDto;

@FeignClient(name="FILE-MANAGEMENT-${app.vm}",path="${app.vm-path}/api/file-management/file")
public interface FileApiService {

	@GetMapping(path="/cek-folder",params= {"path"})
	public MessageDto cekPath (@RequestHeader("Authorization") String token,
			@RequestHeader("X-Tenant") String schema,@RequestParam String path);
	
	@PutMapping(path="/copy-file")
	public MessageDto copyFile (@RequestHeader("Authorization") String token,
			@RequestHeader("X-Tenant") String schema,@RequestBody List<CopyFileDto> datas);
	
}
