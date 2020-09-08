package com.hm2.ums.vo;

import com.hm2.ums.entities.User;

import lombok.Data;

@Data
public class UserVo {
	private User user = new User();
	private String password;
	private String tel;
	private Long gameAgentId;

	@Override
	public String toString() {
		return "UserVo [user=" + user + ", tel=" + tel + "]";
	}

}
