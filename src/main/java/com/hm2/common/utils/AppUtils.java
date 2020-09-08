package com.hm2.common.utils;

import javax.servlet.http.HttpServletRequest;

public class AppUtils {

    public static String getClientIp(HttpServletRequest httpReq) {
        String remoteAddr = "";
        if (httpReq != null) {
            remoteAddr = httpReq.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = httpReq.getRemoteAddr();
            }
        }
        return remoteAddr;
    }
}
