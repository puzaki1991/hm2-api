package com.hm2.ums.critrria;

import com.hm2.common.beans.PaginationRequest;

import lombok.Data;

@Data
public class UserCriteria extends PaginationRequest {

	private String username;

	@Override
	public String toString() {
		return "UserCriteria [username=" + username + "]";
	}

}
