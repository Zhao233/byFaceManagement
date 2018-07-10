package com.duckduckgogogo.services;

import java.util.List;

public interface CameraService {
    String addCamera(String cameraName, String rtspUrl, int server, String entranceGuard, String entranceGuardType, String entranceGuardNO, String cameraXY);

    String searchCamera(String search, String offset, String limit);

    String updateCamera(int id, int version, String cameraName, String rtspUrl, int server, String entranceGuard, String entranceGuardType, String entranceGuardNO, String cameraXY);

    String deleteCamera(List<String> listOfcameraID);

    String get(int id);
}
