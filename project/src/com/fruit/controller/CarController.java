package com.fruit.controller;

import com.alibaba.fastjson.JSONObject;
import com.fruit.po.Item;
import com.fruit.po.Car;
import com.fruit.service.ItemService;
import com.fruit.service.CarService;
import com.fruit.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * 购物车 controller 层
 */
@Controller
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarService carService;

    @Autowired
    private ItemService itemService;

    @RequestMapping("/exAdd")
    @ResponseBody
    public String exAdd(Car car, HttpServletRequest request){
        JSONObject js = new JSONObject();
        Object attribute = request.getSession().getAttribute(Consts.USERID);
        if(attribute == null){
            js.put(Consts.RES, "0");
            return js.toJSONString();
        }
        // 保存到购物车
        Integer userId = Integer.valueOf(attribute.toString());
        car.setUserId(userId);

        Item item = itemService.load(car.getItemId());

        String price = item.getPrice();
        Double valueOf = Double.valueOf(price);
        car.setPrice(valueOf);
        if(item.getZk() != null){
            valueOf = valueOf * item.getZk()/10;
            BigDecimal bg = new BigDecimal(valueOf).setScale(2, RoundingMode.UP);
            car.setPrice(bg.doubleValue());
            valueOf = bg.doubleValue();
        }

        Integer num = car.getNum();
        Double t = valueOf * num;
        BigDecimal bg2 = new BigDecimal(t).setScale(2, RoundingMode.UP);
        double doubleValue = bg2.doubleValue();
        car.setTotal(doubleValue + "");

        carService.insert(car);

        js.put(Consts.RES, 1);

        return js.toJSONString();
    }

    /**
     * 跳转到 我的购物车 页面
     */
    @RequestMapping("/findBySql")
    public String findBySql(Model model, HttpServletRequest request){
        Object attribute = request.getSession().getAttribute(Consts.USERID);
        if(attribute == null){
            return "redirect:/login/toLogin";
        }
        Integer userId = Integer.valueOf(attribute.toString());
        String sql = "select * from car where user_id = " + userId + " order by id desc";
        List<Car> list = carService.listBySqlReturnEntity(sql);
        model.addAttribute("list", list);
        return "car/car";
    }

    /**
     * 前端删除，后端处理
     * 删除购物车中所选商品
     */
    @RequestMapping("/delete")
    public String delete(Integer id){
        carService.deleteById(id);
        return "success";
    }

}
