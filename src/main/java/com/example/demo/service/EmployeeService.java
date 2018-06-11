package com.example.demo.service;

import com.example.demo.model.Employee;
import com.example.demo.model.User;

import java.util.List;

public interface EmployeeService {
    Employee getEmployeeByName(String name);

    List<Employee> getAllEmployee();

    void updateEmployee(Employee employee);

    void deleteEmployeeById(int id);

    void addEmployee(Employee employee);
}
