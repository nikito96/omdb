package com.uni.nikola.omdb.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
		securedEnabled = true,
		prePostEnabled = true,
		jsr250Enabled = true
		)
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {
	
	private PasswordEncoder passwordEncoder;
	private UserDetailsService userDetailsService;

	@Autowired
	public SecurityConfigurer(PasswordEncoder passwordEncoder, DetailsService userDetailsService) {
		this.passwordEncoder = passwordEncoder;
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.cors().disable()
		.authorizeRequests()
		.antMatchers("/css/login.css").permitAll()
		.antMatchers("/register.html").permitAll()
		.antMatchers("/register").permitAll()
		.antMatchers("/update.html").permitAll()
		.antMatchers("/user").permitAll()
		.anyRequest().authenticated()
		.and().formLogin().loginPage("/login.html")
		.loginProcessingUrl("/perform_login")
		.defaultSuccessUrl("/index.html").permitAll()
		.and()
		.logout().permitAll();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		super.configure(auth);
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}
}
