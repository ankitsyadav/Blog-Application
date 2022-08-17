package com.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.entities.Post;
import com.blog.paylaods.PostDto;
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

}
