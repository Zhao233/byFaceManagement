//package com.example.demo.controller;
//
//
//import com.example.model.User;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController("user")
//public class UserManagementController {
//
//    @Autowired
//    private UserService userService;
//
//
//    @ResponseBody
//    @RequestMapping(value = "/add", produces = {"application/json;charset=UTF-8"})
//    public int addUser(User user){
//        return userService.addUser(user);
//    }
//
//
//    @ResponseBody
//    @RequestMapping(value = "/all/{pageNum}/{pageSize}", produces = {"application/json;charset=UTF-8"})
//    public Object findAllUser(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize){
//
//        return userService.findAllUser(pageNum,pageSize);
//    }
//
//
//    @RequestMapping("/test")
//    @ResponseBody
//    public String a(){
//        return "test";
//    }
//}
