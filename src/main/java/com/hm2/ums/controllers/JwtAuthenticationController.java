package com.hm2.ums.controllers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hm2.common.beans.ResponseData;
import com.hm2.common.controllers.BaseController;
import com.hm2.common.entities.JwtRequest;
import com.hm2.common.entities.JwtResponse;
import com.hm2.common.utils.JwtTokenUtil;
import com.hm2.ums.services.UserDetailsServiceCustom;

@RestController
public class JwtAuthenticationController extends BaseController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsServiceCustom userDetailsService;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping(value = "/authenticate")
	public ResponseData<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		logger.info("Long => {}", authenticationRequest.toString());

		// ....
		
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);
		logger.info("Token => {}", token);
		ResponseData responseData = new ResponseData();
		responseData.setData(new JwtResponse(token));
		setMessageSuccess(responseData);
		return responseData;
	}

	private void authenticate(String username, String password) throws Exception {

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

		} catch (DisabledException e) {

			throw new Exception("USER_DISABLED", e);

		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}