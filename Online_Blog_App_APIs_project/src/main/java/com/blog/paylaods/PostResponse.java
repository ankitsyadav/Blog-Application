package com.blog.paylaods;

import java.util.List;

import lombok.Data;

@Data
public class PostResponse {

	private List<PostDto> content;
	private int pageNumber;
	private int pageSize;
	private long totalElements;
	private int totalPages;
	private boolean lastPage;

}
