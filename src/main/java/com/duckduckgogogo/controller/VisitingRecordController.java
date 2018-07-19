package com.duckduckgogogo.controller;

import com.duckduckgogogo.services.EmployeeService;
import com.duckduckgogogo.services.VisitingRecordService;
import com.duckduckgogogo.services.VisitorService;
import com.duckduckgogogo.utils.JSONHandler;
import com.duckduckgogogo.utils.PasswordEncodeAssistant;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/console/visitorRecord_management")
public class VisitingRecordController {
    @Autowired
    private VisitingRecordService visitingRecordService;

    @RequestMapping("/search")
    @ResponseBody
    public  Map<String,Object> search(@RequestParam(value = "search", defaultValue = "") String search,
                                      @RequestParam(value = "offset", defaultValue = "0") Integer offset,
                                      @RequestParam(value = "limit", defaultValue = "10") Integer limit){
        Map<String, Object> r = new HashMap<>();

        String response = visitingRecordService.searchVisitingRecord(search, offset, limit);

        System.out.println("search visitingRecord succeed : "+response);

        JSONObject object = JSONObject.fromObject(response);

        r.put("total",object.getInt("total"));
        JSONArray array = object.getJSONArray("rows");

        for(int i = 0 ; i < array.size(); i++){
            array.getJSONObject(i).remove("server");
        }

        array.remove("server");
        r.put("rows", array);

        return r;
    }

    @RequestMapping("/add")
    @ResponseBody
    public  Map<String,Object> add(@RequestParam(value = "userInfo") int userInfo,
                                   @RequestParam(value = "cause") String cause,
                                   @RequestParam(value = "startDate") String startDate,
                                   @RequestParam(value = "endDate") String endDate
    ){
        Map<String, Object> r = new HashMap<>();

        String response = visitingRecordService.addVisitingRecord(userInfo,cause,startDate,endDate);

        System.out.println("add visitingRecord succeed : "+response);

        if(JSONHandler.isSuccess(response)){
            r.put("status","SUCCEED");
        } else {
            r.put("status", "FAILED");
        }

        return r;
    }

    @RequestMapping("/update")
    @ResponseBody
    public  Map<String,Object> update(@RequestParam(value = "id") int id,
                                      @RequestParam(value = "version") int version,
                                      @RequestParam(value = "userInfo") String userInfo,
                                      @RequestParam(value = "cause") String cause,
                                      @RequestParam(value = "startDate") String startDate,
                                      @RequestParam(value = "endDate") String endDate){

        Map<String, Object> r = new HashMap<>();

        String response = visitingRecordService.updateVisitingRecord(id, version+1, userInfo, cause, startDate, endDate);

        System.out.println("update visitingRecord succeed : "+response);

        if(JSONHandler.isSuccess(response)){
            r.put("status","SUCCEED");
        } else {
            r.put("status", "FAILED");
        }
        return r;
    }

    @RequestMapping("/get/{id}")
    @ResponseBody
    public  Map<String,Object> get(@PathVariable(value = "id") int id){
        Map<String, Object> r = new HashMap<>();

        String response = visitingRecordService.getVisitingRecordById(id);

        System.out.println("get visitingRecord succeed : "+response);

        if(JSONHandler.isSuccess(response)){
            JSONObject object = JSONObject.fromObject(response);

            r.put("userInfo",object.getString("userInfo"));
            r.put("cause",object.getString("cause"));
            r.put("updateDateString",object.getString("updateDateString"));
            r.put("id",object.getString("id"));
            r.put("startDateString",object.getString("startDateString"));
            r.put("version",object.getString("version"));
            r.put("enabled",object.getString("enabled"));
            r.put("endDateString",object.getString("endDateString"));

            r.put("status","SUCCEED");
        } else {
            r.put("status","FAILED");
        }

        return r;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public  Map<String,Object> delete(@RequestParam(value = "list_ID") List<Integer> list){
        Map<String, Object> r = new HashMap<>();

        String respons = visitingRecordService.deleteVisitingRecord(list);

        if(JSONHandler.isSuccess(respons)){
            r.put("status","SUCCEED");
        } else {
            r.put("status","FAILED");
        }


        return r;
    }


}
