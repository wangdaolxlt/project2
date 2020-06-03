package com.lxlt.service.storageservice;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.lxlt.bean.Storage;
import com.lxlt.bean.StorageExample;
import com.lxlt.bean.storagebean.StorageQueryBean;
import com.lxlt.mapper.StorageMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

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
    @Transactional(rollbackFor = Exception.class)
    public Storage create(MultipartFile file) {
        //随机生成一个uuid
        String key = UUID.randomUUID().toString().replace("-","").toLowerCase();
        String originalFilename = file.getOriginalFilename();
        int index = originalFilename.lastIndexOf(".");
        String suffix = originalFilename.substring(index);
        //拼接生成新的文件名
        key = key + suffix;
        //上传文件
        File newFile = new File(uploadFileDirectory, key);
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
        String url = domain + port + staticPath + key;
        storage.setUrl(url);
        storageMapper.insertSelective(storage);
        return storage;
    }

    @Override
    public Map queryTopic(StorageQueryBean storageQueryBean) {
        PageHelper.startPage(storageQueryBean.getPage(), storageQueryBean.getLimit());
        StorageExample storageExample = new StorageExample();
        StorageExample.Criteria criteria = storageExample.createCriteria();
        criteria.andDeletedEqualTo(false);

        //对象key
        if(StringUtil.isNotEmpty(storageQueryBean.getKey())){
            criteria.andKeyEqualTo(storageQueryBean.getKey());
        }
        //对象名称模糊查询
        if(StringUtil.isNotEmpty(storageQueryBean.getName())){
            criteria.andNameLike("%" + storageQueryBean.getName() + "%");
        }

        storageExample.setOrderByClause(storageQueryBean.getSort() + " " + storageQueryBean.getOrder());
        List<Storage> storages = storageMapper.selectByExample(storageExample);
        PageInfo<Storage> pageInfo = new PageInfo(storages);
        long total = pageInfo.getTotal();
        Map<String, Object> topicMap = new HashMap<>();
        topicMap.put("items", storages);
        topicMap.put("total", total);
        return topicMap;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Storage updateStorage(Storage requestStorage) {
        requestStorage.setUpdateTime(new Date());
        StorageExample storageExample = new StorageExample();
        storageExample.createCriteria().andIdEqualTo(requestStorage.getId());
        storageMapper.updateByExampleSelective(requestStorage, storageExample);
        return requestStorage;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteById(Storage storage) {
        StorageExample storageExample = new StorageExample();
        storageExample.createCriteria().andIdEqualTo(storage.getId());
        storage.setUpdateTime(new Date());
        storage.setDeleted(true);
        int storageCode = storageMapper.updateByExampleSelective(storage, storageExample);
        if(storageCode == 0){
            return 500;
        }
        return 200;
    }
}
