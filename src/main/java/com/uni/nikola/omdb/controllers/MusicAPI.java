package com.uni.nikola.omdb.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@GetMapping("/searchSongs")
	public ResponseEntity<List<Song>> songs(@RequestParam(required=false) String name,
			@RequestParam(required=false) String artist,
			@RequestParam(required=false) String genre) {
		final List<Song> songs = new ArrayList<>();
		
		if(null == name && null == artist && null == genre) {
			songs.addAll(musicRepo.findAll());
			return ResponseEntity.ok(songs);
		}
		
		songs.addAll(musicRepo.findByNameContainsOrArtistContainsAndGenre(name, artist, genre));
		
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
		final Song song = new Song(name, artist, genre);
		return this.musicRepo.saveAndFlush(song);
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
	public void deleteSpng(@PathVariable() Integer id) {
		this.musicRepo.deleteById(id);
	}
}
