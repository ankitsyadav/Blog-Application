package com.blog.paylaods;

import javax.validation.constraints.NotBlank;

import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CategoryDto {

	private Integer cId;
	@NotBlank
	@Size(min = 4, message = "min size of TITLE is  4 words")
	private String cTitle;
	@NotBlank
	@Size(min = 10, message = "min size of DESC is 10 words")
	private String cDesc;

}
