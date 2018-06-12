package com.duckduckgogogo.utils;

public class Server {
    private String serverIP;
    private String serverName;
    private String updateDateString;
    private String id;
    private String version;
    private String enabled;
    private String mainServer;

    public Server(String serverIP, String serverName, String updateDateString, String id, String version, String enabled, String mainServer) {
        this.serverIP = serverIP;
        this.serverName = serverName;
        this.updateDateString = updateDateString;
        this.id = id;
        this.version = version;
        this.enabled = enabled;
        this.mainServer = mainServer;
    }

    public String getServerIP() {
        return serverIP;
    }

    public void setServerIP(String serverIP) {
        this.serverIP = serverIP;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getUpdateDateString() {
        return updateDateString;
    }

    public void setUpdateDateString(String updateDateString) {
        this.updateDateString = updateDateString;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public String getMainServer() {
        return mainServer;
    }

    public void setMainServer(String mainServer) {
        this.mainServer = mainServer;
    }
}
