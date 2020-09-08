package com.hm2.common.repositories;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.hm2.common.entities.BaseEntity;

@NoRepositoryBean
public interface CommonJpaCrudRepository<T extends BaseEntity, ID extends Serializable> extends CrudRepository<T, ID> {

    /**
     * Returns all instances of the type.
     *
     * @return all entities
     */
//    @Query("select e from #{#entityName} e where e.isActive = '" + ProjectConstant.Flag.Y + "'")
//    List<T> findAllIsActive();

    /**
     * Returns the number of entities available.
     *
     * @return the number of entities
     */
   /* @Query("select count(1) from #{#entityName} e where e.isDeleted = '" + ProjectConstant.Flag.N + "'")
    long count();*/
//    @Modifying
//    @Query(value = "update  #{#entityName} e set e.isActive = 'N' where e.id= ?1 ")
//    void deleteIsActive(long id);
}