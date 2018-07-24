package com.duckduckgogogo.services;

public interface CameraInfoService {
    String getChart(int cameraID, String date);

    String getRank(int cameraID, String date);

    String getChart_hour(int cameraID, String date);

    String getRank_hour(int cameraID, String date);
}
