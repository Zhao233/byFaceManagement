package com.duckduckgogogo.services;

public interface TraceService {
    String search(String startDate,String endDate, int id);

    String search_trace(String search, int offset, int limit);
}
