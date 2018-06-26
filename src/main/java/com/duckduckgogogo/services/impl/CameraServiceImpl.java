package com.duckduckgogogo.services.impl;

import com.duckduckgogogo.services.CameraService;
import com.duckduckgogogo.utils.DeleteCamera;
import com.duckduckgogogo.utils.JSONHandler;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@Service("cameraService")
public class CameraServiceImpl extends Info implements CameraService {
    @Override
    public String addCamera(String cameraName, String rtspUrl, int server, String entranceGuard,
                            String entranceGuardNO, String cameraXY) {
        try {
            String url = "http://" + super.serverIP + "/api/camera/add";
            //String filePath = "D:\\人脸识别\\4b90f603738da977215057e4bb51f8198718e386.jpg";

            RestTemplate rest = new RestTemplate();
            //FileSystemResource resource = new FileSystemResource(new File(filePath));

//            MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
//            param.add("cameraName", cameraName);
//            param.add("rtspUrl", rtspUrl);
//            param.add("server", server);
//            param.add("entranceGuard", entranceGuard);
//            param.add("entranceGuardNO", entranceGuardNO);
//            param.add("cameraXY", cameraXY);

            JSONObject object = new JSONObject();
            object.put("cameraName",cameraName);
            object.put("rtspUrl",rtspUrl);
            object.put("server",server);
            object.put("entranceGuard",entranceGuard);
            object.put("entranceGuardNO",entranceGuardNO);
            object.put("cameraXY",cameraXY);

            rest.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));

            return rest.postForObject(url, object, String.class);
        }catch (Exception e){
            System.out.println("Error: add error");

            return "";
        }
    }

    @Override
    public String searchCamera(String search, String offset, String limit) {
        try{
            String url = "http://" + super.serverIP + "/api/camera/search";

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
    public String updateCamera(int id, int version, String cameraName, String rtspUrl, int server,
                               String entranceGuard, String entranceGuardNO, String cameraXY) {
        try{
            String url = "http://" + super.serverIP + "/api/camera/update";

            RestTemplate rest = new RestTemplate();

            MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
            param.add("cameraName", cameraName);
            param.add("rtspUrl", rtspUrl);
            param.add("server", server);
            param.add("entranceGuard", entranceGuard);
            param.add("entranceGuardNO", entranceGuardNO);
            param.add("cameraXY", cameraXY);
            param.add("id", id);
            param.add("version", version);

            rest.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));

            String request = "{\"server\":\""      + server + "\"," +
                    "\"entranceGuard\":\""          + entranceGuard + "\"," +
                    "\"version\":\""   + version + "\"," +
                    "\"rtspUrl\":\"" + rtspUrl + "\"," +
                    "\"cameraXY\":\""        + cameraXY + "\"," +
                    "\"entranceGuardNO\":\"" + entranceGuardNO + "\"," +
                    "\"id\":\"" + id + "\"," +
                    "\"cameraName\":\"" + cameraName + "\"}";




            return rest.postForObject(url,request,String.class);

        } catch (Exception e){
            return "";
        }


    }

    @Override
    public String deleteCamera(List<String> listOfcameraID) {
        try{
            String url = "http://" + super.serverIP + "/api/camera/delete";

            JSONArray array = new JSONArray();

            for(String temp : listOfcameraID){
                array.add(new DeleteCamera(temp));
            }

            String request = array.toString();

            RestTemplate rest = new RestTemplate();

            return rest.postForObject(url, request, String.class);

        } catch (Exception e){
            return "";
        }

    }

    @Override
    public String get(int id) {
        try{
            String url = "http://" + super.serverIP + "/api/camera/get/"+id;

            RestTemplate rest = new RestTemplate();

            return rest.getForObject(url,String.class);

        }catch (Exception e){
            return "";
        }
    }

    public Map<String, String> messageHandler(){
        return null;
    }
}
