package com.example.demo.serviceImp;

import com.example.demo.mapper.EmployeeMapper;
import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service("employeeService")
public class EmployeeServiceImp implements EmployeeService {

    @Autowired
    private EmployeeMapper mapper;

    @Override
    public Employee getEmployeeByName(String name) {
        try{
            return mapper.getEmployeeByName(name);
        }catch (Exception e){
            //log the error

            return new Employee();
        }
    }

    @Override
    public List<Employee> getAllEmployee() {
        try{
            return mapper.getAllEmployee();
        }catch (Exception e){
            //log the error

            return new LinkedList<Employee>();
        }
    }

    @Override
    public void updateEmployee(Employee employee) {
        try{
            mapper.updateEmployee(employee);
        }catch (Exception e){
            //log the error

        }
    }

    @Override
    public void deleteEmployeeById(int id) {
        try{
            mapper.deleteById(id);
        } catch (Exception e){
            //log the error

        }
    }

    @Override
    public void addEmployee(Employee employee) {
        try{
//            mapper.insert(employee);
        } catch (Exception e){
            //log the error

        }
    }
}
