package com.example.Agriculture.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.Agriculture.Repository.UserRepository;
import com.example.Agriculture.config.JwtTokenUtil;
import com.example.Agriculture.model.DAOUser;
import com.example.Agriculture.model.UserDTO;
import com.example.Agriculture.request.JwtRequest;
import com.example.Agriculture.response.JwtResponse;
import com.example.Agriculture.response.ReturnResponse;
import com.example.Agriculture.service.JwtUserDetailsService;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserRepository userDao;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		if (authenticationRequest.getEmail().isEmpty() || authenticationRequest.getPassword().isEmpty()) {
			return ResponseEntity.status(200)
					.body(new ReturnResponse(401, "BAD CREDENTIAL", "FILL THE DETAILS PROPERLY"));

		}
		String email = authenticationRequest.getEmail();
		DAOUser value = userDao.findByEmail(email);
		if (value == null) {
			return ResponseEntity.status(200).body(new ReturnResponse(404, "NOT FOUND", "user not found"));

		}

		authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
		// .loadUserByUsername(authenticationRequest.getEmail());

		final String token = jwtTokenUtil.generateToken(userDetails);

		JwtResponse jwtResponse = new JwtResponse(token, value);

//
//		JwtResponse jwtResponse = new JwtResponse(token, value.getUsername(), value.getEmail(),
//				value.getDate_of_birth(), value.getGender(), value.getPhone());

		return ResponseEntity.status(200).body(new ReturnResponse(200, "", "USER SIGN IN SUCCESSFUL", jwtResponse));
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody DAOUser user) throws Exception {

		if (user.getEmail().isEmpty() || user.getPhone() == null || user.getPassword().isEmpty()) {
			return ResponseEntity.status(200)
					.body(new ReturnResponse(401, "BAD CREDENTIAL", "USER DETAILS NOT PROVIDED PROPERLY", user));

		}

		if ((userDao.existsByEmail(user.getEmail()))) {
			return ResponseEntity.status(200)
					.body(new ReturnResponse(204, "EMAIL EXISTS", "EMAIL ALREADY EXIST PLEASE TRY WITH NEW EMAIL"));
		}

		userDetailsService.saveUser(user);
		return ResponseEntity.status(200).body(new ReturnResponse(200, "", "USER DETAILS ADDED SUCCESSFULLY"));
	}

	public void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

}
