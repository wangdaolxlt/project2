package com.lxlt.service;


import com.lxlt.mapper.GoodsMapper;
import com.lxlt.mapper.GoodsProductMapper;
import com.lxlt.mapper.OrderMapper;
import com.lxlt.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

/**
 * @PackgeName: com.lxlt.service
 * @ClassName: DashboardServiceImpl
 * @Author: admin
 * Date: 2020/5/28 23:27
 * project name: project2
 * @Version:
 * @Description:
 */

@Service
public class DashboardServiceImpl implements DashboradService {


    @Autowired
    UserMapper userMapper;
    OrderMapper orderMapper;
    GoodsMapper goodsMapper;
    GoodsProductMapper goodsProductMapper;

    // @Override
    // public Integer queryCountsByName(String name) {
    //     if ("user".equals(name)) {
    //
    //     } else if ("order".equals(name)) {
    //         orderMapper
    //     }
    //
    // }
}
