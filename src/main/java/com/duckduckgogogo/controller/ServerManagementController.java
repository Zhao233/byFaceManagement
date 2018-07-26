package com.duckduckgogogo.controller;

import com.duckduckgogogo.services.ServerService;
import com.duckduckgogogo.utils.JSONHandler;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/console/server_management")
public class ServerManagementController {

    @Autowired
    private ServerService serverService;

    @RequestMapping("/search")
    @ResponseBody
    private Map<String,Object> search(@RequestParam(value = "search", defaultValue = "") String search,
                                      @RequestParam(value = "offset", defaultValue = "0") Integer offset,
                                      @RequestParam(value = "limit", defaultValue = "10") Integer limit){
        Map<String, Object> r = new HashMap<>();

        String response = serverService.searchServers(search, String.valueOf(offset), String.valueOf(limit));

        System.out.println("search server succeed : "+response);

        JSONObject object = JSONObject.fromObject(response);

        r.put("total",object.getInt("total"));

        r.put("rows", object.getJSONArray("rows"));

        return r;
    }

    @RequestMapping("/add")
    @ResponseBody
    private Map<String, Object> add(@RequestParam(value = "serverName") String serverName,
                                      @RequestParam(value = "serverIP") String serverIP,
                                      @RequestParam(value = "isMainServer") String isMainServer){
        Map<String,Object> r = new HashMap<>();

        String data = serverService.addServer(serverName,serverIP,isMainServer);

        boolean isSuccess = JSONHandler.isSuccess(data);

        if(isSuccess){
            r.put("status", "SUCCEED");
        } else {
            JSONObject object = JSONObject.fromObject(data);

            if(object.getString("errorMessage") != null){
                r.put( "errorMessage", object.getString("errorMessage") );
            }

            r.put("status", "FAILED");

            r.put("status", "FAILED");
        }

        return r;
    }

    @RequestMapping("/update")
    @ResponseBody
    private Map<String, Object> update(@RequestParam(value = "id") int id,
                                      @RequestParam(value = "serverName") String serverName,
                                      @RequestParam(value = "serverIP") String serverIP,
                                      @RequestParam(value = "isMainServer") String isMainServer,
                                      @RequestParam(value = "version") int version){
        Map<String, Object> r = new HashMap<>();

        String result = serverService.updateServer(id,serverName,serverIP,isMainServer,version+1);

        if(JSONHandler.isSuccess(result)){
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

    @RequestMapping("/delete")
    @ResponseBody
    private Map<String, Object> delete(@RequestParam(value = "list_ID") List<Integer> list_id){
        Map<String, Object> r = new HashMap<>();
        String result = serverService.deleteServer(list_id);

        if(JSONHandler.isSuccess(result)){
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

    @RequestMapping("/test")
    @ResponseBody
    private String print(){
        return "success";
    }

    @RequestMapping("/get/{id}")
    @ResponseBody
    private Map<String,Object> get(@PathVariable int id){
        Map<String,Object> map = new HashMap<>();

        String response = serverService.getServerById(id);

        System.out.println("get server succeed : "+response);


        try {
            if(JSONHandler.isSuccess(response)) {
                JSONObject object = JSONObject.fromObject(response);

                map.put("serverName",object.getString("serverName"));
                map.put("serverIP",object.getString("serverIP"));
                map.put("isMainServer",object.getString("mainServer"));
                map.put("version",object.getString("version"));

                map.put("status", "SUCCEED");
            } else {
                JSONObject object = JSONObject.fromObject(response);

                if(object.getString("errorMessage") != null){
                    map.put( "errorMessage", object.getString("errorMessage") );
                }

                map.put("status", "FAILED");

                map.put("status","FAILED");
            }

            return map;
        }catch (JSONException e){
            System.out.println("error :"+e.getMessage());

            return map;
        }

    }
}
