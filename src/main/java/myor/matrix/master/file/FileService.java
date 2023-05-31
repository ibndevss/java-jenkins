package myor.matrix.master.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import myor.matrix.master.properties.RootDirectoryProperties;

@Service
public class FileService {
	private final Path rootLocationExcel;
	
	private final Path rootLocationPdf;
	
	public FileService(RootDirectoryProperties properties) {
		// TODO Auto-generated constructor stub
		this.rootLocationExcel = Paths.get(properties.getFilePathExcel());
		this.rootLocationPdf = Paths.get(properties.getFilePathPdf());
	}	
	
	public void init() {
		// TODO Auto-generated method stub
		try {			
			Files.createDirectories(rootLocationExcel);
		} catch (Exception e) {
			e.getMessage();
		}
	}	
	
	public void deleteByFileName(MultipartFile file) {
		// TODO Auto-generated method stub
		try {
			String fileName = StringUtils.cleanPath(file.getOriginalFilename());
			Files.delete(this.rootLocationExcel.resolve(fileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	public void deletePdfByFileName(MultipartFile file) {
		// TODO Auto-generated method stub
		try {
			String fileName = StringUtils.cleanPath(file.getOriginalFilename());
			Files.delete(this.rootLocationPdf.resolve(fileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
