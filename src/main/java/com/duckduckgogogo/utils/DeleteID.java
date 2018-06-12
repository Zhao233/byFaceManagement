package com.duckduckgogogo.utils;

/**
 * 删除时Json所用的包装类
 * */
public class DeleteID {
    private String serverID;

    public DeleteID(String serverID) {
        this.serverID = serverID;
    }

    public String getServerID() {
        return serverID;
    }

    public void setServerID(String serverID) {
        this.serverID = serverID;
    }
}
