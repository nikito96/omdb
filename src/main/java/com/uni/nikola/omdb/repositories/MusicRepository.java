package com.uni.nikola.omdb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uni.nikola.omdb.models.Song;

@Repository
public interface MusicRepository extends JpaRepository<Song, Integer> {

}
