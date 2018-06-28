package com.duckduckgogogo.controller;

import com.duckduckgogogo.services.CameraService;
import com.duckduckgogogo.utils.JSONHandler;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/console/camera_management")
public class CameraManagementController {

    @Autowired
    CameraService cameraService;

    @RequestMapping("/add")
    @ResponseBody
    private Map<String,Object> addCamera(@RequestParam("cameraName") String cameraName, @RequestParam("rtspUrl") String rtspUrl,
                                         @RequestParam("server") int server, @RequestParam("entranceGuard") String entranceGuard,
                                         @RequestParam("entranceGuardNO") String entranceGuardNO, @RequestParam("cameraXY") String cameraXY){

        String result = cameraService.addCamera(cameraName, rtspUrl, server, entranceGuard, entranceGuardNO, cameraXY);
        Map<String, Object> r = null;

        if(JSONHandler.isSuccess(result)){
            r = new HashMap<>();

            r.put("status", "SUCCEED");
        } else {
            r.put("status", "FAILED");
        }

        return r;
    }

    @RequestMapping("/search")
    @ResponseBody
    private Map<String,Object> searchCamera(@RequestParam(value = "search", defaultValue = "") String search,
                                                @RequestParam(value = "offset", defaultValue = "0") Integer offset,
                                                @RequestParam(value = "limit", defaultValue = "10")  Integer limit){
        Map<String, Object> r = new HashMap<>();

        String result = cameraService.searchCamera(search,String.valueOf(offset),String.valueOf(limit));

        try {
            JSONObject object = JSONObject.fromObject(result);
            r.put("total", object.getInt("total"));
            r.put("rows", object.getJSONArray("rows"));
        } catch (JSONException e){
            System.out.println("错误："+e.getMessage());


        }

        return r;
    }

    @RequestMapping("/update")
    @ResponseBody
    private Map<String, Object> updateCamera(@RequestParam("server") int server, @RequestParam("entranceGuard") String entranceGuard,
                                             @RequestParam("version") int version, @RequestParam("rtspUrl") String rtspUrl,
                                             @RequestParam("cameraXY") String cameraXY, @RequestParam("entranceGuardNO") String entranceGuardNO,
                                             @RequestParam("id") int id, @RequestParam("cameraName") String cameraName){
        Map<String, Object> r = new HashMap<>();

        String result = cameraService.updateCamera(id, version, cameraName, rtspUrl, server, entranceGuard, entranceGuardNO, cameraXY);

        if(JSONHandler.isSuccess(result)){
            r.put("status", "SUCCEED");
        } else {

        }

        return r;

    }

    @RequestMapping("/delete")
    @ResponseBody
    private Map<String, Object> deleteCamera(@RequestParam("list_ID") List<String> listOfId){
        Map<String, Object> r = new HashMap<>();

        String result = cameraService.deleteCamera(listOfId);

        if(JSONHandler.isSuccess(result)){
            r.put("status", "SUCCEED");
        } else {
            r.put("status", "FAILED");
        }

        return r;

    }

    @RequestMapping("/get/{id}")
    @ResponseBody
    private Map<String, Object> getCamera(@PathVariable int id){
        Map<String, Object> r = new HashMap<>();

        String result = cameraService.get(id);


        if(JSONHandler.isSuccess(result)){
            JSONObject object = JSONObject.fromObject(result);

            r.put("id",object.getString("id"));
            r.put("cameraName",object.getString("cameraName"));
            r.put("rtspUrl",object.getString("rtspUrl"));
            r.put("server",object.getString("server"));
            r.put("entranceGuardNO",object.getString("entranceGuardNO"));
            r.put("cameraXY",object.getString("cameraXY"));
            r.put("entranceGuard",object.getString("entranceGuard"));
            r.put("version", object.getString("version"));

            r.put("status","SUCCEED");
        } else {
            r.put("status","FAILED");
        }

        return r;
    }
}
