package com.duckduckgogogo.services.impl;

import com.duckduckgogogo.services.FaceInfoService;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

public class FaceInfoServiceImp extends Info implements FaceInfoService {
    @Override
    public String searchFace(String search, int offset, int limit) {
        try{
            String url = "http://" + Info.serverIP + "/api/face/query";

            RestTemplate rest = new RestTemplate();

            MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
            param.add("search", search);
            param.add("offset", offset);
            param.add("limit", limit);

            rest.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));

            return rest.postForObject(url, param, String.class);

        } catch (Exception e){
            return "error : "+e.getMessage();
        }
    }

    @Override
    public String updateFace(int personID, String personName) {
        return null;
    }

    @Override
    public String deleteFace(int personID) {
        return null;
    }
}
