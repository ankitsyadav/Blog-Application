package com.blog.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blog.entities.Category;
import com.blog.entities.Post;
import com.blog.entities.User;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.paylaods.PostDto;
import com.blog.paylaods.PostResponse;
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
		Post p = this.pr.findById(pid).orElseThrow(() -> new ResourceNotFoundException("post", "post id ", pid));
		p.setPtitle(pdto.getPtitle());
		p.setPcontent(pdto.getPcontent());
		p.setImageName(pdto.getImageName());

		Post updatedPost = this.pr.save(p);
		return this.mp.map(updatedPost, PostDto.class);
	}

	@Override
	public void deletePost(Integer pid) {
		Post p = this.pr.findById(pid).orElseThrow(() -> new ResourceNotFoundException("post", "post id ", pid));
		this.pr.delete(p);

	}

	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy) {

		Pageable p = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));

		Page<Post> pagePost = this.pr.findAll(p);
		List<Post> posts = pagePost.getContent();
		List<PostDto> pdtoPosts = posts.stream().map((p1) -> this.mp.map(p1, PostDto.class))
				.collect(Collectors.toList());
		PostResponse pr = new PostResponse();
		pr.setContent(pdtoPosts);
		pr.setPageNumber(pagePost.getNumber());
		pr.setPageSize(pagePost.getSize());
		pr.setTotalElements(pagePost.getTotalElements());
		pr.setTotalPages(pagePost.getTotalPages());
		pr.setLastPage(pagePost.isLast());

		return pr;
	}

	@Override
	public PostDto getPostById(Integer pid) {

		Post p = this.pr.findById(pid).orElseThrow(() -> new ResourceNotFoundException("post", "post id ", pid));

		PostDto pdto = this.mp.map(p, PostDto.class);

		return pdto;
	}

	@Override
	public List<PostDto> getPostByCategory(Integer cid) {
		Category cat = this.cr.findById(cid)
				.orElseThrow(() -> new ResourceNotFoundException("category", "category id ", cid));
		List<Post> pList = this.pr.findByCategory(cat);
		List<PostDto> pdtoList = pList.stream().map((post) -> this.mp.map(post, PostDto.class))
				.collect(Collectors.toList());

		return pdtoList;
	}

	@Override
	public List<PostDto> getPostByUser(Integer uid) {

		User user = this.ur.findById(uid).orElseThrow(() -> new ResourceNotFoundException("user", "user id ", uid));
		List<Post> pList = this.pr.findByUser(user);

//	
		List<PostDto> pdtoList = pList.stream().map((p) -> this.mp.map(p, PostDto.class)).collect(Collectors.toList());

		return pdtoList;
	}

//	serching
	@Override
	public List<PostDto> searchPosts(String keyword) {
		List<Post> posts = this.pr.findByPtitleContaining(keyword);
		List<PostDto> pdto = posts.stream().map((p) -> this.mp.map(p, PostDto.class)).collect(Collectors.toList());
		return pdto;
	}

}
