package com.fruit.controller;

import com.fruit.po.Item;
import com.fruit.po.Sc;
import com.fruit.service.ItemService;
import com.fruit.service.ScService;
import com.fruit.utils.Consts;
import com.fruit.utils.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 收藏 controller 层
 */
@Controller
@RequestMapping("/sc")
public class ScController {

    @Autowired
    private ScService scService;

    @Autowired
    private ItemService itemService;

    @RequestMapping("/exAdd")
    public String exAdd(Sc sc, HttpServletRequest request){
        Object attribute = request.getSession().getAttribute(Consts.USERID);
        if(attribute == null){
            return "redirect:/login/uLogin";
        }
        // 保存到收藏夹
        Integer userId = Integer.valueOf(attribute.toString());
        sc.setUserId(userId);
        scService.insert(sc);
        // 商品表收藏数 +1
        Item item = itemService.load(sc.getItemId());
        item.setScNum(item.getScNum() + 1);
        itemService.updateById(item);

        return "redirect:/sc/findBySql.action";
    }

    /**
     * 收藏列表
     */
    @RequestMapping("/findBySql")
    public String findBySql(Model model,HttpServletRequest request){
        Object attribute = request.getSession().getAttribute(Consts.USERID);
        if(attribute == null){
            return "redirect:/login/toLogin";
        }
        Integer userId = Integer.valueOf(attribute.toString());
        String sql = "select * from sc where user_id =" + userId + " order by id desc";
        Pager<Sc> pagers = scService.findBySqlReturnEntity(sql);
        model.addAttribute("pagers", pagers);
        return "sc/my";
    }

    /**
     * 取消收藏操作
     */
    @RequestMapping("/delete")
    public String delete(Integer id,HttpServletRequest request){
        Object attribute = request.getSession().getAttribute(Consts.USERID);
        if(attribute == null){
            return "redirect:/login/toLogin";
        }
        Integer userId = Integer.valueOf(attribute.toString());

        scService.deleteById(id);
        return "redirect:/sc/findBySql.action";
    }

}
