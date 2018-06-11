package com.example.demo.mapper;

import com.example.demo.model.Employee;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EmployeeMapper {
    String dbName = "employee_test";

    /**
     * 增加一个员工
     * */
//    @Insert("INSERT INTO " +
//            "employee_test" +
//            "(" +
//            "id, name, card_number, phone_number, email, password, face_info" +
//            ") " +
//            "VALUES" +
//            "(" +
//            "#{employee.id}, #{employee.name}, #{employee.card_number}, " +
//            "#{employee.phone_number}, #{employee.email}, #{employee.password), " +
//            "#{employee.face_info})")
//    void insert(@Param("employee") Employee employee);

    /**
     * 根据用户ID删除员工
     * */
    @Delete("DELETE FROM "+dbName+" WHERE id = #{id}")
    void deleteById(@Param("id") int id);


    /**
     * 更新员工信息
     * */
    @Update("update "+dbName+
            "set " +
            "name=#{employee.name}," +
            "card_number=#{employee.card_number}," +
            "phone_number=#{employee.phone_number}," +
            "password=#{employee.password}," +
            "email=#{employee.email}," +
            "face_info=#{employee.face_info} " +
            "where id=#{employee.id}")
    void updateEmployee(@Param("employee") Employee employee);

    /**
     * 获取所有用户
     * */
    @Select("select * from "+dbName)
    List<Employee> getAllEmployee();

    /**
     * 根据用户名获取用户（用户名唯一（在表中字段为unique））
     * */
    @Select("select * from "+dbName+" where name = #{name}")
    Employee getEmployeeByName(@Param("name") String name);

}
