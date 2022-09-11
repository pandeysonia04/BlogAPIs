package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;


import com.example.demo.controller.PostResource;
import com.example.demo.entity.Post;
import com.example.demo.payload.PostDto;
import com.example.demo.service.PostService;

@RunWith(SpringRunner.class)
@WebMvcTest(value=PostResource.class)
public class PostControllerValidation {
	
	@Autowired
	private PostResource postController;
	
	@MockBean
	private PostService postService;
	
	@Test
	public void testcreatePost() {
		
		PostDto postDto= new PostDto();
		postDto.setId(Long.valueOf(1));
		postDto.setTitle("Junit Test");
		postDto.setContent("Good to test with Junit");
		postDto.setDescription("Testing with Junit");
		
		ResponseEntity<PostDto> responseEntity= postController.createPost(postDto);
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
		
		
	}
	
	
}
