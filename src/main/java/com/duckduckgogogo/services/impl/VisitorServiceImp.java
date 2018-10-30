package com.duckduckgogogo.services.impl;

import com.duckduckgogogo.services.VisitorService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Service("visitorService")
public class VisitorServiceImp extends Info implements VisitorService {
    @Override
    public String searchVisitor(String search, int offset, int limit, String role) {
        try{
            String url = "http://" + Info.serverIP + "/api/face/search";

            RestTemplate rest = new RestTemplate();

            MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
            param.add("search", search);
            param.add("offset", offset);
            param.add("limit", limit);
            param.add("role", role);

//            JSONObject object = new JSONObject();
//            object.put("search",search);
//            object.put("offset",offset);
//            object.put("limit",limit);
//            object.put("role",role);

            rest.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));

            return rest.postForObject(url, param, String.class);

        } catch (Exception e){
            return "error : "+e.getMessage();
        }
    }

    @Override
    public String addVisitor(FileSystemResource resource, String personName, String IDNumber, String phoneNumber, String title) {
        try{
            String url = "http://" + Info.serverIP + "/api/face/add";
            RestTemplate rest = new RestTemplate();
            MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();

            JSONObject object = new JSONObject();
            object.put("personName", personName);
            object.put("IDNumber",IDNumber);
            object.put("phoneNumber",phoneNumber);
            object.put("role","visitor");
            object.put("title",title);

            if(resource == null){
                resource = new FileSystemResource("");
            }

            param.add("personInfo", object);
            param.add("imageData", resource);

            rest.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));

            return rest.postForObject(url, param, String.class);
        } catch (Exception e){
            return "error : "+e.getMessage();
        }
    }

    @Override
    public String updateVisitor(FileSystemResource resource, int personID, String personName, String IDNumber, String phoneNumber, String title, int version) {
        try{
            String url = "http://" + Info.serverIP + "/api/face/update";

            RestTemplate rest = new RestTemplate();

            JSONObject object = new JSONObject();
            object.put("id", personID);
            object.put("personName", personName);
            object.put("role", "visitor");
            object.put("IDNumber", IDNumber);
            object.put("phoneNumber", phoneNumber);
            object.put("title", title);
            object.put("version", version);

            if(resource == null){
                resource = new FileSystemResource("");
            }

            MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
            param.add("personInfo", object);
            param.add("imageData", resource);

            rest.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));

            return rest.postForObject(url, param, String.class);
        } catch (Exception e){
            return "error : "+e.getMessage();
        }
    }

    @Override
    public String deleteVisitor(List<Integer> list) {
        try{
            String url = "http://" + Info.serverIP + "/api/face/delete";

            JSONArray array = new JSONArray();
            JSONObject object = new JSONObject();


            for(int temp : list){
                object.put("personID",temp);

                array.add(object);
            }

            String request = array.toString();

            RestTemplate rest = new RestTemplate();

            return rest.postForObject(url, request, String.class);

        } catch (Exception e){
            return "";
        }
    }

    @Override
    public String getVisitorById(int id) {
        try{
            String url = "http://" + Info.serverIP + "/api/face/get/"+id;

            RestTemplate rest = new RestTemplate();

            return rest.getForObject(url, String.class);

        } catch (Exception e){
            return "error : "+e.getMessage();
        }
    }
}
