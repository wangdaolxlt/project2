package com.lxlt.controller.wx;

import com.lxlt.bean.BaseRespVo;
import com.lxlt.bean.Storage;
import com.lxlt.service.storageservice.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @PackgeName: com.lxlt.controller.wx
 * @ClassName: WxStorageController
 * @Author: Li Haiquan
 * Date: 2020/6/4 4:28
 * project name: project2
 */
@RestController
@RequestMapping("wx/storage")
public class WxStorageController {
    @Autowired
    StorageService storageService;

    @RequestMapping("upload")
    public BaseRespVo upload(MultipartFile file) {
        BaseRespVo respVo = new BaseRespVo();
        Storage storage = null;
        try {
            storage = storageService.create(file);
        } catch (Exception e) {
            e.printStackTrace();
            respVo.setErrno(502);
            respVo.setErrmsg("系统内部错误");
            return respVo;
        }
        respVo.setErrno(0);
        respVo.setErrmsg("成功");
        respVo.setData(storage.getUrl());
        return respVo;
    }
}
