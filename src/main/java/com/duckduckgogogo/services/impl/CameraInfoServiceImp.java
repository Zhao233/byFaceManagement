package com.duckduckgogogo.services.impl;

import com.duckduckgogogo.services.CameraInfoService;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

@Service("cameraInfoService")
public class CameraInfoServiceImp extends Info implements CameraInfoService {
    @Override
    public String getChart(int cameraID, String date) {
        try{
            String url = "http://" + super.serverIP + "/api/query/camera/day/chart";

            RestTemplate rest = new RestTemplate();

            MultiValueMap<String, Object> param = new LinkedMultiValueMap<String, Object>();
            param.add("queryDate", date);
            param.add("id", cameraID);

            rest.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));

            return rest.postForObject(url, param, String.class);
        } catch (Exception e){
            return "error : "+e.getMessage();
        }
    }

    @Override
    public String getRank(int cameraID, String date) {
        try{
            String url = "http://" + super.serverIP + "/api/query/camera/day/ranking";

            RestTemplate rest = new RestTemplate();

            MultiValueMap<String, Object> param = new LinkedMultiValueMap<String, Object>();
            param.add("queryDate", date);
            param.add("id", cameraID);

            rest.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));

            return rest.postForObject(url, param, String.class);
        } catch (Exception e){
            return "error : "+e.getMessage();
        }
    }
}
