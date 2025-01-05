package com.cts.hcbanking.exception;

import java.util.Date;

//import java.sql.Date;

public class ErrorDetails {
    private Date timeStamp;
    private String message;
    private String details;
   
    public ErrorDetails(Date timeStamp, String message, String details) {
        this.timeStamp = timeStamp;
        this.message = message;
        this.details = details;
    }
    public Date getTimeStamp() {
        return timeStamp;
    }
    public String getMessage() {
        return message;
    }
    public String getDetails() {
        return details;
    }
   
 
}