package com.duckduckgogogo.controller;

import com.duckduckgogogo.services.ContactsConfigService;
import com.duckduckgogogo.utils.JSONHandler;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/console/contacts_config")
public class ContactsConfigController {

    @Autowired
    private ContactsConfigService contactsConfigService;

    @RequestMapping("/updateAlarm")
    @ResponseBody
    private Map<String, Object> updateAlarm(@RequestParam("times") int times, @RequestParam("version") int version) {
        Map<String, Object> r = new HashMap<>();

        String response = contactsConfigService.updateAlarm(times, version+1);

        if (JSONHandler.isSuccess(response)) {
            r.put("status", "SUCCEED");
        } else {
            JSONObject object = JSONObject.fromObject(response);

            if(object.getString("errorMessage") != null){
                r.put( "errorMessage", object.getString("errorMessage") );
            }

            r.put("status", "FAILED");
        }

        return r;
    }
}
