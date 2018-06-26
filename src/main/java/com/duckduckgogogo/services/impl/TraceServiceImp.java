package com.duckduckgogogo.services.impl;

import com.duckduckgogogo.services.TraceService;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

@Service("traceService")
public class TraceServiceImp extends Info implements TraceService {
    @Override
    public String search(String startDate, String endDate, int id) {
        String url = "http://" + super.serverIP + "/api/query/trace";

        RestTemplate rest = new RestTemplate();

        MultiValueMap<String, Object> param = new LinkedMultiValueMap<String, Object>();

        param.add("startDate", startDate);
        param.add("endDate", endDate);
        param.add("id", id);

        rest.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));

        return rest.postForObject(url, param, String.class);
    }
}
