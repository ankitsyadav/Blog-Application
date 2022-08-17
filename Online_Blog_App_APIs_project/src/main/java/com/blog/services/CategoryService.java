package com.blog.services;

import java.util.List;

import com.blog.paylaods.CategoryDto;

public interface CategoryService {

//	Create
	CategoryDto createCategory(CategoryDto cdto);

//	update
	CategoryDto updateCategory(CategoryDto cdto, Integer cid);

//	delete
	void deleteCategory(Integer cid);

//	get

	CategoryDto getCategory(Integer cid);

//get all
	List<CategoryDto> getCategories();

}
