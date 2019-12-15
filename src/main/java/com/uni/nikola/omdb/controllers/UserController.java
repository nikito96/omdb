package com.uni.nikola.omdb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uni.nikola.omdb.models.User;
import com.uni.nikola.omdb.repositories.UserRepository;

@RestController
public class UserController {
	
	private UserRepository userRepo;
	private PasswordEncoder passwordEncoder;

	@Autowired
	public UserController(UserRepository userRepo, PasswordEncoder passwordEncoder) {
		this.userRepo = userRepo;
		this.passwordEncoder = passwordEncoder;
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> register(
			@RequestParam(name="username", required=true) String username,
			@RequestParam(name="email", required=true) String email,
			@RequestParam(name="password", required=true) String password) {
		if((username.length() > 0 && username.length() > 0) && password.length() > 0) {
			String encodedPassword = null;
			encodedPassword = passwordEncoder.encode(password);
			final User user = new User(username, email, encodedPassword);
			userRepo.saveAndFlush(user);
			return ResponseEntity.ok("login.html");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}
	
	@GetMapping("/user")
	public User user() {
		User user = userRepo.findByUsername("admin");
		return user;
	}
}
