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
import java.util.Map;


@Controller
@RequestMapping("/console/server_management")
public class ServerManagementController {

    @Autowired
    private ServerService serverService;

    @RequestMapping("/search")
    @ResponseBody
    public Map<String,Object> search(@RequestParam(value = "search", defaultValue = "") String search,
                                      @RequestParam(value = "offset", defaultValue = "0") Integer offset,
                                      @RequestParam(value = "limit", defaultValue = "10") Integer limit){
        Map<String, Object> map = new HashMap<>();

        String response = serverService.searchServers(search, String.valueOf(offset), String.valueOf(limit));
        System.out.println(response);

        try {
            JSONArray array = JSONArray.fromObject(response);
            JSONArray array_result = new JSONArray();

            String[] arr = new String[array.size()];

            int size = array.size();
            for (int i = 0; i < size; i++) {
                arr[i] = array.getString(i);

                JSONObject object = JSONObject.fromObject(arr[i]);
                object.remove("updateDate");
                object.remove("version");
                object.remove("enabled");

                array_result.add(object);
            }

            map.put("total", String.valueOf(size));
            map.put("rows", array_result);


            return map;
        }catch (JSONException e){
            System.out.println("error :"+e.getMessage());

            return map;
        }
    }

    @RequestMapping("/add")
    @ResponseBody
    public Map<String, Object> add(@RequestParam(value = "serverName", defaultValue = "") String serverName,
                                      @RequestParam(value = "serverIP", defaultValue = "") String serverIP,
                                      @RequestParam(value = "isMainServer", defaultValue = "") String isMainServer){
        //serverService.addServer()
        Map<String,Object> r = new HashMap<>();
        Map<String,String> message = new HashMap<>();

        String data = serverService.addServer(serverName,serverIP,isMainServer);

        boolean isSuccess = JSONHandler.isSuccess(data);

        if(isSuccess){
            r.put("status", "SUCCEED");
        } else {

        }

        return r;
    }

    @RequestMapping("/update")
    @ResponseBody
    public Map<String, Object> update(@RequestParam(value = "search", defaultValue = "") String search,
                                      @RequestParam(value = "offset", defaultValue = "0") Integer offset,
                                      @RequestParam(value = "limit", defaultValue = "10") Integer limit){
        //serverService.updateServer()

        return null;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Map<String, Object> delete(@RequestParam(value = "search", defaultValue = "") String search,
                                      @RequestParam(value = "offset", defaultValue = "0") Integer offset,
                                      @RequestParam(value = "limit", defaultValue = "10") Integer limit){
        //serverService.deleteServer()

        return null;
    }

    @RequestMapping("/test")
    @ResponseBody
    public String print(){
        return "success";
    }

    @RequestMapping("/get/{id}")
    @ResponseBody
    public Map<String,Object> get(@PathVariable String id){
        Map<String,Object> r = new HashMap<>();

        String result = serverService.getServerById(id);

        boolean isSuccess = JSONHandler.isSuccess(result);

        if(isSuccess){
            String[] keys = {"serverName","serverIP","mainServer"};
            String[] values = JSONHandler.getElements(result,keys);

            for(int i = 0; i < keys.length; i++){
                r.put(keys[i], values[i]);
            }

            return r;
        } else {
            return null;
        }


    }
}
