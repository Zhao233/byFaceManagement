package com.example.demo;

import com.example.demo.mapper.EmployeeMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.Employee;
import com.example.demo.model.User;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.type.MappedTypes;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("com.example.demo.mapper")
@MappedTypes({UserMapper.class})

public class DemoApplicationTests {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;

    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private EmployeeService employeeService;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void contextLoads() {
    }

    /**
     * mapper测试
     * */

    //User相关
    @Test
    public void test_insert(){
        User user = new User("2","jack","123456","123345");

        userMapper.insert(user);
    }
    @Test
    public void test_select(){
        List<User> list= userMapper.getAllUser();

        for(User user : list){
            System.out.println(user.toString());
        }
    }
    @Test
    public void test_update(){
        User user = userMapper.getUserByName("jack");

        user.setName("Hellen");

        userMapper.updateUser(user);
    }
    @Test
    public void test_delete(){
        userMapper.deleteById(0);
    }


    //Employee 相关
    @Test
    public void test_insert_employee(){
        Employee employee = new Employee(1,"tom","1234567","13516367666","938373847@qq.com","123456678","safasdfasdfasdf");

        //employeeMapper.insert(employee);
    }
    @Test
    public void test_select_employee(){
        List<Employee> list= employeeMapper.getAllEmployee();

        for(Employee employee : list){
            System.out.println(employee.toString());
        }
    }
    @Test
    public void test_update_employee(){
        Employee employee = employeeMapper.getEmployeeByName("aaa");

        employee.setName("Hellen");

        employeeMapper.updateEmployee(employee);
    }

    @Test
    public void test_delete_employee(){
        employeeMapper.deleteById(1);
    }




    /**
     * service层测试
     * */

    //User 相关
    @Test
    public void test_insert_(){
        User user = new User("2","jack_","123456","123345");

        userService.addUser(user);
    }
    @Test
    public void test_select_(){
        List<User> list= userService.getAllUser();

        for(User user : list){
            System.out.println(user.toString());
        }
    }
    @Test
    public void test_update_(){
        User user = userService.getUserByName("jack");

        user.setName("Hellen");

        userService.updateUser(user);
    }
    @Test
    public void test_delete_(){
        userService.deleteUserById(2);
    }


    /**
     * 分页功能
     * */
    @Test
    public void testFindByPage() {
        int pageNum = 1;
        int pageSize = 10;
        PageHelper.startPage(pageNum, pageSize);
        List<User> list = userService.getAllUser();
        PageInfo pageInfo = new PageInfo(list);
        Page page = (Page) list;
        try {
            System.out.println("PageInfo: " + objectMapper.writeValueAsString(pageInfo) + ", Page: " + objectMapper.writeValueAsString(page));
        } catch (JsonProcessingException e) {
            e.printStackTrace();


        }
    }


}
