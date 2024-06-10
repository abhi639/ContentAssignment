package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.Users;
@Repository
public interface Userrepo extends JpaRepository<Users,Integer>{

}
