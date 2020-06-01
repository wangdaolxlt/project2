package com.lxlt.controller.admin;

import com.lxlt.bean.BaseQueryBean;
import com.lxlt.bean.BaseRespVo;
import com.lxlt.bean.Storage;
import com.lxlt.bean.storagebean.StorageQueryBean;
import com.lxlt.service.storageservice.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

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

    @RequestMapping("list")
    public BaseRespVo list(StorageQueryBean storageQueryBean){
        BaseRespVo baseRespVo = new BaseRespVo();
        Map storageMap = storageService.queryTopic(storageQueryBean);
        if(storageMap == null){
            baseRespVo.setErrmsg("服务器内部错误");
            baseRespVo.setErrno(502);
            return baseRespVo;
        }
        baseRespVo.setErrno(0);
        baseRespVo.setData(storageMap);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    @RequestMapping("update")
    public BaseRespVo update(@RequestBody Storage requestStorage) {
        BaseRespVo baseRespVo = new BaseRespVo();
        Storage updateStorage = storageService.updateStorage(requestStorage);
        if (updateStorage == null) {
            baseRespVo.setErrmsg("服务器内部错误");
            baseRespVo.setErrno(502);
            return baseRespVo;
        }
        baseRespVo.setErrno(0);
        baseRespVo.setData(updateStorage);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    @RequestMapping("delete")
    public BaseRespVo delete(@RequestBody Storage storage){
        BaseRespVo baseRespVo = new BaseRespVo();
        int code = storageService.deleteById(storage);
        if(code != 200){
            baseRespVo.setErrno(502);
            baseRespVo.setErrmsg("服务器内部错误");
            return baseRespVo;
        }
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }
}
