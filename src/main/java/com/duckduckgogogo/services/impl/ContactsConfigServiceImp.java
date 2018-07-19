package com.duckduckgogogo.services.impl;

import com.duckduckgogogo.services.ContactsConfigService;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

@Service("contactsConfigService")
public class ContactsConfigServiceImp implements ContactsConfigService {
    @Override
    public String updateAlarm(int times, int version) {
        String url = "http://" + Info.serverIP + "/api/config/updateAlarm";

        String request = "{\"times\":"+times+",\"version\":"+version+"}";

        RestTemplate rest = new RestTemplate();
        rest.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));

        String string = rest.postForObject(url, request, String.class);
        System.out.println("update alarm success : "+ string);

        return string;

    }
}
