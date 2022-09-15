package com.example.demo.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Role;
import com.example.demo.payload.JwtAuthResponse;
import com.example.demo.payload.LoginDto;
import com.example.demo.payload.SignupDto;
import com.example.demo.repo.RoleRepository;
import com.example.demo.repo.UserRepository;
import com.example.demo.security.JwtTokenProvider;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value= "Auth controller exposes sign in  and sign up REST APIs")
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@ApiOperation(value="REST API to register or Signup user to Blog app")
	@PostMapping("/signin")
	public ResponseEntity<JwtAuthResponse> authenticateUser(@RequestBody LoginDto loginDto) {

		Authentication authentication = authManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		// get token from tokenProvider
		String token = jwtTokenProvider.generateToken(authentication);

		return ResponseEntity.ok(new JwtAuthResponse(token));
	}

	@ApiOperation(value="REST API to sign in  user to Blog app")
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody SignupDto signUpDto) {

		// check for username exists in repository
		if (userRepository.existsByUsername(signUpDto.getUsername())) {
			return new ResponseEntity<>("Username is already taken", HttpStatus.BAD_REQUEST);
		}

		// check if email exists in DB
		if (userRepository.existsByEmail(signUpDto.getEmail())) {
			return new ResponseEntity<>("Email is already taken", HttpStatus.BAD_REQUEST);

		}

		com.example.demo.entity.User user = new com.example.demo.entity.User();
		user.setName(signUpDto.getName());
		user.setUsername(signUpDto.getUsername());
		user.setEmail(signUpDto.getEmail());
		user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

		Role roles = roleRepository.findByName("ROLE_ADMIN").get();
		user.setRoles(Collections.singleton(roles));

		userRepository.save(user);

		return new ResponseEntity<>("User Registered successfully", HttpStatus.OK);

	}

}
