package com.example.Agriculture.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Agriculture.Repository.UserRepository;
import com.example.Agriculture.model.DAOUser;
import com.example.Agriculture.model.UserDTO;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userDao;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		DAOUser user = userDao.findByEmail(email);
		// DAOUser user = userDao.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username:=== " + email);
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				new ArrayList<>());
	}

//doubt----	
	public DAOUser saveUser(DAOUser user) {
		DAOUser newUser = new DAOUser();
		newUser.setEmail(user.getEmail());
		newUser.setUsername(user.getUsername());
		newUser.setPhone(user.getPhone());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		newUser.setImage("/UserImage/userImageDefault.png");
		newUser.setAddress(user.getAddress());
		newUser.setAadhaarNo(user.getAadhaarNo());
		newUser.setGender(user.getGender());
		newUser.setDate_of_birth(user.getDate_of_birth());
		return userDao.save(newUser);
	}

	public DAOUser update(DAOUser user) {
		return userDao.save(user);
	}
}
