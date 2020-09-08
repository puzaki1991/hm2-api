package com.hm2.system.entities;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.hm2.common.entities.BaseEntity;

import lombok.Data;

@Entity
@Table(name = "T_LOGIN_LOG")
@Data
@AttributeOverride(name = "ID", column = @Column(name = "LOGIN_LOG_ID", nullable = false))
public class LoginLog extends BaseEntity {
	
	private static final long serialVersionUID = 1414255750881596619L;
	private String processSuccess; // Y,N
	private String username;
	private String type; // LOGIN, LOGOUT
}
