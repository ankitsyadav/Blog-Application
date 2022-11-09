package com.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.config.AppConstants;
import com.blog.paylaods.ApiResponse;
import com.blog.paylaods.PostDto;
import com.blog.paylaods.PostResponse;
import com.blog.services.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {

	@Autowired
	private PostService ps;

//	create
	@PostMapping("/user/{uid}/category/{cid}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto pdto, @PathVariable Integer uid,
			@PathVariable Integer cid) {

		PostDto createdPostDto = this.ps.createPost(pdto, uid, cid);
		return new ResponseEntity<PostDto>(createdPostDto, HttpStatus.CREATED);
	}

//	get by user Posts
	@GetMapping("/user/{uid}/posts")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer uid) {

		List<PostDto> pdtoList = this.ps.getPostByUser(uid);

		return new ResponseEntity<List<PostDto>>(pdtoList, HttpStatus.OK);
	}

//	get by user category
	@GetMapping("/category/{cid}/posts")
	public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer cid) {

		List<PostDto> pdtoList = this.ps.getPostByCategory(cid);

		return new ResponseEntity<List<PostDto>>(pdtoList, HttpStatus.OK);
	}

//	 get all post 
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy) {

		PostResponse pr = this.ps.getAllPost(pageNumber, pageSize, sortBy);

		return new ResponseEntity<PostResponse>(pr, HttpStatus.OK);
	}
//	get post by ID

	@GetMapping("/posts/{pid}")
	public ResponseEntity<PostDto> getAllPosts(@PathVariable Integer pid) {

		PostDto pdtoList = this.ps.getPostById(pid);

		return new ResponseEntity<PostDto>(pdtoList, HttpStatus.OK);
	}

//	 delete post
	@DeleteMapping("/posts/{pid}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer pid) {

		this.ps.deletePost(pid);

		return new ResponseEntity<ApiResponse>(new ApiResponse("post deleted success", true), HttpStatus.OK);

	}

//	 update post
	@PutMapping("/posts/{pid}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto pdto, @PathVariable Integer pid) {

		PostDto pdtoUpdated = this.ps.updatePost(pdto, pid);

		return new ResponseEntity<PostDto>(pdtoUpdated, HttpStatus.OK);

	}
//	serach by title

	@GetMapping("/posts/search/{keywords}")
	public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable String keywords) {
		List<PostDto> pdto = this.ps.searchPosts(keywords);

		return new ResponseEntity<List<PostDto>>(pdto, HttpStatus.OK);

	}

}
