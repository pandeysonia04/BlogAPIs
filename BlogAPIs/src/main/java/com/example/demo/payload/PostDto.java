package com.example.demo.payload;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
	
	
	private Long id ;
	
	@NotEmpty
	@Size(min =2, message ="Post title should have at least 2 characters")
	private String title;
	
	@NotEmpty
	@Size(min=10, message ="Post description should have at least 10 characters")
	private String description;
	
	@NotEmpty
	private String content;
	
	private Set<CommentDto> comments;
	
	
	

}
