package com.duckduckgogogo.controller;

import com.duckduckgogogo.services.ContactsService;
import com.duckduckgogogo.utils.JSONHandler;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/console/contacts_management")
public class ContactsManagementController {
    @Autowired
    private ContactsService contactsService;

    @RequestMapping("/search")
    @ResponseBody
    private Map<String,Object> search(@RequestParam(value = "search", defaultValue = "") String search,
                                      @RequestParam(value = "offset", defaultValue = "0") String offset,
                                      @RequestParam(value = "limit", defaultValue = "10") String limit){
        Map<String, Object> r = new HashMap<>();

        String response = contactsService.searchContactsInfo(search, offset, limit);
        System.out.println("search contacts succeed : "+response);

        JSONObject object = JSONObject.fromObject(response);

        r.put("total",object.getInt("total"));

        r.put("rows", object.getJSONArray("rows"));

        return r;
    }

    @RequestMapping("/add")
    @ResponseBody
    private Map<String, Object> add(@RequestParam(value = "personName") String personName,
                                    @RequestParam(value = "phoneNumber") String phoneNumber,
                                    @RequestParam(value = "sendPhoneNumber") String sendPhoneNumber,
                                    @RequestParam(value = "email") String email,
                                    @RequestParam(value = "sendEmail") String sendEmail){
        Map<String,Object> r = new HashMap<>();

        String respond = contactsService.addContactsInfo(personName, phoneNumber, sendPhoneNumber, email, sendEmail);


        if(JSONHandler.isSuccess(respond)){
            r.put("status", "SUCCEED");
        } else {
            r.put("status", "FAILED");
        }

        return r;
    }

    @RequestMapping("/update")
    @ResponseBody
    private Map<String, Object> update(@RequestParam(value = "id") int id,
                                       @RequestParam(value = "version") int version,
                                       @RequestParam(value = "personName") String personName,
                                       @RequestParam(value = "phoneNumber") String phoneNumber,
                                       @RequestParam(value = "sendPhoneNumber") String sendPhoneNumber,
                                       @RequestParam(value = "email") String email,
                                       @RequestParam(value = "sendEmail") String sendEmail){
        Map<String, Object> r = new HashMap<>();

        String result = contactsService.updateContactsInfo(id, version+1, personName, phoneNumber, sendPhoneNumber,email,sendEmail);

        if(JSONHandler.isSuccess(result)){
            r.put("status","SUCCEED");
        } else {
            r.put("status","FAILED");
        }

        return r;
    }

    @RequestMapping("/delete")
    @ResponseBody
    private Map<String, Object> delete(@RequestParam(value = "list_ID") List<Integer> list_id){
        Map<String, Object> r = new HashMap<>();
        String result = contactsService.deleteContactsInfo(list_id);

        if(JSONHandler.isSuccess(result)){
            r.put("status","SUCCEED");
        } else {
            r.put("status","FAILED");
        }

        return r;
    }

    @RequestMapping("/get/{id}")
    @ResponseBody
    private Map<String,Object> get(@PathVariable int id){
        Map<String,Object> map = new HashMap<>();

        String response = contactsService.getContactsInfoById(id);
        System.out.println(response);

        JSONObject object = JSONObject.fromObject(response);

        map.put("id", object.getString("id"));
        map.put("version", object.getString("version"));
        map.put("personName", object.getString("personName"));
        map.put("phoneNumber", object.getString("phoneNumber"));
        map.put("sendPhoneNumber", object.getString("sendPhoneNumber"));
        map.put("email", object.getString("email"));
        map.put("sendEmail", object.getString("sendEmail"));

        return map;
    }
}
