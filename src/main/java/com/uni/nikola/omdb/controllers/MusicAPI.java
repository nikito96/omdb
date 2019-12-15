package com.uni.nikola.omdb.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.uni.nikola.omdb.models.Song;
import com.uni.nikola.omdb.models.User;
import com.uni.nikola.omdb.repositories.MusicRepository;
import com.uni.nikola.omdb.repositories.UserRepository;

@RestController
public class MusicAPI {
	
	private MusicRepository musicRepo;
	private UserRepository userRepo;
	
	@Autowired
	public MusicAPI(MusicRepository musicRepo, UserRepository userRepo) {
		this.musicRepo = musicRepo;
		this.userRepo = userRepo;
	}
	
	@GetMapping("/searchSongs")
	public ResponseEntity<List<Song>> songs(@RequestParam(required=false) String name,
			@RequestParam(required=false) String genre) {
		final List<Song> songs = new ArrayList<>();
		
		if(name.length() < 1 && genre.length() < 1) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		songs.addAll(musicRepo.findByNameContainsAndGenre(name, genre));
		
		if(songs.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.ok(songs);
	}
	
	@GetMapping("/songs")
	public List<Song> all(){
		return this.musicRepo.findAll();
	}
	
	@PostMapping("/songs")
	public Song newSong(@RequestParam() String name,
			@RequestParam() String artist,
			@RequestParam() String genre) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = userRepo.findByUsername(authentication.getName());
		Song song = new Song(name, artist, genre);
		List<Song> songs = new ArrayList<>();
		songs.add(song);
		user.setSongs(songs);
		song.setUser(user);
		user = this.userRepo.saveAndFlush(user);
		song = songs.get(songs.size() -1);
		return song;
	}
	
	@GetMapping("/songs/{id}")
	public Optional<Song> getSong(@PathVariable Integer id) {
		return this.musicRepo.findById(id);
	}
	
	@PutMapping("/songs/{id}")
	public Song updateSong(@RequestParam() String name,
			@RequestParam() String artist,
			@RequestParam() String genre,
			@PathVariable Integer id) {
		return this.musicRepo.findById(id).map(song -> {
			song.setName(name);
			song.setArtist(artist);
			song.setGenre(genre);
			return this.musicRepo.saveAndFlush(song);
		}).orElseGet(() -> {
			final Song song = new Song(name, artist, genre);
			song.setId(id);
			return this.musicRepo.saveAndFlush(song);
		});
	}
	
//	@DeleteMapping("/songs/{id}")
//	public void deleteSong(@PathVariable Integer id) {
//		this.musicRepo.deleteById(id);
//	}
	
	@PostMapping("/song/{id}")
	public void deleteSong(@PathVariable() Integer id) {
		this.musicRepo.deleteById(id);
	}
}
