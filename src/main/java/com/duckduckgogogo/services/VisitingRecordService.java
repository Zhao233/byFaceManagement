package com.duckduckgogogo.services;

import java.util.List;

public interface VisitingRecordService {
    String searchVisitingRecord(String search, int offset, int limit);

    String addVisitingRecord(int userInfo, String cause, String startDate, String endDate);

    String updateVisitingRecord(int id, int version, String userInfo, String cause, String startDate, String endDate);

    String getVisitingRecordById(int id);

    String deleteVisitingRecord(List<Integer> list);

}
