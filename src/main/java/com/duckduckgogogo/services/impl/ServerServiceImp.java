package com.duckduckgogogo.services.impl;

import com.duckduckgogogo.services.ServerService;
import com.duckduckgogogo.utils.DeleteID;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.nio.charset.StandardCharsets;
import java.util.List;


/**
 *远程调用接口，操作服务器信息
 * */
@Service("serverService")
public class ServerServiceImp extends Info implements ServerService {
    /**
     * 从服务器获取服务器信息
     * */
    @Override
    public String searchServers(String search, String offset, String limit){
        try {
            String url = "http://" + super.serverIP + "/api/server/search";

            RestTemplate rest = new RestTemplate();

            MultiValueMap<String, Object> param = new LinkedMultiValueMap<String, Object>();
            param.add("search", search);
            param.add("offset", offset);
            param.add("limit", limit);

            rest.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));

            String string = rest.postForObject(url, param, String.class);

            System.out.print("查询成功：");
            System.out.println(string);

            return string;
        } catch (Exception e){
            System.out.println("Error: search error");

            return "";
        }

    }

    /**
     * 向服务器添加服务器信息
     * */
    @Override
    public String addServer(String serverName,String serverIP,String isMainServer){
        try {
            String url = "http://" + super.serverIP + "/api/server/add";
            //String filePath = "D:\\人脸识别\\4b90f603738da977215057e4bb51f8198718e386.jpg";

            RestTemplate rest = new RestTemplate();
            //FileSystemResource resource = new FileSystemResource(new File(filePath));
            MultiValueMap<String, Object> param = new LinkedMultiValueMap<String, Object>();
            param.add("personID", 83);
            param.add("personName", "test005");

            rest.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));

            String request = "{\"serverName\":\"" + serverName + "\",\"serverIP\":\"" + serverIP + "\",\"isMainServer\":\"" + isMainServer + "\"}";

            String string = rest.postForObject(url, request, String.class);

            System.out.print("增加成功：");
            System.out.println(string);

            return string;
        }catch (Exception e){
            System.out.println("Error: add error");

            return "";
        }
    }

    /**
     *向服务器提交修改信息
     * */
    @Override
    public String updateServer(int id, String serverName,String serverIP,String isMainServer,int version){
		try {
            String url = "http://" + super.serverIP + "/api/server/update";

            RestTemplate rest = new RestTemplate();

//            MultiValueMap<String, Object> param = new LinkedMultiValueMap<String, Object>();
//            param.add("id", id);
//            param.add("serverName", serverName);
//            param.add("serverIP", serverIP);
//            param.add("isMainServer", isMainServer);
//            param.add("version", version);

            rest.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));

            String request = "{\"id\":"+id+",\"serverName\":\"" + serverName + "\",\"serverIP\":\"" + serverIP + "\",\"isMainServer\":\"" + isMainServer + "\",\"version\":"+version+"}";

            System.out.println("update request: "+ request);

            String string = rest.postForObject(url, request, String.class);


            return string;
        } catch (Exception e){
		    return "";
        }
    }

    /**
     * 向服务器提交删除请求
     * */
    @Override
    public String deleteServer(List<Integer> list){
        String url = "http://"+super.serverIP+"/api/server/delete";
        //String filePath = "D:\\人脸识别\\4b90f603738da977215057e4bb51f8198718e386.jpg";

        RestTemplate rest = new RestTemplate();

        JSONArray array = new JSONArray();
        JSONObject object = new JSONObject();

        for(int temp : list){
            object.put("serverID",temp);

            array.add(object);
        }

        String request = array.toString();

        return rest.postForObject(url, request, String.class);
    }

    @Override
    public String getServerById(int id) {
        try {
            String url = "http://" + super.serverIP + "/api/server/get/" + id;

            RestTemplate rest = new RestTemplate();
            //FileSystemResource resource = new FileSystemResource(new File(filePath));

            String string = rest.getForObject(url, String.class);

            System.out.print("获取成功：");
            System.out.println(string);

            return string;
        } catch (Exception e){
            return "";
        }
    }

    @Override
    public String getServerInfoById(int id) {
        String url_status = "http://127.0.0.1:8080/api/system/resource/" + id;

        RestTemplate rest = new RestTemplate();
        rest.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));

        String string = rest.getForObject(url_status, String.class);

        return string;
    }
}
