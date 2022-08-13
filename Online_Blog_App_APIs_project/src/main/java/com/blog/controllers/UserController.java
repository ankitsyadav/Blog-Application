package com.blog.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.paylaods.ApiResponse;
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

	@PutMapping("/{uid}")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto udto, @PathVariable Integer uid) {

		UserDto updatedUser = this.uService.updateUser(udto, uid);

		return ResponseEntity.ok(updatedUser);
	}

//	delete - delete user

	@DeleteMapping("/{uid}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer uid) {

		this.uService.deleteUser(uid);
		return new ResponseEntity<ApiResponse>(new ApiResponse("user deleted succesfully", true), HttpStatus.OK);
	}

//	get - get user

	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers() {
		return ResponseEntity.ok(this.uService.getAllUsers());
	}

	@GetMapping("/{uid}")
	public ResponseEntity<UserDto> getUser(@PathVariable Integer uid) {
		UserDto udto = this.uService.getUserById(uid);
		return ResponseEntity.ok(udto);
	}

}
