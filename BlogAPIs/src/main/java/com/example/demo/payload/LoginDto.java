package com.example.demo.payload;

import lombok.Data;

@Data
public class LoginDto {
	
	private String UsernameOrEmail;
	private String password;

}
