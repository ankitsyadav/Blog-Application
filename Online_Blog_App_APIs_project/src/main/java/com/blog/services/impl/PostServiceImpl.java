package com.blog.services.impl;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.type.LocalDateTimeType;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entities.Category;
import com.blog.entities.Post;
import com.blog.entities.User;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.paylaods.PostDto;
import com.blog.repositories.CategoryRepo;
import com.blog.repositories.PostReop;
import com.blog.repositories.UserRepo;
import com.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostReop pr;
	@Autowired
	private ModelMapper mp;
	@Autowired
	private UserRepo ur;
	@Autowired
	private CategoryRepo cr;

	@Override
	public PostDto createPost(PostDto pdto, Integer uid, Integer cid) {
		User user = this.ur.findById(uid).orElseThrow(() -> new ResourceNotFoundException("user", "user id", uid));
		Category category = this.cr.findById(cid)
				.orElseThrow(() -> new ResourceNotFoundException("category", "category id", cid));

		Post post = this.mp.map(pdto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(LocalDateTime.now());
		post.setUser(user);
		post.setCategory(category);
		Post newPost = this.pr.save(post);

		return this.mp.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto pdto, Integer pid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePost(Integer pid) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<PostDto> getAllPost() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PostDto getPostById(Integer pid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PostDto> getPostByCategory(Integer cid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PostDto> getPostByUser(User uid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

}
