package com.lxlt.controller.admin;

import com.lxlt.bean.BaseRespVo;
import com.lxlt.service.footprintservice.FootprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @PackgeName: com.lxlt.controller
 * @ClassName: UserController
 * @Author: admin
 * Date: 2020/5/29 14:28
 * project name: project2
 * @Version:
 * @Description:
 */

@RestController
@RequestMapping("admin/footprint")
public class FootprintController {

    @Autowired
    FootprintService footprintService;

    @RequestMapping("list")
    public BaseRespVo list() {
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        HashMap<String, Object> hashMap = footprintService.queryAllFootprints();
        baseRespVo.setData(hashMap);
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }
}
