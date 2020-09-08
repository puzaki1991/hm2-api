package com.hm2.common.entities;

import java.io.Serializable;

public class JwtResponse implements Serializable {

	private static final long serialVersionUID = 3182074176321675979L;
	private final String jwttoken;

	public JwtResponse(String jwttoken) {
		this.jwttoken = jwttoken;
	}

	public String getToken() {
		return this.jwttoken;
	}
}