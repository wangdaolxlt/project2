package com.lxlt.controller;

import com.lxlt.bean.BaseRespVo;
import com.lxlt.bean.Storage;
import com.lxlt.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @PackgeName: com.lxlt.controller
 * @ClassName: StorageController
 * @Author: Li Haiquan
 * Date: 2020/5/29 16:17
 * project name: project2
 */
@RestController
@RequestMapping("admin/storage")

public class StorageController {

    @Autowired
    StorageService storageService;

    @RequestMapping("create")
    public BaseRespVo create(MultipartFile file){
        BaseRespVo<Storage> baseRespVo = new BaseRespVo<>();
        Storage storage = new Storage();
        try{
            storage = storageService.create(file);
        }catch (Exception e){
            e.printStackTrace();
            baseRespVo.setErrno(502);
            baseRespVo.setErrmsg("服务器错误");
            return baseRespVo;
        }
        baseRespVo.setErrmsg("成功");
        baseRespVo.setData(storage);
        baseRespVo.setErrno(0);
        return baseRespVo;
    }
}
