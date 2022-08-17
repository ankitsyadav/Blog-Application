package com.blog.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entities.Category;

import com.blog.exceptions.CategoryNotFoundException;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.paylaods.CategoryDto;

import com.blog.repositories.CategoryRepo;
import com.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo cr;

	@Autowired
	private ModelMapper mmp;

	public Category cDtoToCategory(CategoryDto cdto) {
		Category c = this.mmp.map(cdto, Category.class);
		return c;
	}

	public CategoryDto CategoryToCDto(Category c) {
		CategoryDto cdto = this.mmp.map(c, CategoryDto.class);
		return cdto;
	}

	@Override
	public CategoryDto createCategory(CategoryDto cdto) {
		Category c = this.cDtoToCategory(cdto);
		Category uc = cr.save(c);
		return this.CategoryToCDto(uc);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto cdto, Integer cid) {
		Category c = this.cr.findById(cid).orElseThrow(() -> new ResourceNotFoundException("category", "id", cid));
		c.setCTitle(cdto.getCTitle());
		c.setCDesc(cdto.getCDesc());
		this.cr.save(c);
		return this.CategoryToCDto(c);
	}

	@Override
	public void deleteCategory(Integer cid) {
		Category c = this.cr.findById(cid).orElseThrow(() -> new ResourceNotFoundException("category", "id", cid));
		this.cr.delete(c);

	}

	@Override
	public CategoryDto getCategory(Integer cid) {
		Category c = this.cr.findById(cid).orElseThrow(() -> new ResourceNotFoundException("category", "id", cid));

		return this.CategoryToCDto(c);
	}

	@Override
	public List<CategoryDto> getCategories() {
		List<Category> c = this.cr.findAll();
		List<CategoryDto> cdto = c.stream().map(c1 -> this.CategoryToCDto(c1)).collect(Collectors.toList());
		return cdto;

	}
}
