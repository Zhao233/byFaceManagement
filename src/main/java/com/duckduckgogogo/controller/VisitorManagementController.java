package com.duckduckgogogo.controller;

import com.duckduckgogogo.services.VisitorService;
import com.duckduckgogogo.utils.JSONHandler;
import com.duckduckgogogo.utils.PasswordEncodeAssistant;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/console/visitor_management")
public class VisitorManagementController {
    @Autowired
    private VisitorService visitorService;

    @RequestMapping("/search")
    @ResponseBody
    public  Map<String,Object> search(@RequestParam(value = "search", defaultValue = "") String search,
                                      @RequestParam(value = "offset", defaultValue = "0") Integer offset,
                                      @RequestParam(value = "limit", defaultValue = "10") Integer limit){
        Map<String, Object> r = new HashMap<>();

        String response = visitorService.searchVisitor(search, offset, limit,"visitor");

        System.out.println("search visitor succeed : "+response);

        JSONObject object = JSONObject.fromObject(response);


        r.put("total",object.getInt("total"));
        JSONArray array = object.getJSONArray("rows");

        for(int i = 0 ; i < array.size(); i++){
            array.getJSONObject(i).remove("server");
        }

        array.remove("server");
        r.put("rows", array);

        return r;
    }

    @RequestMapping("/add")
    @ResponseBody
    public  Map<String,Object> add(MultipartHttpServletRequest request,
                                   @RequestParam(value = "personName") String personName,
                                   @RequestParam(value = "IDNumber") String IDNumber,
                                   @RequestParam(value = "phoneNumber") String phoneNumber,
                                   @RequestParam(value = "title")  String title
    ){
        Map<String, Object> r = new HashMap<>();

        MultipartFile mf = request.getFile("file");
        FileSystemResource resource = null;
        if(mf != null) {

            String filename = mf.getOriginalFilename();
            String suffix = filename.substring(filename.indexOf('.') + 1);
            // File.separator
            String folder = System.getProperty("java.io.tmpdir");
            String datetime = String.valueOf(new Date().getTime());
            String target = folder + PasswordEncodeAssistant.encode((datetime + filename).toCharArray()) + "." + suffix;
            File file = new File(target);

            try (FileInputStream fis = (FileInputStream) mf.getInputStream();
                 FileOutputStream fos = new FileOutputStream(target)) {
                byte[] b = new byte[1024];
                int i = fis.read(b);
                while (i > -1) {
                    fos.write(b, 0, b.length);
                    fos.flush();
                    i = fis.read(b);
                }
            } catch (Exception e) {
                try {
                    throw e;
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

            resource = new FileSystemResource(file);
        }

        String response = visitorService.addVisitor(resource,personName,IDNumber,phoneNumber,title);
        System.out.println(response);

        if(JSONHandler.isSuccess(response)){
            r.put("status","SUCCEED");
        } else {
            JSONObject object = JSONObject.fromObject(response);

            if(object.getString("errorMessage") != null){
                r.put( "errorMessage", object.getString("errorMessage") );
            }

            r.put("status", "FAILED");

            r.put("status", "FAILED");
        }

        return r;
    }

    @RequestMapping("/update")
    @ResponseBody
    public  Map<String,Object> update(MultipartHttpServletRequest request,
                                      @RequestParam(value = "personID") int personID,
                                      @RequestParam(value = "personName") String personName,
                                      @RequestParam(value = "IDNumber") String IDNumber,
                                      @RequestParam(value = "phoneNumber") String phoneNumber,
                                      @RequestParam(value = "title") String title,
                                      @RequestParam(value = "version") int version){

        Map<String, Object> r = new HashMap<>();

        MultipartFile mf = request.getFile("file");
        FileSystemResource resource = null;

        if(mf != null) {

            String filename = mf.getOriginalFilename();
            String suffix = filename.substring(filename.indexOf('.') + 1);
            // File.separator
            String folder = System.getProperty("java.io.tmpdir");
            String datetime = String.valueOf(new Date().getTime());
            String target = folder + PasswordEncodeAssistant.encode((datetime + filename).toCharArray()) + "." + suffix;
            File file = new File(target);

            try (FileInputStream fis = (FileInputStream) mf.getInputStream();
                 FileOutputStream fos = new FileOutputStream(target)) {
                byte[] b = new byte[1024];
                int i = fis.read(b);
                while (i > -1) {
                    fos.write(b, 0, b.length);
                    fos.flush();
                    i = fis.read(b);
                }
            } catch (Exception e) {
                try {
                    throw e;
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

            resource = new FileSystemResource(file);
        }

        String response = visitorService.updateVisitor(resource,personID, personName, IDNumber, phoneNumber, title,version+1);
        System.out.println(response);

        if(JSONHandler.isSuccess(response)){
            r.put("status","SUCCEED");
        } else {
            JSONObject object = JSONObject.fromObject(response);

            if(object.getString("errorMessage") != null){
                r.put( "errorMessage", object.getString("errorMessage") );
            }

            r.put("status", "FAILED");

            r.put("status", "FAILED");
        }
        return r;
    }

    @RequestMapping("/get/{id}")
    @ResponseBody
    public  Map<String,Object> get(@PathVariable(value = "id") int personID){
        Map<String, Object> r = new HashMap<>();

        String response = visitorService.getVisitorById(personID);

        System.out.println("get visitor succeed : "+response);

        if(JSONHandler.isSuccess(response)){
            JSONObject object = JSONObject.fromObject(response);

            r.put("name",object.getString("name"));
            r.put("personNumber",object.getString("personNumber"));
            r.put("cardNumber",object.getString("cardNumber"));
            r.put("phoneNumber",object.getString("phoneNumber"));
            r.put("IDNumber",object.getString("IDNumber"));
            r.put("version",object.getString("version"));
            r.put("startDateString",object.getString("startDateString"));
            r.put("endDateString",object.getString("endDateString"));
            r.put("title",object.getString("title"));
            r.put("feature",object.getString("feature"));

            r.put("status","SUCCEED");
        } else {
            JSONObject object = JSONObject.fromObject(response);

            if(object.getString("errorMessage") != null){
                r.put( "errorMessage", object.getString("errorMessage") );
            }

            r.put("status", "FAILED");

            r.put("status","FAILED");
        }

        return r;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public  Map<String,Object> delete(@RequestParam(value = "list_ID", defaultValue = "") List<Integer> list){
        Map<String, Object> r = new HashMap<>();

        String response = visitorService.deleteVisitor(list);
        System.out.println(response);

        if(JSONHandler.isSuccess(response)){
            r.put("status","SUCCEED");
        } else {
            JSONObject object = JSONObject.fromObject(response);

            r.put("status", "FAILED");

            if(object.getString("errorMessage") != null) {
                r.put("errorMessage", object.getString("errorMessage"));
            }
        }

        return r;
    }
}
