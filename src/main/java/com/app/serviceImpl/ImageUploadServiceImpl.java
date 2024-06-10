package com.app.serviceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.service.ImageUploadService;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class ImageUploadServiceImpl implements ImageUploadService{

	@Override
	public String uploaImage(String path, MultipartFile file) throws IOException {
		//get the file name
		String name=file.getOriginalFilename();
        log.debug("Original file name: {}", name);

		//randome name genrATE
		
		String randomID=UUID.randomUUID().toString();
		String filename1=randomID.concat(name.substring(name.lastIndexOf(".")));
		//fullPath
        log.debug("Generated random file name: {}", filename1);

		//System.out.println("File name"+filename1);
		String filepath=path+File.separatorChar+filename1;
        log.debug("File path: {}", filepath);

		//create a folder if notcreated
		File f=new File(path);
		System.out.println(f);
		
		if(!f.exists()) {
	
			Boolean iscreated=f.mkdir();
            log.debug("Directory created: {}", iscreated);

		}
		

			Files.copy(file.getInputStream(), Paths.get(filepath));
		
		
		
		return filename1;
	}
	@Override
	 public InputStream getResource(String path, String fileName) throws FileNotFoundException{
	     String fullPath = path + fileName;
	        log.debug("Resource path: {}", fullPath);

	     InputStream is = new FileInputStream(fullPath);	
	        log.debug("InputStream for resource created: {}", is);

	     // db logic to return inpustream
	     return is;
	                                
	 }



}
