package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class EmployeeManagementController {

    @Autowired
    private UserService userService;

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
}
