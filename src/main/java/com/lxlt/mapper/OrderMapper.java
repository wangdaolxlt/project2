package com.lxlt.mapper;

import com.lxlt.bean.Order;
import com.lxlt.bean.OrderExample;

import com.lxlt.bean.OrderQuery;
import com.lxlt.bean.OrderShip;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {
    long countByExample(OrderExample example);

    int deleteByExample(OrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    List<Order> selectByExample(OrderExample example);

    Order selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByExample(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    //List<Order> selectOrderList(@Param("orderQuery") OrderQuery orderQuery);
    int ship(@Param("orderShip") OrderShip orderShip);
    //int selectDetail(Integer id);
//List<Order> queryAllOrder();
//    List<OrderListData> selectById(@Param("userId") Integer userId);
//    List<OrderL1Data> selectL1(@Param("userId") Integer userId);
}
