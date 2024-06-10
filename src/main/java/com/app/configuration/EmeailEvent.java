package com.app.configuration;

import org.springframework.context.ApplicationEvent;

import com.app.entity.Article;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class EmeailEvent extends ApplicationEvent{
	  private Article article;
	    private String subject;
	    public EmeailEvent(Article article,String subject ) {
	        super(article);
	        this.article = article;
	        this.subject=subject;
	       
	    }
}
