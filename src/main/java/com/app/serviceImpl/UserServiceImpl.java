package com.app.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.Users;
import com.app.repository.Userrepo;
import com.app.service.UserService;

import jakarta.transaction.Transactional;
@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private Userrepo repo;
	@Override
	public List<Users> findall() {
		// TODO Auto-generated method stub
		
		return this.repo.findAll();
	}

}
