package myor.matrix.master.service.impl;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import myor.matrix.master.service.ImageStorageService;
import myor.matrix.master.service.ViewPhotoAndKoordOutletService;

@Service
public class ImageStorageServiceImpl implements ImageStorageService{
	@Autowired
	ViewPhotoAndKoordOutletService viewPhotoAndKoordOutletService;

	@Override
	public Resource load(String custno) {
	    try { 
		      String x =  viewPhotoAndKoordOutletService.getViewPhotoAndKoordOutlet(custno).get(0).getPath();
//		      String x = "uploads/"+idSubdist+"/"+slsId+"/"+date+"/"+outlet;
		      String filename =  viewPhotoAndKoordOutletService.getViewPhotoAndKoordOutlet(custno).get(0).getPictureName();
		      
		      Path root1= Paths.get(x);
		      Path file = root1.resolve(filename);
		      Resource resource = new UrlResource(file.toUri());

		      if (resource.exists() || resource.isReadable()) {
		        return resource;
		      } else {
		        throw new RuntimeException("Could not read the file!");
		      }
		    } catch (MalformedURLException e) {
		      throw new RuntimeException("Error: " + e.getMessage());
		    }
		  }

	@Override
	public Resource loadImage(String path, String filename) {
	    try { 
		      Path root1= Paths.get(path);
		      Path file = root1.resolve(filename);
		      Resource resource = new UrlResource(file.toUri());

		      if (resource.exists() || resource.isReadable()) {
		        return resource;
		      } else {
		        throw new RuntimeException("Could not read the file!");
		      }
		    } catch (MalformedURLException e) {
		      throw new RuntimeException("Error: " + e.getMessage());
		    }
		  }

}
