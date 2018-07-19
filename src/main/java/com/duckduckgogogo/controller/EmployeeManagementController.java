package com.duckduckgogogo.controller;

import com.duckduckgogogo.services.EmployeeService;
import com.duckduckgogogo.utils.JSONHandler;
import com.duckduckgogogo.utils.PasswordEncodeAssistant;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
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
@RequestMapping("/console/employee_management")
public class EmployeeManagementController {
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/search")
    @ResponseBody
    public  Map<String,Object> search(@RequestParam(value = "search", defaultValue = "") String search,
                                      @RequestParam(value = "offset", defaultValue = "0") Integer offset,
                                      @RequestParam(value = "limit", defaultValue = "10") Integer limit){
        Map<String, Object> r = new HashMap<>();

        String response = employeeService.searchEmployee(search, offset, limit,"staff");
        System.out.println("search employee succeed : "+response);

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
                                      @RequestParam(value = "personNumber") String personNumber,
                                      @RequestParam(value = "cardNumber") String cardNumber,
                                      @RequestParam(value = "IDNumber") String IDNumber,
                                      @RequestParam(value = "phoneNumber") String phoneNumber
                                      ){
        Map<String, Object> r = new HashMap<>();

        MultipartFile mf = request.getFile("file");
        FileSystemResource resource =null;

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

        String response = employeeService.addEmployee(resource,personName,personNumber,cardNumber,IDNumber,phoneNumber);
        System.out.println(response);

        if(JSONHandler.isSuccess(response)){
            r.put("status","SUCCEED");
        } else {
            r.put("status", "FAILED");
        }

        return r;
    }

    @RequestMapping("/update")
    @ResponseBody
    public  Map<String,Object> update(MultipartHttpServletRequest request,
                                      @RequestParam(value = "personID") int personID,
                                      @RequestParam(value = "personName") String personName,
                                      @RequestParam(value = "personNumber") String personNumber,
                                      @RequestParam(value = "cardNumber") String cardNumber,
                                      @RequestParam(value = "IDNumber") String IDNumber,
                                      @RequestParam(value = "phoneNumber") String phoneNumber,
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

        String response = employeeService.updateEmployee(resource, personID, personName, personNumber, cardNumber, IDNumber, phoneNumber, version + 1);
        System.out.println(response);

        if(JSONHandler.isSuccess(response)){
            r.put("status","SUCCEED");
        } else {
            r.put("status", "FAILED");
        }

        return r;
    }

    @RequestMapping("/get/{id}")
    @ResponseBody
    public  Map<String,Object> get(@PathVariable(value = "id") int personID){
        Map<String, Object> r = new HashMap<>();

        String response = employeeService.getEmployeeById(personID);
        System.out.println("get employee succeed : "+response);

        if(JSONHandler.isSuccess(response)){
            JSONObject object = JSONObject.fromObject(response);

            r.put("name",object.getString("name"));
            r.put("personNumber",object.getString("personNumber"));
            r.put("cardNumber",object.getString("cardNumber"));
            r.put("phoneNumber",object.getString("phoneNumber"));
            r.put("IDNumber",object.getString("IDNumber"));
            r.put("version",object.getString("version"));
            r.put("feature",object.getString("feature"));

            r.put("status","SUCCEED");
        } else {
            r.put("status","FAILED");
        }

        return r;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public  Map<String,Object> delete(@RequestParam(value = "list_ID", defaultValue = "") List<Integer> list){
        Map<String, Object> r = new HashMap<>();

        String response = employeeService.deleteEmployee(list);
        System.out.println(response);

        if(JSONHandler.isSuccess(response)){
            r.put("status", "SUCCEED");
        } else {
            r.put("status", "FAILED");
        }
        return r;
    }

    @RequestMapping("/detect")
    @ResponseBody
    private Map<String,Object> detect(MultipartHttpServletRequest request){
        Map<String, Object> r = new HashMap<>();

        MultipartFile mf = request.getFile("file");
        FileSystemResource resource =null;

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

        String response = employeeService.detect(resource);
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
