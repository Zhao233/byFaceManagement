package com.example.demo.model;

public class Employee {
    private String id;
    private String name;
    private String card_number;
    private String phone_number;
    private String email;
    private String password;
    private String face_info;

    public Employee(String id, String name, String card_number, String phone_number, String email, String password, String face_info) {
        this.id = id;
        this.name = name;
        this.card_number = card_number;
        this.phone_number = phone_number;
        this.email = email;
        this.password = password;
        this.face_info = face_info;
    }

    public Employee(int id, String name, String card_number, String phone_number, String email, String password, String face_info)
    {
        this.id = String.valueOf(id);
        this.name = name;
        this.card_number = card_number;
        this.phone_number = phone_number;
        this.email = email;
        this.password = password;
        this.face_info = face_info;
    }

    public Employee(){}


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFace_info() {
        return face_info;
    }

    public void setFace_info(String face_info) {
        this.face_info = face_info;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", card_number='" + card_number + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", face_info='" + face_info + '\'' +
                '}';
    }
}
