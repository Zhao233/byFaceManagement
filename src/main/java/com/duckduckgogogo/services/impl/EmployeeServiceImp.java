package com.duckduckgogogo.services.impl;

import com.duckduckgogogo.services.EmployeeService;
import com.duckduckgogogo.utils.DeleteCamera;
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

@Service("employeeService")
public class EmployeeServiceImp extends Info implements EmployeeService {
    @Override
    public String searchEmployee(String search, int offset, int limit, String role) {
        try{
            String url = "http://" + super.serverIP + "/api/face/search";

            RestTemplate rest = new RestTemplate();

            MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
            param.add("search", search);
            param.add("offset", offset);
            param.add("limit", limit);
            param.add("role", role);

            rest.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));

            return rest.postForObject(url, param, String.class);

        } catch (Exception e){
            return "error : "+e.getMessage();
        }
    }

    @Override
    public String addEmployee(FileSystemResource image, String personName, String personNumber, String cardNumber, String IDNumber, String phoneNumber) {
        try{
            String url = "http://" + super.serverIP + "/api/face/add";
            RestTemplate rest = new RestTemplate();

            JSONObject object = new JSONObject();
            object.put("personName", personName);
            object.put("personNumber", personNumber);
            object.put("cardNumber",cardNumber);
            object.put("IDNumber",IDNumber);
            object.put("phoneNumber",phoneNumber);
            object.put("role","staff");

            MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
            param.add("personInfo", object);
            param.add("imageData", image);

            rest.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));

            return rest.postForObject(url, param, String.class);
        } catch (Exception e){
            return "error : "+e.getMessage();
        }
    }

    @Override
    public String addEmployee(String personName, String personNumber, String cardNumber, String IDNumber, String phoneNumber) {
        try{
            String url = "http://" + super.serverIP + "/api/face/add";
            RestTemplate rest = new RestTemplate();

            JSONObject object = new JSONObject();
            object.put("personName", personName);
            object.put("personNumber", personNumber);
            object.put("cardNumber",cardNumber);
            object.put("IDNumber",IDNumber);
            object.put("phoneNumber",phoneNumber);
            object.put("role","staff");

            MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
            param.add("personInfo",object);

            FileSystemResource image =new FileSystemResource("");
            param.add("imageData", image);

            rest.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));

            return rest.postForObject(url, param, String.class);
        } catch (Exception e){
            return "error : "+e.getMessage();
        }
    }


    @Override
    public String updateEmployee(FileSystemResource resource, int personID, String personName, String personNumber, String cardNumber, String IDNumber, String phoneNumber, int version) {
        try{
            String url = "http://" + super.serverIP + "/api/face/update";

            RestTemplate rest = new RestTemplate();

            JSONObject object = new JSONObject();
            object.put("id", personID);
            object.put("personName", personName);
            object.put("role", "staff");
            object.put("personNumber", personNumber);
            object.put("cardNumber", cardNumber);
            object.put("IDNumber", IDNumber);
            object.put("phoneNumber", phoneNumber);
            object.put("version", version);

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
    public String deleteEmployee(List<Integer> list) {
        try{
            String url = "http://" + super.serverIP + "/api/face/delete";

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
    public String getEmployeeById(int id) {
        try{
            String url = "http://" + super.serverIP + "/api/face/get/"+id;

            RestTemplate rest = new RestTemplate();

            return rest.getForObject(url, String.class);

        } catch (Exception e){
            return "error : "+e.getMessage();
        }
    }
}
