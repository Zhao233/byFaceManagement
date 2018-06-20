package com.duckduckgogogo.utils;

/**
 * 删除时Json所用的包装类
 * */
public class DeleteCamera {
    public String cameraID;

    public DeleteCamera(String cameraID) {
        this.cameraID = cameraID;
    }

    public String getCameraID() {
        return cameraID;
    }

    public void setCameraID(String cameraID) {
        this.cameraID = cameraID;
    }
}
