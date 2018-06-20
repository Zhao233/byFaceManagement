package com.duckduckgogogo.services.impl;

import com.duckduckgogogo.services.CameraSearchService;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

public class CameraSearchServiceImp extends Info implements CameraSearchService {
    @Override
    public String getCameraInfo(int camereid, String datestrat, String dateend) {
        try{
            String url = "http://" + super.serverIP + "/api/query/camera";

            RestTemplate rest = new RestTemplate();

            MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
            param.add("camereid", camereid);
            param.add("datestrat", datestrat);
            param.add("dateend", dateend);

            rest.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));

            return rest.postForObject(url, param, String.class);

        } catch (Exception e){
            return "error : "+e.getMessage();
        }
    }
}
