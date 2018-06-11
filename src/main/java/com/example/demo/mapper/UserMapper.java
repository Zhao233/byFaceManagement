package com.example.demo.mapper;

import com.example.demo.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    String test_DB = "user_test";
    String DB = "";

    String dbName = "user_test";

    /**
     * 增加一个用户
     * */
    @Insert("INSERT INTO user_test(ID,NAME, PASSWORD, PHONE) VALUES(#{user.id}, #{user.name}, #{user.password}, #{user.phone})")
    int insert(@Param("user") User user);

    /**
     * 根据用户ID删除用户
     * */
    @Delete("DELETE FROM user_test WHERE id = #{id}")
    void deleteById(@Param("id") int id);

    /**
     * 更新用户信息
     * */
    @Update("update user_test " +
            "set " +
            "name = #{user.name}," +
            "password=#{user.password}, " +
            "phone=#{user.phone} " +
            "where id = #{user.id}")
    void updateUser(@Param("user") User user);

    /**
     * 获取所有用户
     * */
    @Select("select * from user_test")
    List<User> getAllUser();

    /**
     * 根据用户名获取用户（用户名唯一（在表中字段为unique））
     * */
    @Select("select * from user_test where name=#{name}")
    User getUserByName(@Param("name") String name);

    @Select("select * from "+test_DB+" where name=#{username}")
    List<User> getUsersByName(@Param("username") String name);

}