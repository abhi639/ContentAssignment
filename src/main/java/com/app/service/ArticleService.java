package com.app.service;

import java.util.List;
import java.util.Optional;

import com.app.dto.ArticleDTO;
import com.app.entity.Article;

public interface ArticleService {
	

	    public List<Article> getAllArticles();

	    public Article getArticleById(Long id);

	    public Article saveArticle(ArticleDTO articledto,long id,String filename); 

	    public String deleteArticle(Long id);
	    public Article updatearticle(ArticleDTO articledto, long id,long authoId,String filename);

}
