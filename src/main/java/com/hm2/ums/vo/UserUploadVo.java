package com.hm2.ums.vo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UserUploadVo {
    private MultipartFile[] files;


}
