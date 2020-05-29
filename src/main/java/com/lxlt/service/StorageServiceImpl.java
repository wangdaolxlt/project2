package com.lxlt.service;

import com.lxlt.bean.Storage;
import com.lxlt.mapper.StorageMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

/**
 * @PackgeName: com.lxlt.service
 * @ClassName: StorageServiceImpl
 * @Author: Li Haiquan
 * Date: 2020/5/29 17:14
 * project name: project2
 */
@Component
@ConfigurationProperties(prefix = "map")
@Data
public class StorageServiceImpl implements StorageService{

    @Autowired
    StorageMapper storageMapper;

    String domain;
    String uploadFileDirectory;
    String port;
    String staticPath;
    @Override
    public Storage create(MultipartFile file) {
        //随机生成一个uuid
        String key = UUID.randomUUID().toString().replace("-","").toLowerCase();
        String originalFilename = file.getOriginalFilename();
        int index = originalFilename.indexOf(".");
        String suffix = originalFilename.substring(index);
        //拼接生成新的文件名
        key = key + suffix;
        String path = System.getProperty("user.dir");
        path = path + uploadFileDirectory;
        //上传文件
        File newFile = new File(path, key);
        try {
            file.transferTo(newFile);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        Storage storage = new Storage();
        storage.setName(originalFilename);
        storage.setKey(key);
        storage.setType(file.getContentType());
        storage.setSize((int) file.getSize());
        storage.setDeleted(false);
        Date date = new Date();
        storage.setAddTime(date);
        storage.setUpdateTime(date);
        String url = domain + port + staticPath.substring(0,staticPath.length()-2) + key;
        storage.setUrl(url);
        storageMapper.insertSelective(storage);
        storage.setId(storageMapper.getLastInsertId());
        return storage;
    }
}
