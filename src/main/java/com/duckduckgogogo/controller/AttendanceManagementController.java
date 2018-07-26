package com.duckduckgogogo.controller;

import com.duckduckgogogo.services.AttendanceService;
import com.duckduckgogogo.utils.JSONHandler;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/console/attendance_management")
public class AttendanceManagementController{
    @Autowired
    private AttendanceService attendanceService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/search")
    @ResponseBody
    public Map<String, Object> search(
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate,
            @RequestParam("search") String search,
            @RequestParam("offset") String offset,
            @RequestParam(name="limit",required = false) Integer limit ){
        Map<String, Object> r = new HashMap<>();

        String result = "";

        if(limit == null){
            limit = Integer.MAX_VALUE;

            offset = "0";

            result = attendanceService.searchServers(search, Integer.parseInt(offset), limit, startDate, endDate);
        } else {
            result = attendanceService.searchServers(search, Integer.parseInt(offset), limit, startDate, endDate);
        }

        System.out.println("search attendance succeed : "+result);

        JSONObject object = JSONObject.fromObject(result);

        r.put("total",object.getInt("total"));
        r.put("rows", object.getJSONArray("rows"));

        return r;
    }


    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        logger.info("wertsdfgfsdfgn");

        return "qwertyu";
    }
}
