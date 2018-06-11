package com.example.demo.serviceImp;

import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service("userService")
public class UserServiceImp implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByName(String name) {
        try {

            return userMapper.getUserByName(name);
        } catch (Exception e){


            return null;
        }
    }

    @Override
    public List<User> getAllUser() {
        try {
            return userMapper.getAllUser();
        } catch (Exception e){


            return new LinkedList<User>();
        }

    }

    @Override
    public void updateUser(User user) {
        try {
            userMapper.updateUser(user);
        } catch (Exception e){



        }
    }

    @Override
    public void deleteUserById(int id) {
        try {
            userMapper.deleteById(id);
        } catch (Exception e){



        }
    }


    @Override
    public void addUser(User user) {
        try {
            userMapper.insert(user);
        } catch (Exception e){



        }


    }

}
