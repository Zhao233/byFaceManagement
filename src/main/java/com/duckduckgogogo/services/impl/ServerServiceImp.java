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
public class ServerServiceImp implements ServerService {
    private static final String serverIP = "10.8.20.255:8080";

    /**
     * 从服务器获取服务器信息
     * */
    @Override
    public String searchServers(String search, String offset, String limit){
        try {
            String url = "http://" + this.serverIP + "/api/server/search";
            //String filePath = "D:\\人脸识别\\4b90f603738da977215057e4bb51f8198718e386.jpg";

            RestTemplate rest = new RestTemplate();
            //FileSystemResource resource = new FileSystemResource(new File(filePath));
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
            String url = "http://" + this.serverIP + "/api/server/add";
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
    public String updateServer(String serverName,String serverIP,String isMainServer){
		try {
            String url = "http://" + this.serverIP + "/api/server/update";
            //String filePath = "D:\\人脸识别\\4b90f603738da977215057e4bb51f8198718e386.jpg";

            RestTemplate rest = new RestTemplate();
            //FileSystemResource resource = new FileSystemResource(new File(filePath));
            MultiValueMap<String, Object> param = new LinkedMultiValueMap<String, Object>();
            param.add("personID", 83);
            param.add("personName", "test005");

            JSONObject message = new JSONObject();

            message.put("search", "");
            message.put("offset", 0);
            message.put("limit", 5);

            rest.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));

            String request = "{\"serverName\":\"" + serverName + "\",\"serverIP\":\"" + serverIP + "\",\"isMainServer\":\"" + isMainServer + "\"}";

            String string = rest.postForObject(url, request, String.class);

            System.out.print("修改成功：");
            System.out.println(string);

            return string;
        } catch (Exception e){
		    return "";
        }
    }

    /**
     * 向服务器提交删除请求
     * */
    @Override
    public String deleteServer(List<String> list){
        String url = "http://"+this.serverIP+"/api/server/delete";
        //String filePath = "D:\\人脸识别\\4b90f603738da977215057e4bb51f8198718e386.jpg";

        RestTemplate rest = new RestTemplate();
        //FileSystemResource resource = new FileSystemResource(new File(filePath));
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<String, Object>();
        param.add("search", "");
        param.add("offset", 0);
        param.add("limit", 5);

        JSONObject message = new JSONObject();

        message.put("search", "");
        message.put("offset", 0);
        message.put("limit", 5);

        rest.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));

        JSONArray array = new JSONArray();

        int size = list.size();
        for(int i = 0; i < size;i++){
            array.add(new DeleteID(list.get(i)));
        }

        //String request = "[{\"serverID\":2},{\"serverID\":3}]";
        String request = array.toString();

        String string = rest.postForObject(url, request, String.class);

        System.out.print("删除成功：");
        System.out.println(string);

        return string;
    }

    @Override
    public String getServerById(String id) {
        try {
            String url = "http://" + this.serverIP + "/api/server/get/" + id;

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
}
