package com.app.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.app.dto.ArticleDTO;
import com.app.entity.Article;
import com.app.entity.Author;
import com.app.service.ArticleService;
import com.app.service.AuthorService;
import com.app.service.ImageUploadService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/")
@Slf4j
public class ArticleController {
    @Autowired
	ArticleService articleService;
    @Value(value = "${project.image}")
	String path;
    @Autowired
    AuthorService authorservice;
    @Autowired
 ImageUploadService imageservice;
    @GetMapping ("/")
    public String view() {
    	
        return "new";
   
    }
     @GetMapping ("/add")
    public String articleform(Model model,Article article) {
         log.info("Displaying article form");

    	List<Author>authors=this.authorservice.getallAuthor();
    	
       model.addAttribute("authors", authors);
        return "createarticle";
   
    }
     @GetMapping ("/list")
     public String showarticles(Model model) {
         log.info("Fetching list of articles");

    	 List<Article>articles=this.articleService.getAllArticles();
         log.debug("Articles fetched: {}", articles);
        model.addAttribute("articles", articles);
         return "view";
    
     }
     @GetMapping ("/update/articleId/{articleId}")
     public String updatearticleform(Model model,@PathVariable("articleId")Long id) {
         log.info("Displaying update form for article ID: {}", id);

    	 List<Author>authors=this.authorservice.getallAuthor();
     	Article article=this.articleService.getArticleById(id);
     	model.addAttribute("article",article);
        model.addAttribute("authors",authors);
         return "updatearticle";
    
     }
     @GetMapping ("/article/view/{id}")
     public String viewarticle(Model model,@PathVariable("id")Long id) {
         log.info("Viewing article with ID: {}", id);

    	 Article article=this.articleService.getArticleById(id);
     	model.addAttribute("articles",article);
         return "viewarticle";
    
     }
     
    @PostMapping(value="/addArticle/authorId/{Id}",consumes = { "multipart/form-data"})
    public String createArticle(@ModelAttribute("article") ArticleDTO article,@RequestParam("banner")MultipartFile file,@PathVariable("Id")long id) throws IOException {
        log.info("Creating article for author ID: {}", id);
       

	
	      if(!file.isEmpty()) {
	    		String fileNmae=this.imageservice.uploaImage(path, file);
                log.info("File uploaded: {}", fileNmae);
    	this.articleService.saveArticle(article, id,fileNmae);}
	      else {
	    	  log.error("Image file is not added{}");
	      	this.articleService.saveArticle(article, id,"NA");}
  
	      
        return "redirect:/list";
   
    }
    @PostMapping("/updateArticle/article/{articleid}/author/{authorId}")
    public String updateArticle(ArticleDTO articledto,@PathVariable("articleid")long id,@RequestParam("banner")MultipartFile file,@PathVariable("authorId")long authorid) throws IOException {
     
      if(!file.isEmpty()) {
    	  String fileNmae=this.imageservice.uploaImage(path, file);
    	this.articleService.updatearticle(articledto, id, authorid,fileNmae);}
    	
      else {
      	this.articleService.updatearticle(articledto, id, authorid,"NA");
      	}

      
        return "redirect:/list";
    }
    @GetMapping(value ="/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(@PathVariable ("imageName") String imageName,HttpServletResponse response) throws IOException {
    		System.out.println("image name=="+imageName);
    		if(!imageName.equals("NA")) {
    InputStream resource = this.imageservice.getResource(path, imageName);
    System.out.println("Resource name"+resource);
    response. setContentType(MediaType. IMAGE_JPEG_VALUE);
    StreamUtils.copy(resource, response. getOutputStream());
    		}
    		
    	}
     
}
