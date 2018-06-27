package com.duckduckgogogo.services;

import net.sf.json.JSONObject;
import org.springframework.core.io.FileSystemResource;

import java.util.List;

public interface EmployeeService {
    String searchEmployee(String search, int offset, int limit, String role);

    String addEmployee(FileSystemResource resource, String personName, String personNumber, String cardNumber, String IDNumber, String phoneNumber);
//    String addEmployee(String personName, String personNumber, String cardNumber, String IDNumber, String phoneNumber);

    String updateEmployee(FileSystemResource resource, int personID, String personName, String personNumber, String cardNumber, String IDNumber, String phoneNumber, int version);

    String deleteEmployee(List<Integer> list);

    String getEmployeeById(int id);
}
