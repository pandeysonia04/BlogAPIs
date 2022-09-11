package com.example.demo.service;

import java.util.List;

import com.example.demo.payload.CommentDto;

public interface CommentService {

	CommentDto createComment(long postid, CommentDto commentDto);

	List<CommentDto> getCommentByPostId(long postId);

	CommentDto getCommentById(long postId, long commentId);

	CommentDto updateCommentById(long postId, long commentId, CommentDto updatedcommentDto);

void deleteCommentById(long postId, long commentId);

}
