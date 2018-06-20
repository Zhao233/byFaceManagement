package com.duckduckgogogo.test;

import com.duckduckgogogo.controller.EmployeeManagementController;
import com.duckduckgogogo.services.CameraService;
import com.duckduckgogogo.services.ConfigInfoService;
import com.duckduckgogogo.services.EmployeeService;
import com.duckduckgogogo.services.ServerService;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BootstrapTests {

    private static final String serverIP = "10.8.20.146:8080";

    @Autowired
    private ServerService serverService;

    @Autowired
    private ConfigInfoService configInfoService;

    @Autowired
    private CameraService cameraService;

    @Autowired
    private EmployeeService employeeService;

//    @Autowired
//    private ServerService serverService;
//
//    @Test
//    public void contextLoads() {
//        Server server = serverService.findById(1);
//        System.out.println(server.toString());
//    }

    @Test
    public void testRestTemplate(){
        String search = "";
        String offset = "0";
        String limit = "10";

        String response = serverService.searchServers(search, String.valueOf(offset),String.valueOf(limit));

        System.out.println(response);

        JSONArray array = JSONArray.fromObject(response);
        JSONArray array1 = new JSONArray();

        array1.add("");

        String[] arr=new String[array.size()];


        for(int i=0;i<array.size();i++){
            arr[i]=array.getString(i);

            JSONObject object = JSONObject.fromObject(arr[i]);
            object.remove("updateDate");

            array1.add(object);

            object.getString("serverIP");

        }

        System.out.println(array1.toString());

    }

    @Test
    public void testRestTemplate_add(){
        serverService.addServer("test_1","123.206.301.1","false");
        serverService.addServer("test_2","123.206.301.2","false");
        serverService.addServer("test_3","123.206.301.3","false");
        serverService.addServer("test_4","123.206.301.4","false");
    }

    @Test
    public void testT(){
        System.out.println(serverService.searchServers("7","0","1"));
    }

    @Test
    public void test_config(){
        System.out.println(configInfoService.save("123","test001","test_url",
                10,30,20,
                10,0,1,2,1));
    }

    @Test
    public void test_Camera(){
        //System.out.println(cameraService.addCamera("test_cam","rtsp://admin:admin@10.22.0.198:554/cam/realmonitor?channel=1&subtype=0",1,"test_entranceGuard","test_No","123,111,2222"));

        System.out.println(cameraService.searchCamera("","0","10"));

        //System.out.println(cameraService.updateCamera(3,1,"test_camera_updated","rtsp://admin:admin@10.22.0.198:554/cam/realmonitor?channel=1&subtype=0",1,"test_entranceGuard","test_No","123,111,2222"));

        //System.out.println(cameraService.get(3));

        //List<Integer> list = new LinkedList<>();
        //list.add(3);

        //System.out.println(cameraService.deleteCamera(list));
    }

    @Test
    public void JSON(){
        JSONArray array = new JSONArray();
        HashMap<String, Integer> map;

        List<Integer> list = new LinkedList<>();

        list.add(123);
        list.add(123);
        list.add(123);
        list.add(123);

        for(int temp : list){
            map = new HashMap<>();

            map.put("taskID",temp);

            array.add(map);
        }

        System.out.println(array);
    }

    @Test
    public  void Employee(){
        EmployeeManagementController controller = new EmployeeManagementController();

        Map<String, Object> map = controller.search("",0,10);
    }

}