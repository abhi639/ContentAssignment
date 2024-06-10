package com.app.serviceImpl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.app.configuration.EmeailEvent;
import com.app.dto.ArticleDTO;
import com.app.entity.Article;
import com.app.entity.Author;
import com.app.exception.ResouceNotFoundException;
import com.app.repository.ArticleRepo;
import com.app.repository.AuthorRepo;
import com.app.service.ArticleService;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService{
	  @Autowired
	    private ArticleRepo articleRepository;
		@Autowired
		private ModelMapper mpodelmapper;
	  @Autowired
private AuthorRepo authorrepo;
	    @Autowired
	    private  ApplicationEventPublisher publisher;
	@Override
	public List<Article> getAllArticles() {
		// TODO Auto-generated method stub
		
		
		return this.articleRepository.findAll();
	}

	@Override
	public Article getArticleById(Long id) {
		// TODO Auto-generated method stub
		Article article=null;
		try {
			log.info("Article id printing {}",id);
		 article=this.articleRepository.findById(id).orElseThrow(()->new ResouceNotFoundException("Not found","Article", id));
		 log.info("Article data{}",article);
		} catch (Exception e) {
			log.error("Article data not found by id{}",id);
		}
	
		return article;
	}

	@Override
	public Article saveArticle(ArticleDTO articledto ,long id,String filename) {
		// TODO Auto-generated method stub
		log.info("Saving Data");
		
		Author author=this.authorrepo.findById(id).orElseThrow(()->new ResouceNotFoundException("Not found","Author", id));
		
		Article article=this.mpodelmapper.map(articledto, Article.class);
	
		article.setStatus("Draft");
		article.setBanner(filename);
	article.setAuthor(author);
	log.info("Article Saved succefully {}");
	publisher.publishEvent(new EmeailEvent(article,"Article Saved"));
	
		return this.articleRepository.save(article);
	}
     
	@Override
	public String deleteArticle(Long id) {
		try {
			
		
		Article article=this.articleRepository.findById(id).orElseThrow(()->new ResouceNotFoundException("Not found","Article", id));
		System.out.println(article);
		this.articleRepository.delete(article);
		} catch (Exception e) {
			e.getStackTrace();
			log.error("Aricle not deleted");
		}
		return "Article deleted Succesfully";
	}

	@Override
	public Article updatearticle(ArticleDTO articledto,long id,long authorId,String filename) {
		// TODO Auto-generated method stub
		
		log.info("Article update starting");
		Article article=this.articleRepository.findById(id).orElseThrow(()->new ResouceNotFoundException("Not found","Article", id));
        System.out.println("updated article"+article);
		Article maparticle=this.mpodelmapper.map(articledto, Article.class);

        article.setPublishDate(maparticle.getPublishDate());
        article.setDescription(maparticle.getDescription());
       article.setTitle(maparticle.getTitle());
        article.setStatus("Published");
		article.setBanner(filename);
		Author author=this.authorrepo.findById(authorId).orElseThrow(()->new ResouceNotFoundException("Not found","Author", id));

		article.setAuthor(author);
		log.info("Article update Susccesfully");
		publisher.publishEvent(new EmeailEvent(article,"Article Updated"));
		return this.articleRepository.save(article);
	}

	
	 
}
