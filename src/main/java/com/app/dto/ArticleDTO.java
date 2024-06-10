package com.app.dto;

import java.time.LocalDate;

import org.springframework.web.multipart.MultipartFile;

import com.app.entity.Author;

import lombok.Data;

@Data
public class ArticleDTO {
    private Long id;

	    private String title;

	    private String description;

	    private LocalDate publishDate;

	    private String status;
	
	    private MultipartFile banner;
	 
	    private Author author;
	  
}
