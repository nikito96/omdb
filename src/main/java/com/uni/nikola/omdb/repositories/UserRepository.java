package com.uni.nikola.omdb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.uni.nikola.omdb.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
	public User findByUsername(String username);
}
