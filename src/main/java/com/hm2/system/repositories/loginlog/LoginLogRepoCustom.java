package com.hm2.system.repositories.loginlog;

import java.util.List;
import com.hm2.common.beans.ResponseData;
import com.hm2.system.entities.LoginLog;
import com.hm2.ums.critrria.UserCriteria;


public interface LoginLogRepoCustom{

	ResponseData<List<LoginLog>> searchLoginLog(UserCriteria criteria);

}