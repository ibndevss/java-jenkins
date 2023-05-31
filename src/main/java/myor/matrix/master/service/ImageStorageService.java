package myor.matrix.master.service;

import org.springframework.core.io.Resource;

public interface ImageStorageService {
	 public Resource load(String custno);
	 public Resource loadImage(String path, String filename);
}
