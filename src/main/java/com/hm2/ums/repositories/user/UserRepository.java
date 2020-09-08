package com.hm2.ums.repositories.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hm2.common.repositories.CommonJpaCrudRepository;
import com.hm2.ums.entities.User;

import java.util.List;

public interface UserRepository extends CommonJpaCrudRepository<User, Long>, UserRepositoryCustom {
	
	User findByUsername(String username);
	User findByEmail(String email);
	User findByForgotPasswordKey(String key);
	List<User> findAllByOrderByCreatedDateDesc();

	Page<User> findAll(Pageable of);
}
