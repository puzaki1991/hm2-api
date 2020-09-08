package com.hm2.common.utils;

import com.hm2.ums.vo.UserProfileVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserLoginUtils {

    private final static Logger logger = LoggerFactory.getLogger(UserLoginUtils.class);

    private static Authentication getUserLogin() {
        Authentication auth = null;
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            auth = SecurityContextHolder.getContext().getAuthentication();
        }
        return auth;
    }

    public static String getCurrentUsername() {
        if (getUserLogin() != null) return getUserLogin().getName();
        else return "SYSTEM";
    }

    public static List<String> getGrantedAuthorityList() {
        List<String> authorityList = null;
        if (getUserLogin() != null) {
            Collection<? extends GrantedAuthority> grantedAuthorityList = getUserLogin().getAuthorities();
            authorityList = new ArrayList<>();
            for (GrantedAuthority grantedAuthority : grantedAuthorityList) {
                authorityList.add(grantedAuthority.getAuthority());
            }
        } else {
            new ArrayList<>();
        }
        logger.info("Method getGrantedAuthorityList :  {} success", authorityList);
        return authorityList;
    }

    public static UserProfileVo getCurrentUser() {
        UserProfileVo userProfile = new UserProfileVo();
        String username = getCurrentUsername();
        List<String> roles = getGrantedAuthorityList();
        userProfile.setUsername(username);
        userProfile.setRoles(roles);
        return userProfile;
    }

}
