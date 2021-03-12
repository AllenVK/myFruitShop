package com.fruit.controller;

import com.fruit.base.BaseController;
import com.fruit.po.OrderDetail;
import com.fruit.service.OrderDetailService;
import com.fruit.utils.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 订单详情 controller 层
 */
@Controller
@RequestMapping("/orderDetail")
public class OrderDetailController extends BaseController {

    @Autowired
    private OrderDetailService orderDetailService;

    @RequestMapping("/uList")
    public String uList(OrderDetail orderDetail, Model model){
        // 分页查询
        String sql = "select * from order_detail where order_id = " + orderDetail.getOrderId();
        Pager<OrderDetail> pagers = orderDetailService.findBySqlReturnEntity(sql);
        model.addAttribute("pagers", pagers);
        model.addAttribute("obj", orderDetail);
        return "orderDetail/uList";
    }

}
