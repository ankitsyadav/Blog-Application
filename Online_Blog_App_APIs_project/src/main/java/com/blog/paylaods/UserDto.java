package com.blog.paylaods;

import javax.persistence.Column;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {

	private int id;

	private String name;
	private String email;
	private String about;
	private String password;

}
