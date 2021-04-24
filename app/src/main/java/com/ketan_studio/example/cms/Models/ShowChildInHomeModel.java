package com.ketan_studio.example.cms.Models;

public class ShowChildInHomeModel {
    String Name;
    String Key_Code;

    public ShowChildInHomeModel() {
    }

    public ShowChildInHomeModel(String name, String key_Code) {
        Name = name;
        Key_Code = key_Code;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getKey_Code() {
        return Key_Code;
    }

    public void setKey_Code(String key_Code) {
        Key_Code = key_Code;
    }
}
