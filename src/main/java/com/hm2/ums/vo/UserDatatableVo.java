package com.hm2.ums.vo;

import lombok.Data;

@Data
public class UserDatatableVo {
    private String username;
    private String email;
    private String id;
    private String createDateStr;
    private String updateDatdStr;
    private String isActive;
}
