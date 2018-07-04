package com.duckduckgogogo.services.impl;

import com.duckduckgogogo.services.ContactsService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Service("contactsService")
public class ContactsServiceImp extends Info implements ContactsService {
    @Override
    public String searchContactsInfo(String search, String offset, String limit) {
        String url = "http://" + super.serverIP + "/api/contacts/search";

        RestTemplate rest = new RestTemplate();

        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
        param.add("search", search);
        param.add("offset", offset);
        param.add("limit", limit);

        rest.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));

        return rest.postForObject(url, param, String.class);
    }

    @Override
    public String addContactsInfo(String personName, String phoneNumber, String sendPhoneNumber, String email, String sendEmail) {
        String url = "http://" + super.serverIP + "/api/contacts/add";
        RestTemplate rest = new RestTemplate();
        JSONObject param = new JSONObject();

        param.put("personName", personName);
        param.put("phoneNumber", phoneNumber);
        param.put("sendPhoneNumber",sendPhoneNumber);
        param.put("email",email);
        param.put("sendEmail",sendEmail);

        rest.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));

        return rest.postForObject(url, param, String.class);
    }

    @Override
    public String updateContactsInfo(int id, int version, String personName, String phoneNumber, String sendPhoneNumber, String email, String sendEmail) {
        String url = "http://" + super.serverIP + "/api/contacts/update";

        RestTemplate rest = new RestTemplate();

        JSONObject param = new JSONObject();

        param.put("id", id);
        param.put("version", version);
        param.put("personName", personName);
        param.put("phoneNumber", phoneNumber);
        param.put("sendPhoneNumber",sendPhoneNumber);
        param.put("email",email);
        param.put("sendEmail",sendEmail);

        rest.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));

        return rest.postForObject(url, param, String.class);
    }

    @Override
    public String deleteContactsInfo(List<Integer> list) {
        String url = "http://" + super.serverIP + "/api/contacts/delete";

        JSONArray array = new JSONArray();
        JSONObject object = new JSONObject();

        for(int temp : list){
            object.put("alarmContactsID",temp);

            array.add(object);
        }

        RestTemplate rest = new RestTemplate();

        return rest.postForObject(url, array, String.class);
    }

    @Override
    public String getContactsInfoById(int id) {
        String url = "http://" + super.serverIP + "/api/contacts/get/"+id;

        RestTemplate rest = new RestTemplate();

        return rest.getForObject(url, String.class);
    }
}
