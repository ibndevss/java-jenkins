package myor.matrix.master.file;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import myor.matrix.master.exception.StorageException;
import myor.matrix.master.properties.RootDirectoryProperties;
import myor.matrix.master.service.ProductFocusService;

@Component
public class ProductFocusFile {
	
	private final Path rootLocationExcel;
	
	@Autowired ProductFocusService service;
	
	@Autowired FileService fileService;
	
	@Autowired
	public ProductFocusFile(RootDirectoryProperties properties) {
		// TODO Auto-generated constructor stub		
		this.rootLocationExcel = Paths.get(properties.getFilePathExcel());
	}	
	
	public void uploadFocusSFA(MultipartFile file, String userId) {
		// TODO Auto-generated method stub		
		try {
			if (file.isEmpty()) {
				throw new StorageException("Failed to store empty file " + file.getOriginalFilename());
			}
			if (file.getOriginalFilename().contains("..")) {
				throw new StorageException(
						"Cannot store file with relative path outside current directory " + file.getOriginalFilename());
			}
			try (InputStream inputStream = file.getInputStream()) {
				Files.copy(inputStream, this.rootLocationExcel.resolve(file.getOriginalFilename()),StandardCopyOption.REPLACE_EXISTING);				
				service.readUploadSFA(userId, file.getOriginalFilename());
				fileService.deleteByFileName(file);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}		
	}
}
