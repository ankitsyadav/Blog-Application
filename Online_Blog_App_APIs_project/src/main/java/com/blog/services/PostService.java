package com.blog.services;

import java.util.List;

import com.blog.paylaods.PostDto;
import com.blog.paylaods.PostResponse;

public interface PostService {

//	create
	PostDto createPost(PostDto pdto, Integer uid, Integer cid);

//	update
	PostDto updatePost(PostDto pdto, Integer pid);

//	delete
	void deletePost(Integer pid);

//	get all post

	PostResponse getAllPost(Integer pageNumber, Integer PageSize, String sortBy);

//	get single post 
	PostDto getPostById(Integer pid);

//	 get all post by category
	List<PostDto> getPostByCategory(Integer cid);

//	get all post by user
	List<PostDto> getPostByUser(Integer uid);

//	search post
	List<PostDto> searchPosts(String keyword);

}
