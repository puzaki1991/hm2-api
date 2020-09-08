package com.hm2.common.upload;

import com.hm2.common.beans.ResponseData;
import com.hm2.common.controllers.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UploadController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IUploadFileService uploadFileService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseData<UploadFileVo> upload(@RequestParam("file") MultipartFile file, @RequestParam("module") String module) throws IOException {
        ResponseData<UploadFileVo> responseData = new ResponseData<>();
        responseData.setData(uploadFileService.uploadFile(file, module));
        return responseData;
    }

    @PostMapping(value = "/uploadMultipartFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseData<List<UploadFileVo>> uploadMultipartFile(@RequestParam("files") MultipartFile[] files, @RequestParam("module") String module) throws IOException {
        ResponseData<List<UploadFileVo>> responseData = new ResponseData<>();
        responseData.setData(uploadFileService.uploadMultiPartFile(files, module));

        return responseData;
    }

    @GetMapping("/download/{moduleName}/{fileName}")
    public ResponseEntity<?> download(@PathVariable String moduleName, @PathVariable String fileName) {
        Resource resource = null;
        try {
            resource = uploadFileService.download(moduleName, fileName);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(String.valueOf(MediaType.APPLICATION_OCTET_STREAM)))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
