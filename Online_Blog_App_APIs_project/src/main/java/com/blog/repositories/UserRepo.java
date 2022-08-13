package com.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {

	
	
}
