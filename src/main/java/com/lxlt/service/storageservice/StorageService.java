package com.lxlt.service.storageservice;

import com.lxlt.bean.Storage;
import com.lxlt.bean.storagebean.StorageQueryBean;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @PackgeName: com.lxlt.service
 * @ClassName: StorageService
 * @Author: Li Haiquan
 * Date: 2020/5/29 17:13
 * project name: project2
 */
public interface StorageService {

    Storage create(MultipartFile myFile);

    Map queryTopic(StorageQueryBean storageQueryBean);

    Storage updateStorage(Storage requestStorage);

    int deleteById(Storage storage);
}
