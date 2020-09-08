package com.hm2.system.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hm2.common.beans.ResponseData;
import com.hm2.common.persistencecustom.persistence.service.BaseService;
import com.hm2.system.entities.LoginLog;
import com.hm2.system.repositories.loginlog.LoginLogRepo;
import com.hm2.ums.critrria.UserCriteria;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class SystemService extends BaseService{
	@Autowired
	private LoginLogRepo loginLogRepo;
	
    public ResponseData<List<LoginLog>> searchLoginLog(UserCriteria criteria) {
        return loginLogRepo.searchLoginLog(criteria);
    }

}
