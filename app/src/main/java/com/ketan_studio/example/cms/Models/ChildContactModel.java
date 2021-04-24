package com.ketan_studio.example.cms.Models;

public class ChildContactModel {
    String Name,PhoneNumber;

    public ChildContactModel() {
    }

    public ChildContactModel(String name, String phoneNumber) {
        Name = name;
        PhoneNumber = phoneNumber;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }
}
