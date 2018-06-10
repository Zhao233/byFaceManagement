package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/console")
public class ConsoleController {
    @RequestMapping("/user_management")
    public ModelAndView toUserManagement() {
        return new ModelAndView("console/user_management");
    }

    @RequestMapping("/employee_management")
    public ModelAndView toEmployeeManagement() {
        return new ModelAndView("console/employee_management");
    }

    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        return "success";
    }
}
