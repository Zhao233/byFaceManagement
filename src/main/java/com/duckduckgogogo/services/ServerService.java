package com.duckduckgogogo.services;

import java.util.List;

public interface ServerService {
    String searchServers(String search, String offset, String limit);

    String addServer(String serverName,String serverIP,String isMainServer);

    String updateServer(int id, String serverName,String serverIP,String isMainServer,int version);

    String deleteServer(List<Integer> list);

    String getServerById(int id);

    //获取状态信息
    String getServerInfoById(int id, String serverIP);
}
