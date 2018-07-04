package com.duckduckgogogo.services;

import java.util.List;

public interface ContactsService {
    String addContactsInfo(String personName, String phoneNumber, String sendPhoneNumber, String email, String sendEmail);

    String searchContactsInfo(String search, String offset, String limit);
//    String addEmployee(String personName, String personNumber, String cardNumber, String IDNumber, String phoneNumber);

    String updateContactsInfo(int id, int version, String personName, String phoneNumber, String sendPhoneNumber, String email, String sendEmail);

    String deleteContactsInfo(List<Integer> list);

    String getContactsInfoById(int id);
}
