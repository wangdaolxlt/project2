package com.lxlt.service.wxuserservice;

import com.lxlt.bean.Order;
import com.lxlt.bean.OrderExample;
import com.lxlt.bean.wxorderbean.WxUserOrderBean;
import com.lxlt.mapper.OrderMapper;
import com.lxlt.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @PackgeName: com.lxlt.service.wxuserservice
 * @ClassName: WxUserServiceImpl
 * @Author: Pipboy
 * Date: 2020/6/4 9:42
 * project name: project2
 * @Version:
 * @Description:
 */

@Service
public class WxUserServiceImpl implements WxUserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    OrderMapper orderMapper;

    @Override
    public HashMap<String, Object> queryAllOrders() {
        OrderExample orderExample = new OrderExample();
        orderExample.createCriteria().andIdGreaterThan(0);
        List<Order> orders = orderMapper.selectByExample(orderExample);
        Integer unrecv = 0;
        Integer uncomment = 0;
        Integer unpaid = 0;
        Integer unship = 0;

        for (Order order : orders) {
            switch (order.getOrderStatus()) {
                case 101: unpaid++; break;
                case 201: unship++; break;
                case 301: unrecv++; break;
                case 402: uncomment++; break;
            }
        }

        WxUserOrderBean wxUserOrderBean = new WxUserOrderBean(unpaid, unship, unrecv, unship);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("order", wxUserOrderBean);
        return hashMap;
    }
}
