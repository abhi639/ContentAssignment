package com.app.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.Article;
import com.app.entity.Users;
import com.app.repository.Userrepo;
import com.app.service.ArticleService;
import com.app.service.ImageUploadService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api/")
public class Controller {
	@Autowired
	 ArticleService servie;
@Autowired
	Userrepo repo;
	@DeleteMapping("/deletearticle")
        public ResponseEntity<String> deletedata(@RequestParam ("id")long id) {
    String result=	this.servie.deleteArticle(id);
    	System.out.println("id ===================="+id);
   return ResponseEntity.ok(result);
    
	
	
	}
	@GetMapping ("/l")
    public ResponseEntity<List<Users>>  showarticles(Model model) {
    	List<Users>users=this.repo.findAll();
    	System.out.println("articles"+users);
       
        return ResponseEntity.ok(users);
   
    }
}
