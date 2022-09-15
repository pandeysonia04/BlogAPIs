package com.example.demo.payload;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;

@ApiModel(description = "Post model information")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
	
	@ApiModelProperty(value = "Bog Post id")
	private Long id ;
	
	@ApiModelProperty(value = "Blog post title")
	@NotEmpty
	@Size(min =2, message ="Post title should have at least 2 characters")
	private String title;
	
	@ApiModelProperty(value ="Blog post description")
	@NotEmpty
	@Size(min=10, message ="Post description should have at least 10 characters")
	private String description;
	
	@ApiModelProperty(value = "Blog Post content")
	@NotEmpty
	private String content;
	
	private Set<CommentDto> comments;
	
	
	

}
