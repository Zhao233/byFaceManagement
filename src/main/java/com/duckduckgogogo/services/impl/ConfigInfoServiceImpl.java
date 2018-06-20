package com.duckduckgogogo.services.impl;

import com.duckduckgogogo.domain.ConfigInfo;
import com.duckduckgogogo.services.ConfigInfoService;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.nio.charset.StandardCharsets;

@Service("configInfoService")
@Transactional
public class ConfigInfoServiceImpl extends Info implements ConfigInfoService {
    @Override
    public ConfigInfo findById(long id) {

        //http://ip:port/api/config/queryhttp://ip:port/api/config/query
        try {
            String url = "http://" + this.serverIP + "/api/server/search";
            //String filePath = "D:\\人脸识别\\4b90f603738da977215057e4bb51f8198718e386.jpg";

            RestTemplate rest = new RestTemplate();
            //FileSystemResource resource = new FileSystemResource(new File(filePath));
            MultiValueMap<String, Object> param = new LinkedMultiValueMap<String, Object>();


            rest.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));

            String string = rest.postForObject(url, param, String.class);

            System.out.print("查询成功：");
            System.out.println(string);

            return null;
        } catch (Exception e){
            System.out.println("Error: search error");

            return null;
        }
    }

    @Override
    public String search(){

        try {
            String url = "http://" + super.serverIP + "/api/config/query";

            RestTemplate rest = new RestTemplate();

            String string = rest.getForObject(url, String.class);

            System.out.print("查询成功：");
            System.out.println(string);

            return string;
        } catch (Exception e){
            System.out.println("Error: search error");

            return "";
        }
    }

    @Override
    public String save(String serverIPandPort, String imageDBName, String receiveURL, int userquality,
                    int visitorquality, int similarscore, int warningscore, int time1,
                    int time2, int time3, int version) {
        try {
            String url = "http://" + super.serverIP + "/api/config/update";
            //String filePath = "D:\\人脸识别\\4b90f603738da977215057e4bb51f8198718e386.jpg";

            String request = "{\"visitorquality\":"+visitorquality+"," +
                    "\"userquality\":"+userquality+"," +
                    "\"time1\":"+time1+"," +
                    "\"warningscore\":"+warningscore+"," +
                    "\"time2\":"+time2+"," +
                    "\"time3\":"+time3+"," +
                    "\"receiveURL\":\""+receiveURL+"\"," +
                    "\"imageDBName\":\""+imageDBName+"\"," +
                    "\"serverIPandPort\":\""+serverIPandPort+"\"," +
                    "\"similarscore\":"+similarscore+"," +
                    "\"version\":"+version+"}";

            System.out.println("the request : "+request);


            RestTemplate rest = new RestTemplate();

            String string = rest.postForObject(url,request,String.class);

            System.out.println(string);

            return string;
        } catch (Exception e){
            System.out.println("Error: search error");

            return "";
        }
    }
}
