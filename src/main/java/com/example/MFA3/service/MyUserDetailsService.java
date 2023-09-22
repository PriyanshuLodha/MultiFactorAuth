package com.example.MFA3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.MFA3.model.SecureUser;
import com.example.MFA3.model.User;
import com.example.MFA3.repo.UserDetailsRepo;

@Component
public class MyUserDetailsService implements UserDetailsService {

	@Autowired UserDetailsRepo userDetailsRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) 
			throws UsernameNotFoundException {
		User u = userDetailsRepo.findByUsername(username)
								.orElse(new User());
		return new SecureUser(u);
	}

}
