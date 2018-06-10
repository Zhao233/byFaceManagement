package com.example.demo;

import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
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

    @Test
    public void contextLoads() {
    }

    @Test
    public void test_insert(){
        userMapper.insert(0,"jack","123456","123345");
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

}
