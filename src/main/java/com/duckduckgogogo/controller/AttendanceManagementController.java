package com.duckduckgogogo.controller;

import com.duckduckgogogo.services.AttendanceService;
import com.duckduckgogogo.utils.JSONHandler;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/console/attendance_management")
public class AttendanceManagementController extends Logger_{
    @Autowired
    private AttendanceService attendanceService;

    @RequestMapping("/search")
    @ResponseBody
    public Map<String, Object> search(
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate,
            @RequestParam("search") String search,
            @RequestParam("offset") int offset,
            @RequestParam("limit") int limit){
        Map<String, Object> r = new HashMap<>();

        String result = attendanceService.searchServers(search,offset,limit,startDate,endDate);
        System.out.println("search attendance succeed : "+result);

        super.logger.info("search attendance succeed : "+result);

        JSONObject object = JSONObject.fromObject(result);

        r.put("total",object.getInt("total"));
        r.put("rows", object.getJSONArray("rows"));

        return r;
    }
}
