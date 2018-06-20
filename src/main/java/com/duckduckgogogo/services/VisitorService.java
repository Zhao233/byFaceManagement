package com.duckduckgogogo.services;

import net.sf.json.JSONObject;
import org.springframework.core.io.FileSystemResource;

import java.util.List;

public interface VisitorService {
    String searchVisitor(String search, int offset, int limit, String role);

    String addVisitor(FileSystemResource resource, JSONObject Visitor);

    String updateVisitor(int personID,String personName);

    String deleteVisitor(List<Integer> list);

    String getVisitorById(int id);
}
