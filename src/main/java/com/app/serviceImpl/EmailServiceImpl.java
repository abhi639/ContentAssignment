package com.app.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.app.entity.Users;
import com.app.service.EmailService;

import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
@Async
public class EmailServiceImpl  implements EmailService {
	  @Autowired
	    private JavaMailSender javaMailSender;
@Override
	    public void sendEmail(Users user, String subject, String body) {
	try {
		
	log.info("masseg sending {}");
     String senderName = "Content Article";
     String mailContent = "<p> Hi, "+ user.getName()+ ", </p>"+
    		 "<p> Hi, "+ body+ ", </p>"+
             "Please, please check our content.</p>"+
             "<p> Thank you <br> Article Service";
	MimeMessage message = javaMailSender.createMimeMessage();
    var messageHelper = new MimeMessageHelper(message);


	  
    messageHelper.setFrom("abhijit", senderName);
    messageHelper.setTo(user.getMail_Id());
    log.info("mail users {}", user.getMail_Id());

    messageHelper.setSubject(subject);
    messageHelper.setText(mailContent, true);
    javaMailSender.send(message);
    log.info("mail data {}",messageHelper.toString());
	} catch (Exception e) {
		e.getStackTrace();
		log.error("Email not send {}");
	}
	    }
}
