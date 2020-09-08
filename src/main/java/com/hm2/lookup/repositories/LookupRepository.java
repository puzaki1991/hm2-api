package com.hm2.lookup.repositories;

import java.util.List;

import com.hm2.common.repositories.CommonJpaCrudRepository;
import com.hm2.lookup.entities.Lookup;

public interface LookupRepository extends CommonJpaCrudRepository<Lookup, Long>{

	List<Lookup> findByTypeOrderByOrderAsc(String type);
	List<Lookup> findByTypeAndGroupOrderByOrderAsc(String type, String group1);
	List<Lookup> findByTypeAndGroup2OrderByOrderAsc(String type, String group2);
	Lookup findByTypeAndValueOrderByOrderAsc(String type, String value);
}
