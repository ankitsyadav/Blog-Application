package com.blog.controllers;

import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RestController;

import com.blog.paylaods.ApiResponse;
import com.blog.paylaods.CategoryDto;
import com.blog.services.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	@Autowired
	private CategoryService cr;

//	create
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto cdto) {

		CategoryDto createdCategory = this.cr.createCategory(cdto);

		return new ResponseEntity<CategoryDto>(createdCategory, HttpStatus.CREATED);
	}

//	update

	@PutMapping("/{cid}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto cdto, @PathVariable Integer cid) {

		CategoryDto updatedCategory = this.cr.updateCategory(cdto, cid);

		return new ResponseEntity<CategoryDto>(updatedCategory, HttpStatus.OK);
	}

//	delete
	@DeleteMapping("/{cid}")
	public ResponseEntity<ApiResponse> updateCategory(@PathVariable Integer cid) {

		this.cr.deleteCategory(cid);

		return new ResponseEntity<ApiResponse>(new ApiResponse("Category deleted succesfully", true), HttpStatus.OK);
	}

//	get
	@GetMapping("/{cid}")
	public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Integer cid) {

		CategoryDto c = this.cr.getCategory(cid);

		return new ResponseEntity<CategoryDto>(c, HttpStatus.OK);
	}

//	get all
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getALLCategories() {

		List<CategoryDto> cdto = this.cr.getCategories();

		return new ResponseEntity<List<CategoryDto>>(cdto, HttpStatus.OK);
	}

}
