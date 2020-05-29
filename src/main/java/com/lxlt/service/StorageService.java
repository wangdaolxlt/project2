package com.lxlt.service;

import com.lxlt.bean.Storage;
import org.springframework.web.multipart.MultipartFile;

/**
 * @PackgeName: com.lxlt.service
 * @ClassName: StorageService
 * @Author: Li Haiquan
 * Date: 2020/5/29 17:13
 * project name: project2
 */
public interface StorageService {

    Storage create(MultipartFile myFile);
}
