package com.example.demo.service;

import java.util.List;

import com.example.demo.controller.PostResource;
import com.example.demo.payload.PostDto;
import com.example.demo.payload.PostResponse;

public interface PostService {
	
	PostDto createPost(PostDto postDto);
	
	PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);
	
	PostDto getPostById(long id);
	
	PostDto updatePost(PostDto dto, long id);
	
	void postDeleteById(long id);

}
