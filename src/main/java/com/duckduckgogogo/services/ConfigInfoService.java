package com.duckduckgogogo.services;

import com.duckduckgogogo.domain.ConfigInfo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfigInfoService{
    ConfigInfo findById(long id);
    String search();
    String save(String serverIPandPort, String imageDBName, String receiveURL, int userquality,
                int visitorquality, int similarscore, int warningscore, int time1,
                int time2, int time3, int version);
}
