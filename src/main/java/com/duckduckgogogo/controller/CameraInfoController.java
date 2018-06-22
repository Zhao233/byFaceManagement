package com.duckduckgogogo.controller;

import com.duckduckgogogo.services.CameraInfoService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/console/camera_info")
public class CameraInfoController {
    @Autowired
    private CameraInfoService cameraInfoService;

    @RequestMapping("/getchart")
    @ResponseBody
    public Map<String, Object> getChart(@RequestParam("cameraID") int id,
                                        @RequestParam("date") String date){
        Map<String, Object> r = new HashMap<>();

        String result = cameraInfoService.getChart(id,date);

        JSONArray array = JSONArray.fromObject(result);

        r.put("data", array);

        System.out.println("查询结果:" + array);

        return r;
    }


    @RequestMapping("/getrank")
    @ResponseBody
    public Map<String, Object> Rank(@RequestParam("cameraID") int id,
                                        @RequestParam("date") String date){
        Map<String, Object> r = new HashMap<>();

        String result = cameraInfoService.getChart(id,date);

        JSONArray array = JSONArray.fromObject(result);

        r.put("data", array);

        System.out.println("查询结果:" + array);

        return r;
    }

}
