package com.uni.nikola.omdb.controllers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.uni.nikola.omdb.models.Song;
import com.uni.nikola.omdb.repositories.MusicRepository;

@RestController
public class MusicAPI {
	
	private MusicRepository musicRepo;
	
	@Autowired
	public MusicAPI(MusicRepository musicRepo) {
		this.musicRepo = musicRepo;
	}
	
	@GetMapping("/songs")
	public ResponseEntity<List<Song>> searchSongs(@RequestParam(required=false) String name,
			@RequestParam(required=false) String artist,
			@RequestParam String genre) {
		List<Song> songs = new ArrayList<>();
		
		if(null == name && null == artist && null == genre) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		
		songs.addAll(musicRepo.findByNameLikeOrArtistLikeOrGenre(name, artist, genre));
		
		if(songs.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.ok(songs);
	}
}
