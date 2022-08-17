package com.blog.paylaods;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class PostDto {

	@NotBlank
	private String ptitle;
	@NotBlank
	private String pcontent;
	private String imageName;
	private LocalDateTime addedDate;

	private CategoryDto category;

	private UserDto user;
}
