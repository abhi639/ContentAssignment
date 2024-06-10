package com.app.configuration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.app.entity.Article;
import com.app.entity.Users;
import com.app.exception.ResouceNotFoundException;
import com.app.service.EmailService;
import com.app.service.UserService;

import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import lombok.RequiredArgsConstructor;

@Component
public class ArticleChangeListener  implements ApplicationListener<EmeailEvent>{
	   
	 @Autowired    
    private  EmailService emailService;
     
    @Autowired
    private  UserService userservice;
    
    @Override
	public void onApplicationEvent(EmeailEvent event) {
		if(event.getSubject().equals("Article Updated")) {
			try {
				sendEmailOnArticleChange(event.getArticle());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			
			sendEmailOnArticleInsert(event.getArticle());
		}
		
	}


    public void sendEmailOnArticleChange(Article article) throws InterruptedException {
        String subject = "Article Updated";
        String body = "An article with title '" + article.getTitle() + "' has been updated or added.";

        List<Users> users =this.userservice.findall();
       if(users.isEmpty()) {
    	   throw new ResouceNotFoundException("Users", "not found", 0);
       }
        
        for (Users user : users) {
            emailService.sendEmail(user, subject, body);
        }
    }

    public void sendEmailOnArticleInsert(Article article) {
        String subject = "New Article adedd";
        String body = "An article with title '" + article.getTitle() + "' has been added.";
        // Here, you can get all users from the database and send emails to each user
        List<Users> users = this.userservice.findall(); // Assuming you have a UserRepository
        for (Users user : users) {
            emailService.sendEmail(user, subject, body);
        }
    }

	
}

