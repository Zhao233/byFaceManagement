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
public class AttendanceManagementController {
    @Autowired
    private AttendanceService attendanceService;

    @RequestMapping("/search")
    @ResponseBody
    public Map<String, Object> search(
            @RequestParam("id") int id,
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate,
            @RequestParam("search") String search,
            @RequestParam("offset") int offset,
            @RequestParam("limit") int limit){
        Map<String, Object> r = new HashMap<>();

        String result = attendanceService.searchServers(search,offset,limit,startDate,endDate,id);

        JSONArray array = JSONArray.fromObject(result);

//      "tracedate":"2018-06-20 14:13:07",
//      "personnumber":"1003",
//      "name":"张三",
//      "id":292,
//      "cameraname":"东门入口"

        r.put("total", array.size());
        r.put("rows", array);
        return r;
    }
}
