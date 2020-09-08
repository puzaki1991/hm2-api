package com.hm2.system.vo;

import lombok.Data;

import java.util.Date;

@Data
public class SystemVo {
    private String timeZone;
    private Date date;
    private String dateStr;
    private String ip;
}
