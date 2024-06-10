package com.app.entity;

import java.time.LocalDate;

import com.app.configuration.ArticleChangeListener;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

    @Data
	@Entity
	@Table(name = "articles")
	public class Article {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(nullable = false)
	    private String title;

	    @Column(columnDefinition = "TEXT")
	    private String description;

	    @Column(name = "publish_date")
	    @JsonFormat(pattern = "yyyy-MM-dd")
	    private LocalDate publishDate;

	    @Column(nullable = false)
	    private String status;
	
	    private String banner;
	 
	    @ManyToOne
	    @JoinColumn(name = "author_id", nullable = false)
	    private Author author;
	
	  
}
