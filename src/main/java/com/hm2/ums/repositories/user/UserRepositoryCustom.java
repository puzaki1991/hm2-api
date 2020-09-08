package com.hm2.ums.repositories.user;

import java.util.List;

import com.hm2.common.beans.ResponseData;
import com.hm2.ums.critrria.UserCriteria;
import com.hm2.ums.entities.User;

public interface UserRepositoryCustom {
    ResponseData<List<User>> searchUser(UserCriteria criteria);
}