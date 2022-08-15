package com.blog.paylaods;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {

	private int id;

	@NotEmpty
	@Size(min = 4, message = "user name must be min of 4 char")
	private String name;

	@Email(message = "email is not valid")
	private String email;

	@NotEmpty
	private String about;

	@NotEmpty
	@Size(min = 3, max = 10, message = "password must be min of 3 and max of 10 chars")
	private String password;

}
