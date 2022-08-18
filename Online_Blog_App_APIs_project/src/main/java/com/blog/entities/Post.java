package com.blog.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "posts")
@Data
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer pid;
	@Column(length = 100, nullable = false)
	private String ptitle;
	@Column(length = 100000)
	private String pcontent;
	private String imageName;
	private LocalDateTime addedDate;

	@ManyToOne
	private Category category;

	@ManyToOne
	private User user;

}
