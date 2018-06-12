package com.duckduckgogogo.services;

import java.util.List;

public interface ServerService {
    String searchServers(String search, String offset, String limit);

    String addServer(String serverName,String serverIP,String isMainServer);

    String updateServer(String serverName,String serverIP,String isMainServer);

    String deleteServer(List<String> list);

    String getServerById(String id);
}
