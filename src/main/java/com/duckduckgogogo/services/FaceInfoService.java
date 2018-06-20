package com.duckduckgogogo.services;

public interface FaceInfoService {
    String searchFace(String search, int offset, int limit);

    String updateFace(int personID, String personName);

    String deleteFace(int personID);
}
