package com.duckduckgogogo.controller;

import com.duckduckgogogo.services.TraceService;
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
@RequestMapping("/console/trace")
public class TraceController {
    @Autowired
    private TraceService traceService;

    @RequestMapping("search")
    @ResponseBody
    public Map<String, Object> search(
            @RequestParam("startDate") String startTime,
            @RequestParam("endDate") String endTime,
            @RequestParam("id") int id) {
        Map<String, Object> map = new HashMap<>();

        String result = traceService.search(startTime,endTime,id);

        if(JSONHandler.isSuccess(result)){
            System.out.println("查询成功："+result);
            JSONArray array = JSONArray.fromObject(result);

            map.put("list", array);
            map.put("status", "SUCCEED");
        } else {
            System.out.println("打印失败: "+result);

            map.put("status","FAILED");
        }

        return map;
    }
}
