package com.hm2.lookup.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hm2.lookup.entities.Lookup;
import com.hm2.lookup.repositories.LookupRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Transactional
@Service
public class LookupService {

    @Autowired
    private LookupRepository lookupRepository;

    List<Lookup> lookupList = new ArrayList<Lookup>();
    Map<String, Lookup> lookupTypeValueMap = new HashMap<String, Lookup>();

    //==> Initial Data Lookup
    @PostConstruct
    private void lookupPostConstruct() {
        Iterable<Lookup> findAll = lookupRepository.findAll();
        findAll.forEach(lookup -> {
        	lookupList.add(lookup);
            lookupTypeValueMap.put(lookup.getType() + lookup.getValue(), lookup);
        });
    }

    public List<Lookup> getLookupAll() {
        return lookupList;
    }

    public List<Lookup> getLookupValue(String type) {
        List<Lookup> objcectList = lookupList.stream().filter(o -> o.getType().equals(type)).collect(Collectors.toList());
        return objcectList;
    }

    //	public List<Lookup> getLookupValueByGroup1(String type, String group1) {
//		return lookupRepository.findByTypeAndGroupOrderByOrderAsc(type, group1);
//	}
//	public List<Lookup> getLookupValueByGroup2(String type, String group2) {
//		return lookupRepository.findByTypeAndGroup2OrderByOrderAsc(type, group2);
//	}
    public Lookup getLookupValue(String type, String value) {
        return lookupTypeValueMap.get(type + value);
    }
}
