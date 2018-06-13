package com.duckduckgogogo.controller;

import com.duckduckgogogo.domain.ConfigInfo;
import com.duckduckgogogo.services.ConfigInfoService;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import com.duckduckgogogo.utils.JSONHandler;
import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/console/faceconfig_management")
public class ConfigInfoController {
    @Autowired
    private ConfigInfoService configInfoService;

    @PostMapping("/save")
    @ResponseBody
    public Map<String, Object> save(@Param("serverIPandPort") String serverIPandPort,
                                    @Param("imageDBName") String imageDBName,
                                    @Param("receiveURL") String receiveURL,
                                    @Param("userquality") int userquality,
                                    @Param("visitorquality") int visitorquality,
                                    @Param("similarscore") int similarscore,
                                    @Param("warningscore") int warningscore,
                                    @Param("time1") int time1,
                                    @Param("time2") int time2,
                                    @Param("time3") int time3,
                                    @Param("version") int version) throws Exception {
        Map<String, Object> r = new HashMap<>();

        String result = configInfoService.save(serverIPandPort,
                imageDBName,
                receiveURL,
                userquality,
                visitorquality,
                similarscore,
                warningscore,
                time1,
                time2,
                time3,
                version);

        if(JSONHandler.isSuccess(result)){
            r.put("status", "SUCCEED");
        } else {
            Map<String, String> message = new HashMap<>();
            message.put("WARNING","error");

            r.put("message", message);
        }


        return r;
    }

    @RequestMapping("/search")
    @ResponseBody
    public Map<String ,Object> get(){
        Map<String, Object> map = new HashMap<>();

        String result = configInfoService.search();

        if(JSONHandler.isSuccess(result)){
            String[] keys = {"visitorquality","version","userquality","time1","warningscore","time2","time3","receiveURL","updateDateString","id","imageDBName","serverIPandPort","similarscore"};

            String[] elements = JSONHandler.getElements(result,keys);

            int size = keys.length;
            for(int i = 0; i < size; i++){
                map.put(keys[i], elements[i]);
            }
        } else {

        }

        return map;
    }
}
