package com.app.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public interface ImageUploadService {
String uploaImage(String filename,MultipartFile file) throws IOException ;
	
	InputStream getResource(String path, String fileName) throws FileNotFoundException;

}
