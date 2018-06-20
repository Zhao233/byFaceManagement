package com.duckduckgogogo.services;

import net.sf.json.JSONObject;
import org.springframework.core.io.FileSystemResource;

import java.util.List;

public interface VisitorService {
    String searchVisitor(String search, int offset, int limit, String role);

    String addVisitor(FileSystemResource resource, String personName, String IDNumber, String phoneNumber, String startDate, String endData);

    String updateVisitor(FileSystemResource resource, int personID, String personName, String personNumber, String phoneNumber, int version, String startDate, String endDate);

    String deleteVisitor(List<Integer> list);

    String getVisitorById(int id);
}
