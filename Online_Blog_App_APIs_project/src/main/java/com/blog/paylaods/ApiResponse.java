package com.blog.paylaods;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse {

	private String message;
	private Boolean success;
	
	
}
