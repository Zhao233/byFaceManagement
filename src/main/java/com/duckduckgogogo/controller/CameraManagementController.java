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
                                         @RequestParam("entranceGuardType") String entranceGuardType,@RequestParam("entranceGuardNO") String entranceGuardNO, @RequestParam("cameraXY") String cameraXY,
                                         @RequestParam("time1") int time1, @RequestParam("cameraType") int cameraType, @RequestParam("faceWidth") int faceWidth){

        String result = cameraService.addCamera(cameraName, rtspUrl, server, entranceGuard, entranceGuardType, entranceGuardNO, cameraXY, time1, cameraType, faceWidth);
        Map<String, Object> r = r = new HashMap<>();
//


        if(JSONHandler.isSuccess(result)){

            r.put("status", "SUCCEED");
        } else {
            JSONObject object = JSONObject.fromObject(result);

            if(object.getString("errorMessage") != null){
                r.put( "errorMessage", object.getString("errorMessage") );
            }

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

        System.out.println("search camera succeed : "+result);


        try {
            JSONObject object = JSONObject.fromObject(result);
            r.put("total", object.getInt("total"));
            r.put("rows", object.getJSONArray("rows"));
            r.put("status", "SUCCEED");
        } catch (JSONException e){
            System.out.println("错误："+e.getMessage());
            r.put("status", "FAILED");


        }

        return r;
    }

    @RequestMapping("/update")
    @ResponseBody
    private Map<String, Object> updateCamera(@RequestParam("server") int server, @RequestParam("entranceGuard") String entranceGuard,
                                             @RequestParam("entranceGuardType") String entranceGuardType,
                                             @RequestParam("version") int version, @RequestParam("rtspUrl") String rtspUrl,
                                             @RequestParam("cameraXY") String cameraXY, @RequestParam("entranceGuardNO") String entranceGuardNO,
                                             @RequestParam("id") int id, @RequestParam("cameraName") String cameraName,
                                             @RequestParam("time1") int time1, @RequestParam("cameraType") int cameraType, @RequestParam("faceWidth") int faceWidth){
        Map<String, Object> r = new HashMap<>();

        String result = cameraService.updateCamera(id, version, cameraName, rtspUrl, server, entranceGuard,entranceGuardType, entranceGuardNO, cameraXY, time1, cameraType, faceWidth);

        if(JSONHandler.isSuccess(result)){
            r.put("status", "SUCCEED");
        } else {
            JSONObject object = JSONObject.fromObject(result);

            if(object.getString("errorMessage") != null){
                r.put( "errorMessage", object.getString("errorMessage") );
            }

            r.put("status", "FAILED");
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
            JSONObject object = JSONObject.fromObject(result);

            if(object.getString("errorMessage") != null){
                r.put( "errorMessage", object.getString("errorMessage") );
            }

            r.put("status", "FAILED");
        }

        return r;
    }

    @RequestMapping("/get/{id}")
    @ResponseBody
    private Map<String, Object> getCamera(@PathVariable int id){
        Map<String, Object> r = new HashMap<>();

        String result = cameraService.get(id);

        System.out.println("get camera succeed : "+result);

        if(JSONHandler.isSuccess(result)){
            JSONObject object = JSONObject.fromObject(result);

            r.put("id",object.getString("id"));
            r.put("cameraName",object.getString("cameraName"));
            r.put("rtspUrl",object.getString("rtspUrl"));
            r.put("server",object.getString("server"));
            r.put("entranceGuardNO",object.getString("entranceGuardNO"));
            r.put("cameraXY",object.getString("cameraXY"));
            r.put("entranceGuard",object.getString("entranceGuard"));
            r.put("entranceGuardType",object.getString("entranceGuardType"));
            r.put("time1", object.getString("time1"));
            r.put("cameraType", object.getString("cameraType"));
            r.put("faceWidth", object.getString("faceWidth"));
            r.put("version", object.getString("version"));

            r.put("status","SUCCEED");
        } else {
            JSONObject object = JSONObject.fromObject(result);

            if(object.getString("errorMessage") != null){
                r.put( "errorMessage", object.getString("errorMessage") );
            }

            r.put("status", "FAILED");
        }

        return r;
    }
}
