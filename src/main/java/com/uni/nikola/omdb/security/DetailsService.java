package com.uni.nikola.omdb.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.uni.nikola.omdb.models.User;
import com.uni.nikola.omdb.repositories.UserRepository;

@Service
public class DetailsService implements UserDetailsService {
	
	private UserRepository userRepo;
	
	@Autowired
	public DetailsService(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		final User user = userRepo.findByUsername(username);
		if(null == user) {
			throw new UsernameNotFoundException("User with username "+username+" doesn't exists!");
		}
		return new DetailedUser(user);
	}

}
