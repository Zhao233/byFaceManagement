package com.example.demo.controller;


import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller()
@RequestMapping(value = "/console/user_management")
public class UserManagementController {

    @Autowired
    private UserService userService;

    ObjectMapper objectMapper = new ObjectMapper();

    @ResponseBody
    @RequestMapping(value = "/add", produces = {"application/json;charset=UTF-8"})
    public void addUser(User user){
        userService.addUser(user);
    }


    @ResponseBody
    @RequestMapping(value = "/all", produces = {"application/json;charset=UTF-8"}, method = RequestMethod.GET)
    public Map<String,Object> findAllUser(){
        Map<String,Object> map = new HashMap<>();

        map.put("data",userService.getAllUser());
        return map;
    }

    @RequestMapping("/test")
    @ResponseBody
    public String a(){
        return "test";
    }

    @RequestMapping("/find/mybatis/page")
    public String findUserPageFromMybatis(HttpServletRequest request, Integer pageNum, Integer pageSize) {
        pageNum = pageNum == null ? 1 : pageNum;
        pageSize = pageSize == null ? 10 : pageSize;
        PageHelper.startPage(pageNum, pageSize);
        List<User> list = userService.getAllUser();
        PageInfo pageInfo = new PageInfo(list);
        Page page = (Page) list;
        try {
            return "PageInfo: " + objectMapper.writeValueAsString(pageInfo) + ", Page: " + objectMapper.writeValueAsString(page);
        } catch (JsonProcessingException e) {
            e.printStackTrace();

            return "";
        }
    }


}
