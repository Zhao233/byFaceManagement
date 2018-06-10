package com.example.demo.service;

import com.example.demo.model.User;

import java.util.List;

public interface UserService {
    User getUserByName(String name);

    List<User> getAllUser();

    void updateUser(User user);

    void deleteUserById(int id);

    void addUser(User user);
}
