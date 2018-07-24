package com.duckduckgogogo.controller;

import com.duckduckgogogo.services.CameraInfoService;
import com.duckduckgogogo.services.CameraService;
import net.sf.json.JSON;
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
@RequestMapping("/console/camera_info")
public class CameraInfoController {
    @Autowired
    private CameraInfoService cameraInfoService;

    @Autowired
    private CameraService cameraService;

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
    public Map<String, Object>   Rank(@RequestParam("cameraID") int id,
                                        @RequestParam("date") String date){
        Map<String, Object> r = new HashMap<>();

        String result = cameraInfoService.getRank(id,date);

        JSONArray array = JSONArray.fromObject(result);

        r.put("data", array);

        System.out.println("查询结果:" + array);

        return r;
    }



    @RequestMapping("/search")
    @ResponseBody
    public Map<String, Object> search(@RequestParam("cameraID") int id,
                                      @RequestParam("date") String date){
        Map<String, Object> map = new HashMap<>();

        String result_chart = cameraInfoService.getChart(id,date);
        String result_rank = cameraInfoService.getRank(id,date);
        String result_cameraName = cameraService.get(id);

        System.out.println("查询结果 chart:" + result_chart);
        System.out.println("查询结果 rank:" + result_rank);

        JSONArray object_chart = JSONArray.fromObject(result_chart);
        JSONArray object_rank = JSONArray.fromObject(result_rank);
        JSONObject object_camera = JSONObject.fromObject(result_cameraName);

        JSONObject object = new JSONObject();
        object.put("chartData",object_chart);
        object.put("rankData", object_rank);
        object.put("cameraName", object_camera.getString("cameraName"));

        map.put("data",object);

        return map;
    }

    @RequestMapping("/search_hour")
    @ResponseBody
    public Map<String, Object> search_hour(@RequestParam("cameraID") int id,
                                      @RequestParam("date") String date){
        Map<String, Object> map = new HashMap<>();

        String result_chart = cameraInfoService.getChart_hour(id,date);
        String result_rank = cameraInfoService.getRank_hour(id,date);
        String result_cameraName = cameraService.get(id);

        System.out.println("查询结果 chart:" + result_chart);
        System.out.println("查询结果 rank:" + result_rank);

        JSONArray object_chart = JSONArray.fromObject(result_chart);
        JSONArray object_rank = JSONArray.fromObject(result_rank);
        JSONObject object_camera = JSONObject.fromObject(result_cameraName);

        JSONObject object = new JSONObject();
        object.put("chartData",object_chart);
        object.put("rankData", object_rank);
        object.put("cameraName", object_camera.getString("cameraName"));

        map.put("data",object);

        return map;
    }
}
