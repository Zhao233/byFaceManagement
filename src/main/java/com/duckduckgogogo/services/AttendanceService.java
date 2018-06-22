package com.duckduckgogogo.services;

import java.util.List;

public interface AttendanceService {
    String searchServers(String search, int offset, int limit, String startDate, String endDate, int id);
}
