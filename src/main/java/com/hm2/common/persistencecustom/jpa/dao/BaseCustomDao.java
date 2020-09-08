package com.hm2.common.persistencecustom.jpa.dao;

import org.springframework.stereotype.Repository;

import com.hm2.common.entities.BaseEntity;

@Repository
public class BaseCustomDao extends AbstractJpaDAO<BaseEntity> implements AbstractJpaCustomDao {

	public BaseCustomDao() {
		super();
		setClazz(BaseEntity.class);
	}

}
