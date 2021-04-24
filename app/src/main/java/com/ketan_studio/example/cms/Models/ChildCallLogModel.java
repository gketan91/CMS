package com.ketan_studio.example.cms.Models;

public class ChildCallLogModel {
    String CallDate,Call_Duration,Call_Number,Call_Time,Call_Type,Contact_Name;

    public ChildCallLogModel() {
    }

    public ChildCallLogModel(String callDate, String call_Duration, String call_Number, String call_Time, String call_Type, String contact_Name) {
        CallDate = callDate;
        Call_Duration = call_Duration;
        Call_Number = call_Number;
        Call_Time = call_Time;
        Call_Type = call_Type;
        Contact_Name = contact_Name;
    }

    public String getCallDate() {
        return CallDate;
    }

    public void setCallDate(String callDate) {
        CallDate = callDate;
    }

    public String getCall_Duration() {
        return Call_Duration;
    }

    public void setCall_Duration(String call_Duration) {
        Call_Duration = call_Duration;
    }

    public String getCall_Number() {
        return Call_Number;
    }

    public void setCall_Number(String call_Number) {
        Call_Number = call_Number;
    }

    public String getCall_Time() {
        return Call_Time;
    }

    public void setCall_Time(String call_Time) {
        Call_Time = call_Time;
    }

    public String getCall_Type() {
        return Call_Type;
    }

    public void setCall_Type(String call_Type) {
        Call_Type = call_Type;
    }

    public String getContact_Name() {
        return Contact_Name;
    }

    public void setContact_Name(String contact_Name) {
        Contact_Name = contact_Name;
    }
}
