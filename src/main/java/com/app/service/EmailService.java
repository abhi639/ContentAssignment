package com.app.service;

import com.app.entity.Article;
import com.app.entity.Users;

public interface EmailService {
	  public void sendEmail(Users user, String subject, String body);
}
