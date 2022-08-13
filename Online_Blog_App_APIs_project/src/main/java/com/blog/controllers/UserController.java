package com.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.paylaods.UserDto;
import com.blog.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	private UserService uService;

//	post -create user
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto udto) {
		UserDto createdUserDto = this.uService.createUser(udto);
		return new ResponseEntity<>(createdUserDto, HttpStatus.CREATED);

	}

//	put - update user

//	delete - delete user

//	get - get user

}
