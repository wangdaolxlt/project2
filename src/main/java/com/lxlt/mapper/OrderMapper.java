package com.lxlt.mapper;

import com.lxlt.bean.Order;
import com.lxlt.bean.OrderExample;
<<<<<<< HEAD
import com.lxlt.bean.statbean.StatOrder;
=======

>>>>>>> 4f9631b51957df43fa0e6cdf17f0374c4cbb9d38
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {
    long countByExample(OrderExample example);

    List<StatOrder> selectOrder();

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
    //int selectDetail(Integer id);
//List<Order> queryAllOrder();
//    List<OrderListData> selectById(@Param("userId") Integer userId);
//    List<OrderL1Data> selectL1(@Param("userId") Integer userId);
}
