package com.example.glcalendarandroid;

import com.google.api.client.util.DateTime;

import java.util.Date;

public class GoogleUserAppointment {
    public String Id;
    public String Summary;
    public Date StartTime;
    public Date EndTime;

    public GoogleUserAppointment(String Id, String Summary, long StartTime, long EndTime) {
        this.Id = Id;
        this.Summary = Summary;
        this.StartTime = new Date(StartTime);
        this.EndTime = new Date(EndTime);
    }

}
