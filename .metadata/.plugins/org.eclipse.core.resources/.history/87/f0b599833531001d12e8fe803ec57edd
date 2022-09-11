package com.example.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;import org.springframework.data.convert.DtoInstantiatingConverter;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Post;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.payload.PostDto;
import com.example.demo.repo.PostRepository;
import com.example.demo.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	private PostRepository postRepository;
	
	public PostServiceImpl(PostRepository postRepository) {
		this.postRepository = postRepository;
	}



	@Override
	public PostDto createPost(PostDto postDto) {
	
		
		Post post = mapToEntity(postDto);
		Post newPost= postRepository.save(post);
		PostDto postResponse= mapToDto(newPost);
		
		return postResponse;
		}


	//convert entity to DTO
	private PostDto mapToDto(Post newPost) {
		PostDto postResponse = new PostDto();
		postResponse.setId(newPost.getId());
		postResponse.setTitle(newPost.getTitle());
		postResponse.setDescription(newPost.getDescription());
		postResponse.setContent(newPost.getContent());
		return postResponse;
	}
	
	//convert DTo to entity
	private Post mapToEntity(PostDto postDto) {
		Post post = new Post();
	post.setTitle(postDto.getTitle());
	post.setDescription(postDto.getDescription());
	post.setContent(postDto.getContent());
	return post;
	}



	@Override
	public List<PostDto> getAllPosts() {
		List<Post> posts= postRepository.findAll();
		return posts.stream().map(post->mapToDto(post)).collect(Collectors.toList());
		
	}



	@Override
	public PostDto getPostById(long id) {
	 Post post = postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post", "id", id));
		return mapToDto(post);
	}



	@Override
	public PostDto updatePost(PostDto dto, long id) {
		
		//get post by id from the database, if post not exists then throw exception
		Post post = postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post", "id", id));
		post.setTitle(dto.getTitle());
		post.setContent(dto.getContent());
		post.setContent(dto.getDescription());
		
		Post updatePost = postRepository.save(post);
		
		return mapToDto(updatePost);
	}



	@Override
	public void postDeleteById(long id) {
		
		Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("post", "id", id));
		
		postRepository.delete(post);
		
	}

}
