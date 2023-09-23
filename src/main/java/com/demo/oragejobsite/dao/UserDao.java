package com.demo.oragejobsite.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.oragejobsite.entity.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer>{
	 User findByUserName(String userName);
}

