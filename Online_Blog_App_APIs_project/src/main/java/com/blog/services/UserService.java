package com.blog.services;

import java.util.List;

import com.blog.entities.User;
import com.blog.paylaods.UserDto;

public interface UserService {

	UserDto createUser(UserDto udto);

	UserDto updateUser(UserDto udto, Integer uid);

	UserDto getUserById(Integer uid);

	List<UserDto> getAllUsers();

	void deleteUser(Integer userId);

}
