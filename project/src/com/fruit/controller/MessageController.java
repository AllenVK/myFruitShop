package com.fruit.controller;

import com.alibaba.fastjson.JSONObject;
import com.fruit.base.BaseController;
import com.fruit.po.Message;
import com.fruit.service.MessageService;
import com.fruit.utils.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 留言 controller 层
 */
@Controller
@RequestMapping("/message")
public class MessageController extends BaseController {

    @Autowired
    private MessageService messageService;

    /**
     * 留言列表
     * @param message
     * @param model
     * @return
     */
    @RequestMapping("/findBySql")
    public String findBySql(Message message, Model model){
        String sql = "select * from message where 1 = 1 ";
        if(!isEmpty(message.getName())){
            sql += " and name like '%" + message.getName() + "%'";
        }
        sql += "order by id desc";
        Pager<Message> pagers = messageService.findBySqlReturnEntity(sql);
        model.addAttribute("pagers", pagers);
        model.addAttribute("obj", message);
        return "message/message";
    }


    /**
     * 删除留言
     */
    @RequestMapping("/delete")
    public String delete(Integer id){
        messageService.deleteById(id);
        return "redirect:/message/findBySql";
    }

    /**
     * 发表留言入口
     */
    @RequestMapping("/add")
    public String add(){
        return "message/add";
    }

    /**
     * 执行发表留言
     */
    @RequestMapping("/exAdd")
    @ResponseBody
    public String exAdd(Message message){
        messageService.insert(message);
        JSONObject js = new JSONObject();
        js.put("message","添加成功");
        return js.toString();
    }

}
