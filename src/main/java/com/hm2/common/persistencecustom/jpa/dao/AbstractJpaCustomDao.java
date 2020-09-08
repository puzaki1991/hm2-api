package com.hm2.common.persistencecustom.jpa.dao;

import java.util.List;

import com.hm2.common.entities.BaseEntity;

public interface AbstractJpaCustomDao {

	BaseEntity findOne(long id);

    List<BaseEntity> findAll();

    BaseEntity create(BaseEntity entity);

    BaseEntity update(BaseEntity entity);

    void delete(BaseEntity entity);

    void deleteById(long entityId);

}
