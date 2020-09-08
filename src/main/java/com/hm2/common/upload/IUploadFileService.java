package com.hm2.common.upload;

import com.hm2.common.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface IUploadFileService {

    List<UploadFileVo> uploadMultiPartFile(MultipartFile[] files, String module) throws IOException;

    UploadFileVo uploadFile(MultipartFile file, String module) throws IOException;

    boolean isFile(String module, String fileName);

    Resource download(String moduleName, String fileName);
}

@Service
@Transactional
class UploadFileService implements IUploadFileService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${defaultUploadPath}")
    private String dir;

    @Override
    public List<UploadFileVo> uploadMultiPartFile(MultipartFile[] files, String module) throws IOException {

        List<UploadFileVo> voList = new ArrayList<>();
        for (MultipartFile file : files) {
            UploadFileVo vo = uploadFile(file, module);
            voList.add(vo);
        }
        return voList;
    }

    @Override
    public UploadFileVo uploadFile(MultipartFile file, String module) throws IOException {

        UploadFileVo vo = new UploadFileVo();

        String path = dir + "/" + module + "/";
        File convert = new File(path);
        if (!convert.exists() && convert.mkdirs()) {
            logger.info("creating directory: {}", convert.getName());
            boolean result = false;
            try {
                convert.mkdirs();
                result = true;
            } catch (SecurityException se) {
                logger.info("Not DIR created : {}", convert.getName());
            }
            if (result) {
                logger.info("DIR created");
            }
        }

        String fileName = file.getOriginalFilename();
        String[] splitFileName = fileName.split("\\.");
        fileName = DateUtils.formatDate(new Date(), DateUtils.YYYYMMDDHHMMSS)
                + "-"
                + UUID.randomUUID().toString()
                + "." + splitFileName[splitFileName.length - 1];

        path += fileName;
        convert = new File(path);
        convert.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(convert);
        fileOutputStream.write(file.getBytes());
        fileOutputStream.close();

        vo.setPath(path);
        vo.setName(fileName);
        vo.setSize(file.getSize());
        vo.setType(file.getContentType());
        logger.info("Upload file success");
        return vo;
    }

    @Override
    public boolean isFile(String module, String fileName) {
        String filePath = dir + "/" + module + "/" + fileName;
        File f = new File(filePath);
        if (f.exists() && !f.isDirectory()) {
            return true;
        }
        return false;
    }

    @Override
    public Resource download(String moduleName, String fileName) {
        try {
            Path root = Paths.get(dir + "/" + moduleName);
            Path file = root.resolve(fileName);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("File not found " + fileName, ex);
        }
    }
}
