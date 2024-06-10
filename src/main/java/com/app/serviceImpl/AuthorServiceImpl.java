package com.app.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.Author;
import com.app.repository.AuthorRepo;
import com.app.service.AuthorService;
@Service
public class AuthorServiceImpl implements AuthorService{

	@Autowired
	private AuthorRepo authorrepo;
	@Override
	public List<Author> getallAuthor() {
		// TODO Auto-generated method stub
		List<Author>author=this.authorrepo.findAll();
		
		return author;
	}

}
