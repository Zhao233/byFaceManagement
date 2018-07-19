package com.duckduckgogogo.services.impl;

import com.duckduckgogogo.services.VisitingRecordService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Service("visitingRecordService")
public class VisitingRecordServiceImp extends Info implements VisitingRecordService {
    @Override
    public String searchVisitingRecord(String search, int offset, int limit) {
        String url = "http://" + Info.serverIP + "/api/visitingRecord/search";

        RestTemplate rest = new RestTemplate();

        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
        param.add("search", search);
        param.add("offset", offset);
        param.add("limit", limit);

        rest.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));

        String result = rest.postForObject(url, param, String.class);

        return result;
    }

    @Override
    public String addVisitingRecord(int userInfo, String cause, String startDate, String endDate) {
        String url = "http://" + Info.serverIP + "/api/visitingRecord/add";
        RestTemplate rest = new RestTemplate();

        String param = "{\"userInfo\":"+userInfo+",\"cause\":\""+cause+"\",\"startDate\":\""+startDate+"\",\"endDate\":\""+endDate+"\"}";


        rest.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));

        String result = rest.postForObject(url, param, String.class);

        return result;
    }

    @Override
    public String updateVisitingRecord(int id, int version, String userInfo, String cause, String startDate, String endDate) {
        String url = "http://" + Info.serverIP + "/api/visitingRecord/update";
        RestTemplate rest = new RestTemplate();
        JSONObject object = new JSONObject();

        object.put("id", id);
        object.put("version", version);
        object.put("userInfo", userInfo);
        object.put("cause", cause);
        object.put("startDate", startDate);
        object.put("endDate", endDate);

        String param = object.toString();

        rest.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));

        String result = rest.postForObject(url, param, String.class);

        return result;
    }

    @Override
    public String deleteVisitingRecord(List<Integer> list) {
        String url = "http://"+Info.serverIP+"/api/visitingRecord/delete";

        RestTemplate rest = new RestTemplate();

        JSONArray array = new JSONArray();
        JSONObject object = new JSONObject();

        for(int temp : list){
            object.put("visitingRecordID",temp);

            array.add(object);
        }

        String request = array.toString();

        String result = rest.postForObject(url, request, String.class);

        return result;
    }

    @Override
    public String getVisitingRecordById(int id) {
        String url = "http://" + Info.serverIP + "/api/visitingRecord/get/"+id;
        RestTemplate rest = new RestTemplate();

        rest.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));

        String result = rest.getForObject(url, String.class);

        return result;
    }
}
