package com.uni.nikola.omdb.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Song {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String name;
	private String artist;
	private String genre;
	
	public Song() {
		
	}
	
	public Song(int id, String name, String artist, String genre) {
		this.id = id;
		this.name = name;
		this.artist = artist;
		this.genre = genre;
	}
}
