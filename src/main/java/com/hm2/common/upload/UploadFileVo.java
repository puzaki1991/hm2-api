package com.hm2.common.upload;

import lombok.Data;

@Data
public class UploadFileVo {
    private String path;
    private Long size;
    private String type;
    private String name;
}
