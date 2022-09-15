package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.example.demo.payload.CommentDto;
import com.example.demo.service.CommentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "CRDU Rest APIs for Comment APIs ")
@RestController
@RequestMapping("/api/v1")
public class CommentController {

	private CommentService commentService;

	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}

	// Save comment for some postId
	@ApiOperation(value = "Create POST")
	@PostMapping("/posts/{postId}/comments")
	public ResponseEntity<CommentDto> createComment( @PathVariable(value = "postId") long postid,
			@Valid @RequestBody CommentDto commentDto) {

		return new ResponseEntity<CommentDto>(commentService.createComment(postid, commentDto), HttpStatus.CREATED);

	}

	// Get COmments by Post Id
@ApiOperation(value = "get comment of post by postid")
	@GetMapping("/posts/{postId}/comments")
	public List<CommentDto> getCommentsByPostId(@PathVariable(value = "postId") long postId) {

		return commentService.getCommentByPostId(postId);

	}

@ApiOperation(value = "Get POST by postid and comment by commentId Rest api")
	@GetMapping("/posts/{postId}/comments/{commentId}")
	public ResponseEntity<CommentDto> getCommentById(@PathVariable(value = "postId") long postId,
			@PathVariable(value = "commentId") long commentId) {

		CommentDto commentDto = commentService.getCommentById(postId, commentId);
		return new ResponseEntity<>(commentDto, HttpStatus.OK);

	}

	@PutMapping("/posts/{postId}/comments/{commentId}")
	public ResponseEntity<CommentDto> updateCommentById(@PathVariable(value = "postId") long postId,
			@PathVariable(value = "commentId") long commentId,@Valid @RequestBody CommentDto commentDto) {

		CommentDto updatedCommentDto = commentService.updateCommentById(postId, commentId, commentDto);

		return new ResponseEntity<CommentDto>(updatedCommentDto, HttpStatus.OK);
	}

	@DeleteMapping("/posts/{postId}/comments/{commentId}")
	public ResponseEntity<String> deleteCommentById(@PathVariable(value = "postId") long postId,
			@PathVariable(value = "commentId") long commentId) {

		commentService.deleteCommentById(postId, commentId);

		return new ResponseEntity<>("Comment deleted Successfully", HttpStatus.OK);
	}

}
