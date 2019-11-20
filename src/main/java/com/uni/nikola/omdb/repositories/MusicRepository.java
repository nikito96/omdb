package com.uni.nikola.omdb.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.uni.nikola.omdb.models.Song;

@Repository
public interface MusicRepository extends JpaRepository<Song, Integer> {
	List<Song> findByNameLikeOrArtistLikeOrGenre(String name, String artist, String genre);
}