package com.example.demo.controller;


import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller()
@RequestMapping(value = "/console/user_management")
public class UserManagementController {

    @Autowired
    private UserService userService;


    @ResponseBody
    @RequestMapping(value = "/add", produces = {"application/json;charset=UTF-8"})
    public void addUser(User user){
        userService.addUser(user);
    }


    @ResponseBody
    @RequestMapping(value = "/all", produces = {"application/json;charset=UTF-8"})
    public List<User> findAllUser(){

        return userService.getAllUser();
    }


    @RequestMapping("/test")
    @ResponseBody
    public String a(){
        return "test";
    }
}
