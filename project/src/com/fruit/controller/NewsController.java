package com.fruit.controller;

import com.fruit.base.BaseController;
import com.fruit.po.News;
import com.fruit.service.NewsService;
import com.fruit.utils.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

/**
 * 公告 controller 层
 */
@Controller
@RequestMapping("/news")
public class NewsController extends BaseController {

    @Autowired
    private NewsService newsService;

    @RequestMapping("/findBySql")
    public String findBySql(News news, Model model){
        String sql = "select * from news where 1 = 1 ";
        if(!isEmpty(news.getName())){
            sql += " and name like '%" + news.getName() + "%'";
        }
        sql += "order by addTime desc";
        Pager<News> pagers = newsService.findBySqlReturnEntity(sql);
        model.addAttribute("pagers", pagers);
        model.addAttribute("obj", news);
        return "news/news";
    }

    /**
     * 公告添加
     */
    @RequestMapping("/add")
    public String add(){
        return "news/add";
    }

    /**
     * 添加执行
     */
    @RequestMapping("/exAdd")
    public String exAdd(News news){
        news.setAddTime(new Date());
        newsService.insert(news);
        return "redirect:/news/findBySql";
    }

    /**
     * 跳转到维护公告页面
     */
    @RequestMapping("/update")
    public String update(Integer id, Model model){
        News obj = newsService.load(id);
        model.addAttribute("obj", obj);
        return "news/update";
    }

    /**
     * 维护（修改）公告执行
     */
    @RequestMapping("/exUpdate")
    public String exUpdate(News news){
        newsService.updateById(news);
        return "redirect:/news/findBySql";
    }

    /**
     * 删除公告
     */
    @RequestMapping("/delete")
    public String delete(Integer id){
        newsService.deleteById(id);
        return "redirect:/news/findBySql";
    }

    /**
     * 前端公告列表
     */
    @RequestMapping("/list")
    public String list(Model model){
        Pager<News> pagers = newsService.findByEntity(new News());
        model.addAttribute("pagers", pagers);
        return "news/list";
    }

    /**
     * 跳转公告详情页面
     */
    @RequestMapping("/view")
    public String view(Integer id, Model model){
        News obj = newsService.load(id);
        model.addAttribute("obj", obj);
        return "news/view";
    }

}
