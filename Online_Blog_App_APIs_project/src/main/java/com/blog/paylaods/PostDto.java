package com.blog.paylaods;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;

import com.blog.entities.Comment;

import lombok.Data;

@Data
public class PostDto {

	private Integer pid;
	@NotBlank
	private String ptitle;
	@NotBlank
	private String pcontent;
	private LocalDateTime addedDate;

	private CategoryDto category;
	private Set<Comment> comments=new HashSet<>();

}
