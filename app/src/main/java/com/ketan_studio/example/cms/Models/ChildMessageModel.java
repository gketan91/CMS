package com.ketan_studio.example.cms.Models;

public class ChildMessageModel {
    String Body,Date,Number;

    public ChildMessageModel() {
    }

    public ChildMessageModel(String body, String date, String number) {
        Body = body;
        Date = date;
        Number = number;
    }

    public String getBody() {
        return Body;
    }

    public void setBody(String body) {
        Body = body;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }
}
