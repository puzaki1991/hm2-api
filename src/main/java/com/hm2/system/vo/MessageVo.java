package com.hm2.system.vo;

import lombok.Data;

@Data
public class MessageVo {
    private String success;
    private String fail;
    private String saveSuccess;
    private String saveFail;
    private String deleteSuccess;
    private String deleteFail;
    private String uploadSuccess;
    private String uploadFail;

}
