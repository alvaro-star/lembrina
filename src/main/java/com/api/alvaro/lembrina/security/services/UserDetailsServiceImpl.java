package com.api.alvaro.lembrina.security.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.api.alvaro.lembrina.security.entities.MainUser;
import com.api.alvaro.lembrina.security.entities.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	private final UserService userService;

	public UserDetailsServiceImpl(UserService userService) {
		this.userService = userService;
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userService.getByUserName(userName).get();
		return MainUser.build(user);
	}

}
