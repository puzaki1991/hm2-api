package com.hm2.system.repositories.loginlog;

import com.hm2.common.repositories.CommonJpaCrudRepository;
import com.hm2.system.entities.LoginLog;

public interface LoginLogRepo extends CommonJpaCrudRepository<LoginLog, Long>, LoginLogRepoCustom {
	
}
