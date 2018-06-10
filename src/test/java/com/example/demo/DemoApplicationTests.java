package com.example.demo;

import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.apache.ibatis.type.MappedTypes;
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

    @Test
    public void contextLoads() {
    }

    /**
     * mapper测试
     * */
    @Test
    public void test_insert(){
        User user = new User("1","jack","123456","123345");

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

    /**
     * service层测试
     * */
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

}
