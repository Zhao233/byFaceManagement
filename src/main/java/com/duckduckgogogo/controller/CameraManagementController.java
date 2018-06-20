package com.duckduckgogogo.controller;

import com.duckduckgogogo.services.CameraService;
import com.duckduckgogogo.utils.JSONHandler;
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

        if(JSONHandler.isSuccess(result)){
            r.put("total", JSONHandler.getJsonArrayLength(result));
            r.put("rows", JSONHandler.getJsonArryFromResponse(result,"server"));
        } else {

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
    private Map<String, Object> deleteCamera(@RequestParam(required = false, value = "list[]") List<String> listOfId){
        Map<String, Object> r = new HashMap<>();

        String result = cameraService.deleteCamera(listOfId);

        if(JSONHandler.isSuccess(result)){
            r.put("status", "SUCCEED");
        } else {

        }

        return r;

    }

    @RequestMapping("/get/{id}")
    @ResponseBody
    private Map<String, Object> getCamera(@PathVariable int id){
        Map<String, Object> r = new HashMap<>();

        String result = cameraService.get(id);

        if(JSONHandler.isSuccess(result)){
            //r.put("")

        } else {

        }

        return r;
    }
}
