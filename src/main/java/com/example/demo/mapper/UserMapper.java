package com.example.demo.mapper;

import com.example.demo.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Insert("INSERT INTO user_test(ID,NAME, PASSWORD, PHONE) VALUES(#{id}, #{name}, #{password}, #{phone})")
    int insert(@Param("id") int id, @Param("name") String name, @Param("password") String password, @Param("phone") String phone);

    @Delete("DELETE FROM user_test WHERE id = #{id}")
    void deleteById(@Param("id") int id);

    @Update("update user_test " +
            "set " +
            "name = #{user.name},password=#{user.password}, phone=#{user.phone} " +
            "where id = #{user.id}")
    void updateUser(@Param("user") User user);

    @Select("select * from user_test")
    List<User> getAllUser();

    @Select("select * from user_test where name = #{name}")
    User getUserByName(@Param("name") String name);

}