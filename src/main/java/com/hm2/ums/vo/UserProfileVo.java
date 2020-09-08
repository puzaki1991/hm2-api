package com.hm2.ums.vo;

import lombok.Data;

import java.util.List;

@Data
public class UserProfileVo {
	private String username;
	private List<String> roles;
	private String isActive;
	private String accessMenu;

	@Override
	public String toString() {
		return "UserProfileVo [username=" + username + ", roles=" + roles + ", isActive=" + isActive + "]";
	}
}
