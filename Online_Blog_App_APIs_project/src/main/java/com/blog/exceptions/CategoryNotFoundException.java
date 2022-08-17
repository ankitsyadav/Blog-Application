package com.blog.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryNotFoundException extends RuntimeException {

	String resourceName;
	String fieldName;
	long fieldvalue;

	public CategoryNotFoundException(String resourceName, String fieldName, long fieldvalue) {
		super(String.format("%s not found with %s  : %s", resourceName, fieldName, fieldvalue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldvalue = fieldvalue;
	}

}
