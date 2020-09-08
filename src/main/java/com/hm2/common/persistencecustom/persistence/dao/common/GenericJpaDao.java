package com.hm2.common.persistencecustom.persistence.dao.common;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.hm2.common.entities.BaseEntity;
import com.hm2.common.persistencecustom.jpa.dao.AbstractJpaDAO;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class GenericJpaDao<T extends BaseEntity> extends AbstractJpaDAO<T> implements IGenericDao<T> {
    //
}