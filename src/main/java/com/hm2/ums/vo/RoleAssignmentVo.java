package com.hm2.ums.vo;

import java.util.List;

public class RoleAssignmentVo {

	private Long userId;
	private List<Long> roleIds;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public List<Long> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(List<Long> roleIds) {
		this.roleIds = roleIds;
	}

}
