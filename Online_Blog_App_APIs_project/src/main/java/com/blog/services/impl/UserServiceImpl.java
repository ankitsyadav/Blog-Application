package com.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.exceptions.*;
import com.blog.paylaods.UserDto;
import com.blog.entities.User;
import com.blog.repositories.UserRepo;
import com.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	@Autowired
	private ModelMapper modelMapper;

//--------------------------------------------------------------------------------------------------
	public UserDto userToDto(User user) {
		UserDto udto = this.modelMapper.map(user, UserDto.class);

//		UserDto udto = new UserDto();
//		udto.setId(user.getId());
//		udto.setName(user.getName());
//		udto.setEmail(user.getEmail());
//		udto.setAbout(user.getAbout());
//		udto.setPassword(user.getPassword());

		return udto;

	}

	public User dtoToUser(UserDto udto) {
		User user = this.modelMapper.map(udto, User.class);
		return user;
	}

//--------------------------------------------------------------------------------------------------------------
	@Override
	public UserDto createUser(UserDto udto) {
		User user = this.dtoToUser(udto);

		User savedUser = this.userRepo.save(user);
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto udto, Integer uid) {

		User user = this.userRepo.findById(uid).orElseThrow(() -> new ResourceNotFoundException("User", "id", uid));
		user.setName(udto.getName());
		user.setEmail(udto.getEmail());
		user.setAbout(udto.getAbout());
		user.setPassword(udto.getPassword());

		User updatedUser = this.userRepo.save(user);

		return this.userToDto(updatedUser);
	}

	@Override
	public UserDto getUserById(Integer uid) {
		User user = this.userRepo.findById(uid).orElseThrow(() -> new ResourceNotFoundException("User", "id", uid));
		return this.userToDto(user);

	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = this.userRepo.findAll();
		List<UserDto> usersDto = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
		return usersDto;
	}

	@Override
	public void deleteUser(Integer uid) {
		User user = this.userRepo.findById(uid).orElseThrow(() -> new ResourceNotFoundException("User", "id", uid));
		this.userRepo.delete(user);

	}

}
