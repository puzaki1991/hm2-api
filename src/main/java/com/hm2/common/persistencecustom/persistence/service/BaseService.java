package com.hm2.common.persistencecustom.persistence.service;

import com.hm2.common.entities.BaseEntity;
import com.hm2.common.persistencecustom.jpa.dao.BaseCustomDao;
import com.hm2.lookup.entities.Lookup;
import com.hm2.lookup.services.LookupService;
import com.hm2.ums.repositories.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BaseService {

    @Autowired
    private BaseCustomDao dao;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LookupService lookUpService;

    public BaseService() {
        super();
    }

    // API

    public BaseEntity saveBaseEntity(final BaseEntity entity) {
        if (entity.getId() != null && entity.getId() != 0)
            return dao.update(entity);
        else
            return dao.create(entity);
    }

    public void saveBaseEntityAll(final List<BaseEntity> baseEntities) {
        for (BaseEntity entity : baseEntities)
            saveBaseEntity(entity);
    }

    public BaseEntity findOne(final long id, Class<BaseEntity> clazzToSet) {
        dao.setClazz(clazzToSet);
        return dao.findOne(id);
    }

    public List<BaseEntity> findAll() {
        return dao.findAll();
    }

    public void delete(long id) {
        dao.deleteById(id);
    }

    public void delete(BaseEntity entity) {
        dao.delete(entity);
    }

    public String getBankLabelFromLookup(String value) {
        Lookup lookup = lookUpService.getLookupValue("TYPE_BANK_CODE", value);
        if (lookup == null)
            return "";
        return lookup.getLabel();
    }

    public String getBankAccountTypeLabelFromLookup(String value) {
        Lookup lookup = lookUpService.getLookupValue("TYPE_BANK_ACCOUNT", value);
        if (lookup == null)
            return "";
        return lookup.getLabel();
    }

    public String getTypeBonusFromLookup(String value) {
        Lookup lookup = lookUpService.getLookupValue("TYPE_BONUS", value);
        if (lookup == null)
            return "";
        return lookup.getLabel();
    }
}
