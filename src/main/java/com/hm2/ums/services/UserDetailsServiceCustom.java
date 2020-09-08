package com.hm2.ums.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hm2.ums.repositories.user.UserRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Transactional
@Service
public class UserDetailsServiceCustom implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		com.hm2.ums.entities.User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("No user found for " + username + ".");
		}
		user.setLastLoginDate(new Date());
		userRepository.save(user);
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		User userDetail = new User(user.getUsername(), user.getPassword(), authorities); // "{noop}"+
		return userDetail;
	}

}
