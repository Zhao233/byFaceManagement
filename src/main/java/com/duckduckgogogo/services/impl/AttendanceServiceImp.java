package com.duckduckgogogo.services.impl;

import com.duckduckgogogo.services.AttendanceService;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

@Service("attendanceService")
public class AttendanceServiceImp extends Info implements AttendanceService {
    @Override
    public String searchServers(String search, int offset, int limit, String startDate, String endDate) {
        try{
            String url = "http://" + super.serverIP + "/api/attendance/search";

            RestTemplate rest = new RestTemplate();

            MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
            param.add("search", search);
            param.add("offset", offset);
            param.add("limit", limit);
            param.add("startDate", startDate);
            param.add("endDate", endDate);

            //默认值


            rest.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));

            return rest.postForObject(url, param, String.class);

        } catch (Exception e){
            return "error : "+e.getMessage();
        }
    }
}
