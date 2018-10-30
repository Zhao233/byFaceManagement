package com.duckduckgogogo.services;

import java.util.List;

public interface CameraService {
    String addCamera(String cameraName, String rtspUrl, int server, String entranceGuard, String entranceGuardType, String entranceGuardNO, String cameraXY,int time1,int cameraType, int faceWidth);

    String searchCamera(String search, String offset, String limit);

    String updateCamera(int id, int version, String cameraName, String rtspUrl, int server, String entranceGuard, String entranceGuardType, String entranceGuardNO, String cameraXY, int time1,  int cameraType, int faceWidth);

    String deleteCamera(List<String> listOfcameraID);

    String get(int id);
}
