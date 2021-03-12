package com.fruit.service.impl;

import com.fruit.base.BaseDao;
import com.fruit.base.BaseServiceImpl;
import com.fruit.mapper.OrderDetailMapper;
import com.fruit.po.OrderDetail;
import com.fruit.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 订单详情 service 层
 */
@Service
public class OrderDetailServiceImpl extends BaseServiceImpl<OrderDetail> implements OrderDetailService {

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Override
    public BaseDao<OrderDetail> getBaseDao() {
        return orderDetailMapper;
    }
}
