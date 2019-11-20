package com.uni.nikola.omdb.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MusicAPI {
	
	@GetMapping("/songs")
	public String getSongs() {
		return null;
	}
}
