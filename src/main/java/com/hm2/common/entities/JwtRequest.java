package com.hm2.common.entities;

import lombok.Data;

import java.io.Serializable;

@Data
public class JwtRequest implements Serializable {

	private static final long serialVersionUID = 7846774703659153076L;
	private String username;
	private String password;
	private String gameAgentCode;

//need default constructor for JSON Parsing
	public JwtRequest() {
	}

	public JwtRequest(String username, String password) {
		this.setUsername(username);
		this.setPassword(password);
	}

	@Override
	public String toString() {
		return "JwtRequest{" +
				"username='" + username + '\'' +
				", password='" + password + '\'' +
				", gameAgentCode='" + gameAgentCode + '\'' +
				'}';
	}
}