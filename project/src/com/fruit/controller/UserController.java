package com.fruit.controller;

import com.fruit.base.BaseController;
import com.fruit.po.User;
import com.fruit.service.UserService;
import com.fruit.utils.Consts;
import com.fruit.utils.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户 controller 层
 * 自己实现对用户的操作，增加用户、删除用户、禁用用户，用户重置密码等功能。
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping("/findBySql")
    public String findBySql(Model model, User user){
        String sql = "select * from user where 1 = 1";
        if(!isEmpty(user.getUserName())){
            sql += " and userName like '%" + user.getUserName() + "%' ";
        }
        sql += " order by id";
        Pager<User> pagers = userService.findBySqlReturnEntity(sql);
        model.addAttribute("pagers", pagers);
        model.addAttribute("obj", user);
        return "user/user";
    }

    /**
     * 用户个人中心 view
     */
    @RequestMapping("/view")
    public String view(Model model, HttpServletRequest request){
        Object attribute = request.getSession().getAttribute(Consts.USERID);
        if(attribute == null){
            return "redirect:/login/uLogin";
        }
        Integer userId = Integer.valueOf(attribute.toString());
        User obj = userService.load(userId);
        model.addAttribute("obj", obj);
        return "user/view";
    }

    /**
     * 执行修改用户信息操作
     */
    @RequestMapping("/exUpdate")
    public String exUpdate(User user, HttpServletRequest request){
        Object attribute = request.getSession().getAttribute(Consts.USERID);
        if(attribute == null){
            return "redirect:/login/uLogin";
        }
        user.setId(Integer.valueOf(attribute.toString()));
        userService.updateById(user);
        return "redirect:/user/view.action";
    }

}
